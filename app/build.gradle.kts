plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id ("kotlin-parcelize")
    id ("kotlin-kapt")
    id ("androidx.navigation.safeargs")
}

android {
    namespace = "com.main.mobiletelehealthsystem"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.main.mobiletelehealthsystem"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    val lifecycle_version = "2.6.1"

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.5.1")
    implementation ("androidx.navigation:navigation-ui-ktx:2.5.1")
    testImplementation("junit:junit:4.13.2")
    testImplementation ("com.google.truth:truth:1.1.3")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation ("com.google.firebase:firebase-analytics")
    implementation ("com.google.firebase:firebase-auth-ktx:21.0.8")
    implementation ("com.google.firebase:firebase-database-ktx:20.0.6")
    implementation ("com.google.firebase:firebase-storage-ktx:20.0.2")

    // Switch
    implementation ("com.github.GwonHyeok:StickySwitch:0.0.16")

    //Swipe/Slide button
    implementation ("com.ncorti:slidetoact:0.9.0")

    // Material Design
    implementation ("com.google.android.material:material:1.6.1")

    //QR Code generator
    implementation ("com.google.zxing:core:3.4.1")
    implementation ("com.journeyapps:zxing-android-embedded:4.3.0@aar")

    //Chart / Graph
    implementation ("com.github.majorkik:SparkLineLayout:1.0.1")

    // Lottie
    implementation ("com.airbnb.android:lottie:5.2.0")

    //Cryptography
    implementation("androidx.security:security-crypto:1.0.0")

    //Requesting permissions at runtime.
    implementation ("com.karumi:dexter:6.2.3")


    //Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.6.4")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    //ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")

    // LiveData
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")

    //GSON
    implementation ("com.google.code.gson:gson:2.10.1")
}