package com.doaamosallam.infinitystore.com.doaamosallam.infinitystore

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.doaamosallam.data.data.local.AppDatabase
import com.doaamosallam.data.repo.LoginRepoImp
import com.doaamosallam.data.repo.RegisterRepoImp
import com.doaamosallam.domain.models.Login
import com.doaamosallam.domain.models.Register
import com.doaamosallam.domain.usecase.LoginUseCase
import com.doaamosallam.domain.usecase.RegisterUseCase
import com.doaamosallam.infinitystore.util.RequestStatus
import com.doaamosallam.infinitystore.viewmodel.user.Login.LoginViewModel
import com.doaamosallam.infinitystore.viewmodel.user.register.RegisterViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
//

fun main() {
    // Create an in-memory Room database instance for testing
    val context = ApplicationProvider.getApplicationContext<Context>()
    val db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
    // Create DAO instances
    val loginDAO = db.loginDao()
    val registerDAO = db.registerDao()

    // Create mock DAOs for testing
//    val mockLoginDao = createMockLoginDAO()
//    val mockRegisterDao = createMockRegisterDAO()

    // Repositories and UseCases
    val loginRepo = LoginRepoImp(loginDAO)
    val loginUseCase = LoginUseCase(loginRepo)
    val loginViewModel = LoginViewModel(loginUseCase)

    val registerRepo = RegisterRepoImp(registerDAO)
    val registerUseCase = RegisterUseCase(registerRepo)
    val registerViewModel = RegisterViewModel(registerUseCase)

    // Hard-coded values for testing registration
    val registerData = createTestRegisterData()
    // Hard-coded values for testing login
    val loginData = createTestLoginData()

    // Perform registration and login
    runBlocking {
        // Register user
        launch {
            registerViewModel.register(registerData)
            registerViewModel.registerState.collect { registerStatus ->
                when (registerStatus) {
                    is RequestStatus.Success -> {
                        println("Registration successful: ${registerStatus.data}")

                        // Login with the registered user
                        loginViewModel.login(loginData)
                        loginViewModel.loginState.collect { loginStatus ->
                            when (loginStatus) {
                                is RequestStatus.Success -> {
                                    println("Login successful: ${loginStatus.data}")
                                }
                                is RequestStatus.Error -> {
                                    println("Login failed: ${loginStatus.message}")
                                }
                                else -> {
                                    println("Unexpected login state")
                                }
                            }
                        }
                    }
                    is RequestStatus.Error -> {
                        println("Registration failed: ${registerStatus.message}")
                    }
                    else -> {
                        println("Unexpected registration state")
                    }
                }
            }
        }
    }

}

//fun createMockLoginDAO(): LoginDAO {
//    return object : LoginDAO {
//        private val loginEntities = mutableListOf<LoginEntity>()
//
//        override suspend fun insertLogin(loginEntity: LoginEntity): Long {
//            loginEntities.add(loginEntity)
//            return loginEntity.id ?: 0
//        }
//
//        override suspend fun getLogin(email: String, password: String): LoginEntity? {
//            return loginEntities.find { it.email == email && it.password == password }
//        }
//    }
//}
//
//fun createMockRegisterDAO(): RegisterDAO {
//    return object : RegisterDAO {
//        private val registerEntities = mutableListOf<RegisterEntity>()
//
//        override suspend fun insertRegister(registerEntity: RegisterEntity): Long {
//            registerEntities.add(registerEntity)
//            return registerEntity.id ?: 0
//        }
//    }
//}

fun createTestRegisterData(): Register {
    return Register(
        name = "John Doe",
        phone = "123456789",
        email = "doaa.mosalam@example.com",
        password = "password123",
        confirmPassword = "password123"
    )
}

fun createTestLoginData(): Login {
    return Login(
        email = "doaa.mosalam@example.com",
        password = "password123"
    )
}


