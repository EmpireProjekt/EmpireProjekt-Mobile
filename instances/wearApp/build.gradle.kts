@file:OptIn(ExperimentalEncodingApi::class)

import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.api.variant.impl.VariantOutputImpl
import com.android.build.gradle.internal.tasks.ValidateSigningTask
import com.android.build.gradle.tasks.ManifestProcessorTask
import com.google.gms.googleservices.GoogleServicesTask
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withType
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
    id("ru.astrainteractive.gradleplugin.android.apk.name")
    id("ru.astrainteractive.gradleplugin.android.apk.sign")
    id("ru.astrainteractive.gradleplugin.android.compose")
    id("ru.astrainteractive.gradleplugin.android.java")
    id("ru.astrainteractive.gradleplugin.android.sdk")
    id("ru.astrainteractive.gradleplugin.java.version")
}

val keystoreBase64 = klibsSecretProperty("KEYSTORE_BASE64").stringOrEmpty
val gServicesBase64 = klibsSecretProperty("GSERVICES_BASE64").stringOrEmpty

// SecretFileTask creates its target file even when the secret is blank, and an empty
// google-services.json fails the build harder than a missing one, so skip the task instead.
val exportKeystore = tasks.register<SecretFileTask>("exportKeystore") {
    // Held in a local so onlyIf captures the string rather than the enclosing build script,
    // which the configuration cache cannot serialize.
    val secret = keystoreBase64
    targetFile = file("keystore.jks")
    base64 = secret
    onlyIf { secret.isNotBlank() }
}

val exportGServicesFile = tasks.register<SecretFileTask>("exportGServicesFile") {
    val secret = gServicesBase64
    targetFile = file("google-services.json")
    base64 = secret
    onlyIf { secret.isNotBlank() }
}

tasks.withType<ManifestProcessorTask>().configureEach {
    dependsOn(exportKeystore)
    dependsOn(exportGServicesFile)
}

// processReleaseGoogleServices reads google-services.json and validateSigningRelease reads
// keystore.jks. Neither is ordered after manifest processing, so wire them up explicitly.
tasks.withType<GoogleServicesTask>().configureEach {
    dependsOn(exportGServicesFile)
}

tasks.withType<ValidateSigningTask>().configureEach {
    dependsOn(exportKeystore)
}

android {
    namespace = requireProjectInfo.group

    // These plugins have to be applied during configuration, but exportGServicesFile only writes
    // google-services.json during execution. On a fresh checkout the file is therefore never there
    // when a file existence check runs here, so release builds used to ship without Firebase even
    // with the secret set. Decide on the secret instead; the task dependency above puts the file
    // in place before processGoogleServices reads it.
    if (gServicesBase64.isNotBlank() || file("google-services.json").length() > 0L) {
        apply(plugin = "com.google.gms.google-services")
        apply(plugin = "com.google.firebase.crashlytics")
    } else {
        logger.warn("Neither GSERVICES_BASE64 nor google-services.json is available - building without Firebase")
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
                output.outputFileName.set("$name-wearos-$version-${variant.name}.apk")
            }
        }
    }
}

dependencies {
    debugImplementation(libs.wear.tiles.tooling)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.wear.foundation)
    implementation(libs.androidx.compose.wear.material)
    implementation(libs.androidx.splash)
    implementation(libs.decompose.compose)
    implementation(libs.decompose.core)
    implementation(libs.google.gms.services.wearable)
    implementation(libs.google.horologist.datalayer.phone)
    implementation(libs.google.horologist.datalayer.watch)
    implementation(libs.google.horologist.tiles)
    implementation(libs.jetbrains.compose.foundation)
    implementation(libs.jetbrains.compose.material)
    implementation(libs.jetbrains.compose.material3)
    implementation(libs.jetbrains.compose.preview)
    implementation(libs.jetbrains.compose.runtime)
    implementation(libs.jetbrains.compose.tooling)
    implementation(libs.jetbrains.compose.ui)
    implementation(libs.klibs.kstorage)
    implementation(libs.klibs.mikro.core)
    implementation(libs.klibs.mikro.extensions)
    implementation(libs.klibs.mikro.platform)
    implementation(libs.kotlin.coroutines.android)
    implementation(libs.kotlin.coroutines.core)
    implementation(libs.kotlin.serialization.json)
    implementation(libs.moko.resources.core)
    implementation(libs.mppsettings)
    implementation(libs.wear.complications.datasource.ktx)
    implementation(libs.wear.glance.tiles)
    implementation(libs.wear.tiles)
    implementation(libs.wear.tiles.material)
    implementation(libs.wear.tiles.tooling.preview)
    implementation(libs.wear.tooling.preview)
    implementation(projects.modules.features.root.api)
    implementation(projects.modules.features.root.impl)
    implementation(projects.modules.features.status.api)
    implementation(projects.modules.features.status.impl)
    implementation(projects.modules.features.theme.api)
    implementation(projects.modules.features.theme.impl)
    implementation(projects.modules.features.theme.ui)
    implementation(projects.modules.services.core.common)
    implementation(projects.modules.services.core.resources)
    implementation(projects.modules.services.core.resources)
    implementation(projects.modules.services.core.ui.common)
    implementation(projects.modules.services.core.ui.dialog)
    implementation(projects.modules.services.core.ui.sheet)
    implementation(projects.modules.services.core.ui.theme)
    implementation(projects.modules.services.wearMessenger.api)
    implementation(projects.modules.services.wearMessenger.common)
    implementation(projects.modules.services.wearMessenger.pingWear)
}
