plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}


android {
    compileSdkVersion(30);
    buildToolsVersion("30.0.3");

    defaultConfig {
        applicationId("com.example.hiltgradlekotlintest");
        minSdkVersion(23);
        targetSdkVersion(30);
        versionCode(1);
        versionName("1.0");

        testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner");
    }

    buildTypes {
        getByName("release") {
            minifyEnabled(false)
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility("${JavaVersion.VERSION_1_8}")
        targetCompatibility("${JavaVersion.VERSION_1_8}")
    }
    kotlinOptions {
        jvmTarget = ("1.8");
    }
}

dependencies {
    api(platform(project(":depconstraints")))
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("com.google.android.material:material:1.4.0")
    kapt(platform(project(":depconstraints")))
    androidTestApi(platform(project(":depconstraints")))
//    implementation(project(":shared"))

    val kotlinVersion = "1.4.31";
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${kotlinVersion}")

    implementation(Libs.APPCOMPAT)
    implementation(Libs.MATERIAL)

    // Dagger Hilt
    implementation(Libs.HILT_ANDROID)
    androidTestImplementation(Libs.HILT_TESTING)
    kapt(Libs.HILT_COMPILER)
    kaptAndroidTest(Libs.HILT_COMPILER)

    // Network
    implementation(Libs.RETROFIT)

    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}