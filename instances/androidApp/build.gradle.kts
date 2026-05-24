@file:OptIn(ExperimentalEncodingApi::class)

import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.api.variant.impl.VariantOutputImpl
import com.android.build.gradle.tasks.ManifestProcessorTask
import ru.astrainteractive.gradle.property.api.klibsGradleProperty
import ru.astrainteractive.gradle.property.api.klibsSecretProperty
import ru.astrainteractive.gradleplugin.property.util.requireInt
import ru.astrainteractive.gradleplugin.property.util.requireProjectInfo
import ru.astrainteractive.gradleplugin.property.util.stringOrEmpty
import ru.astrainteractive.gradleplugin.task.SecretFileTask
import kotlin.io.encoding.ExperimentalEncodingApi


plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("ru.astrainteractive.gradleplugin.android.apk.sign")
    id("ru.astrainteractive.gradleplugin.android.compose")
    id("ru.astrainteractive.gradleplugin.android.java")
    id("ru.astrainteractive.gradleplugin.android.sdk")
    id("ru.astrainteractive.gradleplugin.java.version")
}

val exportKeystore = tasks.register<SecretFileTask>("exportKeystore") {
    targetFile = file("keystore.jks")
    base64 = klibsSecretProperty("KEYSTORE_BASE64").stringOrEmpty
}

val exportGServicesFile = tasks.register<SecretFileTask>("exportGServicesFile") {
    targetFile = file("google-services.json")
    base64 = klibsSecretProperty("GSERVICES_BASE64").stringOrEmpty
}

tasks.withType<ManifestProcessorTask>().configureEach {
    dependsOn(exportKeystore)
    dependsOn(exportGServicesFile)
}

android {
    namespace = requireProjectInfo.group

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

configure<ApplicationAndroidComponentsExtension> {
    onVariants { variant ->
        variant.outputs.onEach { output ->
            if (output is VariantOutputImpl) {
                val name = requireProjectInfo.name
                val version = requireProjectInfo.versionString
                output.outputFileName.set("$name-android-$version-${variant.name}.apk")
            }
        }
    }
}

dependencies {
    debugImplementation(libs.leakcanary)
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-crashlytics-ndk")
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.service)
    implementation(libs.androidx.splash)
    implementation(libs.androidx.work.runtime)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.decompose.compose)
    implementation(libs.decompose.core)
    implementation(libs.google.auth)
    implementation(libs.google.gms.services.wearable)
    implementation(libs.google.horologist.datalayer)
    implementation(libs.jetbrains.compose.foundation)
    implementation(libs.jetbrains.compose.material)
    implementation(libs.jetbrains.compose.material3)
    implementation(libs.jetbrains.compose.preview)
    implementation(libs.jetbrains.compose.runtime)
    implementation(libs.jetbrains.compose.tooling)
    implementation(libs.jetbrains.compose.ui)
    implementation(libs.klibs.kstorage)
    implementation(libs.klibs.mikro.core)
    implementation(libs.klibs.mikro.platform)
    implementation(libs.kotlin.coroutines.android)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.coroutines.playServices)
    implementation(libs.kotlin.serialization.json)
    implementation(libs.moko.resources.core)
    implementation(platform(libs.google.firebase.bom))
    implementation(projects.modules.features.root.api)
    implementation(projects.modules.features.root.impl)
    implementation(projects.modules.features.root.ui)
    implementation(projects.modules.features.status.api)
    implementation(projects.modules.features.status.impl)
    implementation(projects.modules.features.theme.api)
    implementation(projects.modules.features.theme.impl)
    implementation(projects.modules.features.theme.ui)
    implementation(projects.modules.services.core.common)
    implementation(projects.modules.services.core.resources)
    implementation(projects.modules.services.core.ui.common)
    implementation(projects.modules.services.core.ui.dialog)
    implementation(projects.modules.services.core.ui.sheet)
    implementation(projects.modules.services.core.ui.theme)
    implementation(projects.modules.services.wearMessenger.api)
    implementation(projects.modules.services.wearMessenger.common)
    implementation(projects.modules.services.wearMessenger.pingWear)
}
