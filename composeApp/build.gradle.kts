import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinSerializer)
    alias(libs.plugins.nativeCocoaPod)
    alias(libs.plugins.kotlinAndroidParcelize)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
            freeCompilerArgs.addAll(
                "-P",
                "plugin:org.jetbrains.kotlin.parcelize:additionalAnnotation=org.example.marketaja.Parcelize"
            )
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    )

    cocoapods {
        summary = "Deskripsi app lu"
        homepage = "https://example.com"
        version = "1.0.0"
        ios.deploymentTarget = "14.0"
        framework {
            baseName = "ComposeApp"
            isStatic = false
        }
//        podfile = project.file("../iosApp/Podfile")
    }


    sourceSets {

        commonMain.dependencies {
//            implementation(compose.runtime)
//            implementation(compose.foundation)
//            implementation(compose.material)
//            implementation(compose.material3)
//            implementation(compose.ui)
//            implementation(libs.androidx.lifecycle.viewmodel)
//            implementation(libs.androidx.lifecycle.runtime.compose)
//            implementation(libs.circuit.foundation)
//            implementation(libs.circuitx.gesture.navigation)

            implementation(projects.core)
            implementation(projects.data)
        }
    }
}

android {
    namespace = "org.example.marketaja"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "org.example.marketaja"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }
}
dependencies {
    implementation(libs.androidx.activity.ktx)
}

