plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.doaamosallam.domain"
    compileSdk = 34 // todo 1- convert to version Catalog in the libs.version.catalog file

    defaultConfig {
        minSdk = 27 // todo 2- convert to version Catalog in the libs.version.catalog file

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
        sourceCompatibility = JavaVersion.VERSION_1_8 // todo 3- you should work with Java 17
        targetCompatibility = JavaVersion.VERSION_1_8 // todo 4- you should work with Java 17
    }
    kotlinOptions {
        jvmTarget = "1.8" // todo 5- you should work with Java 17
    }
}

dependencies {
    testImplementation(libs.junit)
}