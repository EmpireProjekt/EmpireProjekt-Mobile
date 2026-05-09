@file:OptIn(ExperimentalEncodingApi::class)

import com.android.build.gradle.internal.tasks.ValidateSigningTask
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType
import ru.astrainteractive.gradle.property.api.klibsGradleProperty
import ru.astrainteractive.gradle.property.api.secretProperty
import ru.astrainteractive.gradleplugin.property.util.requireInt
import ru.astrainteractive.gradleplugin.property.util.requireProjectInfo
import ru.astrainteractive.gradleplugin.property.util.stringOrEmpty
import ru.astrainteractive.gradleplugin.task.SecretFileTask
import kotlin.io.encoding.ExperimentalEncodingApi


plugins {
    kotlin("plugin.serialization")
    id("com.android.application")
    id("ru.astrainteractive.gradleplugin.java.version")
    id("ru.astrainteractive.gradleplugin.android.sdk")
    id("ru.astrainteractive.gradleplugin.android.java")
    id("ru.astrainteractive.gradleplugin.android.apk.name")
    id("ru.astrainteractive.gradleplugin.android.apk.sign")
    id("ru.astrainteractive.gradleplugin.android.compose")
    alias(libs.plugins.kotlin.compose.gradle)
}

tasks.register<SecretFileTask>("exportKeystore") {
    targetFile = file("keystore.jks")
    base64 = secretProperty("KEYSTORE_BASE64").stringOrEmpty
    tasks.withType<ValidateSigningTask>().all {
        this.dependsOn(this@register)
    }
}

tasks.register<SecretFileTask>("exportGServicesFile") {
    targetFile = file("google-services.json")
    base64 = secretProperty("GSERVICES_BASE64").stringOrEmpty
    tasks.withType<ValidateSigningTask>().all {
        this.dependsOn(this@register)
    }
}

android {
    namespace = "${requireProjectInfo.group}"

    if (file("google-services.json").exists()) {
        apply(plugin = "com.google.gms.google-services")
        apply(plugin = "com.google.firebase.crashlytics")
    } else {
        logger.error("google-services.json not exists - could not create from secret!")
    }
    defaultConfig {
        applicationId = requireProjectInfo.group
        versionCode = klibsGradleProperty("project.version.code").requireInt
        versionName = requireProjectInfo.versionString
    }
    defaultConfig {
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isDebuggable = true
        }
    }
    packaging {
        resources {
            merges += "**/values-night/colors.xml"
            merges += "**/values/colors.xml"
            merges += "**/values/multiplatform_strings.xml"
        }
        with(resources.excludes) {
            add("META-INF/INDEX.LIST")
        }
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
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.splash)
    implementation(libs.jetbrains.compose.foundation)
    implementation(libs.jetbrains.compose.material)
    implementation(libs.jetbrains.compose.material3)
    implementation(libs.jetbrains.compose.preview)
    implementation(libs.jetbrains.compose.runtime)
    implementation(libs.jetbrains.compose.tooling)
    implementation(libs.jetbrains.compose.ui)
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
    implementation(projects.modules.services.core.resources)
}
