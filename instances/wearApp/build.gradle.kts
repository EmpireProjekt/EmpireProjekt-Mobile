@file:OptIn(ExperimentalEncodingApi::class)

import ru.astrainteractive.gradleplugin.property.baseGradleProperty
import ru.astrainteractive.gradleplugin.property.extension.ModelPropertyValueExt.requireProjectInfo
import ru.astrainteractive.gradleplugin.property.extension.PrimitivePropertyValueExt.requireInt
import ru.astrainteractive.gradleplugin.property.extension.PrimitivePropertyValueExt.stringOrEmpty
import ru.astrainteractive.gradleplugin.property.secretProperty
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi


plugins {
    kotlin("plugin.serialization")
    id("com.android.application")
    alias(libs.plugins.kotlin.multiplatform)
    id("ru.astrainteractive.gradleplugin.java.version")
    id("ru.astrainteractive.gradleplugin.android.sdk")
    id("ru.astrainteractive.gradleplugin.android.apk.name")
    alias(libs.plugins.kotlin.compose.gradle)
    id("dev.icerock.mobile.multiplatform-resources")
}

kotlin {
    androidTarget()
    applyDefaultHierarchyTemplate()
}

android {
    namespace = "${requireProjectInfo.group}"
    val gServicesFile = file("google-services.json")
    if (!gServicesFile.exists()) {
        logger.warn("google-services.json not exists - creating")
        val base64String = secretProperty("GSERVICES_BASE64").stringOrEmpty
        if (base64String.isNotBlank()) {
            val byteArray = Base64.decode(base64String)
            gServicesFile.createNewFile()
            gServicesFile.writeBytes(byteArray)
        }
    }

    if (file("google-services.json").exists()) {
        apply(plugin = "com.google.gms.google-services")
        apply(plugin = "com.google.firebase.crashlytics")
    } else {
        logger.error("google-services.json not exists - could not create from secret!")
    }
    defaultConfig {
        applicationId = requireProjectInfo.group
        versionCode = baseGradleProperty("project.version.code").requireInt
        versionName = requireProjectInfo.versionString
        setProperty(
            "archivesBaseName",
            "${requireProjectInfo.name}-${requireProjectInfo.versionString}"
        )
    }
    defaultConfig {
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        val keyStoreFile = file("keystore.jks")
        val secretKeyAlias = secretProperty("KEY_ALIAS").stringOrEmpty
        val secretKeyPassword = secretProperty("KEY_PASSWORD").stringOrEmpty
        val secretStorePassword = secretProperty("STORE_PASSWORD").stringOrEmpty
        if (!keyStoreFile.exists()) {
            logger.warn("Keystore file not exists - creating")
            val base64String = secretProperty("KEYSTORE_BASE64").stringOrEmpty
            if (base64String.isNotBlank()) {
                val byteArray = Base64.decode(base64String)
                keyStoreFile.createNewFile()
                keyStoreFile.writeBytes(byteArray)
            }
        }
        if (!keyStoreFile.exists()) {
            logger.warn("Keystore file could not be created")
        }
        getByName("debug") {
            if (keyStoreFile.exists()) {
                keyAlias = secretKeyAlias
                keyPassword = secretKeyPassword
                storePassword = secretStorePassword
                storeFile = keyStoreFile
            }
        }
        create("release") {
            if (keyStoreFile.exists()) {
                keyAlias = secretKeyAlias
                keyPassword = secretKeyPassword
                storePassword = secretStorePassword
                storeFile = keyStoreFile
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
        debug {
            isDebuggable = true
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    buildFeatures {
        compose = true
    }
    lint {
        abortOnError = false
    }
}

dependencies {
    // Kotlin
    implementation(libs.kotlin.serialization.json)
    // klibs
    implementation(libs.klibs.mikro.extensions)
    // Coroutines
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.coroutines.android)
    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation(libs.androidx.compose.wear.material)
    implementation(libs.androidx.compose.wear.foundation)

    implementation(libs.wear.glance.tiles)

    implementation(libs.wear.tiles)
    implementation(libs.wear.tiles.material)
    implementation(libs.google.horologist.compose.tools)
    implementation(libs.google.horologist.tiles)
    implementation(libs.wear.complications.datasource.ktx)
    implementation(libs.google.horologist.datalayer.watch)
    implementation(libs.google.horologist.datalayer.phone)
    // klibs
    implementation(libs.klibs.mikro.core)
    implementation(libs.klibs.mikro.platform)
    implementation(libs.klibs.kstorage)
    implementation(libs.klibs.kdi)
    // Settings
    implementation(libs.mppsettings)
    // moko
    implementation(libs.moko.resources.core)
    // Decompose
    implementation(libs.decompose.core)
    implementation(libs.decompose.compose)
    implementation(libs.google.gms.services.wearable)
    // Local
    implementation(projects.modules.features.root.api)
    implementation(projects.modules.features.root.impl)
    implementation(projects.modules.features.theme.api)
    implementation(projects.modules.features.theme.impl)
    implementation(projects.modules.features.theme.ui)
    implementation(projects.modules.features.status.api)
    implementation(projects.modules.features.status.impl)
    implementation(projects.modules.services.core.ui.common)
    implementation(projects.modules.services.core.ui.dialog)
    implementation(projects.modules.services.core.ui.sheet)
    implementation(projects.modules.services.core.ui.theme)
    implementation(projects.modules.services.core.resources)
    implementation(projects.modules.services.wearMessenger.api)
    implementation(projects.modules.services.wearMessenger.pingWear)
    implementation(projects.modules.services.wearMessenger.common)
    implementation(projects.modules.services.core.common)
}

multiplatformResources {
    resourcesPackage.set("${requireProjectInfo.group}.wearapp")
    resourcesClassName.set("WR")
}
