plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    // ksp
    alias(libs.plugins.com.google.devtools.ksp)
}

android {
    namespace = "com.doaamosallam.data"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(libs.versions.javaVersion.get())
        targetCompatibility = JavaVersion.toVersion(libs.versions.javaVersion.get())
    }
    kotlinOptions {
        jvmTarget = libs.versions.javaVersion.get()
    }
}

dependencies {
    implementation(project(":domain"))

    testImplementation(libs.junit)
    // room
    implementation(libs.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.room.ktx)
    //retrofit
    implementation(libs.retrofit)
    //convert retrofit
    implementation(libs.converter.gson)
}