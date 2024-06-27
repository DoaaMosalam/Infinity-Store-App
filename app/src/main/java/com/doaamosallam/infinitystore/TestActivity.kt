package com.doaamosallam.infinitystore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.doaamosallam.data.local.AppDatabase
import com.doaamosallam.data.local.LoginEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TestActivity : ComponentActivity() {
    @Inject
    lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        testDatabaseOperations()
    }

    private fun testDatabaseOperations() {
        lifecycleScope.launch {
            val loginDao = appDatabase.loginDao()

            // Insert a login entity
            val loginEntity = LoginEntity(email = "test@example.com", password = "password")
            val id = loginDao.insertLogin(loginEntity)

            // Log the inserted ID
            println("Inserted LoginEntity with ID: $id")

            // Query the login entity
            val queriedEntity = loginDao.getLogin("Doaa.Mosalam@example.com", "Doaa12345")

            // Log the queried entity
            println("Queried LoginEntity: $queriedEntity")

            // Check if the queried entity is not null and matches the inserted entity
            if (queriedEntity != null && queriedEntity.email == loginEntity.email && queriedEntity.password == loginEntity.password) {
                println("Data saved successfully and queried correctly.")
            } else {
                println("Failed to save or query the data correctly.")
            }
        }
    }

}


//    // Create an in-memory Room database instance for testing
////    val context = ApplicationProvider.getApplicationContext<Context>()
////    val db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
////    // Create DAO instances
////    val loginDAO = db.loginDao()
////    val registerDAO = db.registerDao()
//
//    // Create mock DAOs for testing
//    val mockLoginDao = createMockLoginDAO()
//    val mockRegisterDao = createMockRegisterDAO()
//
//    // Repositories and UseCases
//    val loginRepo = LoginRepoImp(mockLoginDao)
//    val loginUseCase = LoginUseCase(loginRepo)
//    val loginViewModel = LoginViewModel(loginUseCase)
//
//    val registerRepo = RegisterRepoImp(mockRegisterDao)
//    val registerUseCase = RegisterUseCase(registerRepo)
//    val registerViewModel = RegisterViewModel(registerUseCase)
//
//    // Hard-coded values for testing registration
//    val registerData = createTestRegisterData()
//    // Hard-coded values for testing login
//    val loginData = createTestLoginData()
//
//    // Perform registration and login
//    runBlocking {
//        // Register user
//        launch {
//            registerViewModel.register(registerData)
//            registerViewModel.registerState.collect { registerStatus ->
//                when (registerStatus) {
//                    is RequestStatus.Success -> {
//                        println("Registration successful: ${registerStatus.data}")
//
//                        // Login with the registered user
//                        loginViewModel.login(loginData)
//                        loginViewModel.loginState.collect { loginStatus ->
//                            when (loginStatus) {
//                                is RequestStatus.Success -> {
//                                    println("Login successful: ${loginStatus.data}")
//                                }
//                                is RequestStatus.Error -> {
//                                    println("Login failed: ${loginStatus.message}")
//                                }
//                                else -> {
//                                    println("Unexpected login state")
//                                }
//                            }
//                        }
//                    }
//                    is RequestStatus.Error -> {
//                        println("Registration failed: ${registerStatus.message}")
//                    }
//                    else -> {
//                        println("Unexpected registration state")
//                    }
//                }
//            }
//        }
//    }
//
//}
//
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
//
//fun createTestRegisterData(): Register {
//    return Register(
//        name = "John Doe",
//        phone = "123456789",
//        email = "doaa.mosalam@example.com",
//        password = "password123",
//        confirmPassword = "password123"
//    )
//}
//
//fun createTestLoginData(): Login {
//    return Login(
//        email = "doaa.mosalam@example.com",
//        password = "password123"
//    )


