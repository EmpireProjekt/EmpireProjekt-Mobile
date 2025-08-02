@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.property.extension.ModelPropertyValueExt.requireProjectInfo

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("ru.astrainteractive.gradleplugin.java.core")
    id("ru.astrainteractive.gradleplugin.android.core")
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    androidTarget()
    applyDefaultHierarchyTemplate()
    sourceSets {
        val commonMain by getting {
            dependencies {
                // kotlin
                implementation(libs.kotlin.serialization.json)
                // klibs
                implementation(libs.klibs.mikro.core)
                implementation(libs.klibs.mikro.platform)
                implementation(libs.klibs.kstorage)
                implementation(libs.klibs.kdi)
                // Decompose
                implementation(libs.decompose.core)
                implementation(libs.essenty)
                // Moko
                implementation(libs.moko.resources.core)
                // Coroutines
                implementation(libs.kotlin.coroutines.core)
                // settings
                implementation(libs.mppsettings)
                // MVIKotlin
                implementation(libs.mvikotlin)
                // Local
                implementation(projects.modules.services.core.resources)
                implementation(projects.modules.services.core.common)
                implementation(projects.modules.services.data.empireapi)
                implementation(projects.modules.features.root.api)
                implementation(projects.modules.features.splash.impl)
                implementation(projects.modules.features.rating.api)
                implementation(projects.modules.features.rating.impl)
                implementation(projects.modules.features.theme.api)
                implementation(projects.modules.features.theme.impl)
                implementation(projects.modules.features.status.api)
                implementation(projects.modules.features.status.impl)
                implementation(projects.modules.features.towns.api)
                implementation(projects.modules.features.towns.impl)
                implementation(projects.modules.features.tabs.impl)
                implementation(projects.modules.features.webview.impl)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.client.cio)
            }
        }
    }
}
android {
    namespace = "${requireProjectInfo.group}.shared"
    sourceSets {
        getByName("main").java.srcDirs("build/generated/moko/androidMain/src")
    }
}

dependencies {
    // FireBase
    implementation(platform(libs.google.firebase.bom))
    implementation(libs.klibs.kstorage)
    implementation(libs.google.auth)
    implementation(libs.kotlin.coroutines.playServices)
    implementation("io.ktor:ktor-client-logging-jvm:${libs.versions.ktor.get()}")
}
