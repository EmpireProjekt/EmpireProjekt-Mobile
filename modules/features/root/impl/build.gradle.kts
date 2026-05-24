@file:Suppress("UnusedPrivateMember")

plugins {
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("ru.astrainteractive.gradleplugin.android.namespace")
    id("ru.astrainteractive.gradleplugin.android.sdk")
    id("ru.astrainteractive.gradleplugin.java.version")
}

kotlin {
    androidLibrary {}
    applyDefaultHierarchyTemplate()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.decompose.core)
                implementation(libs.essenty)
                implementation(libs.klibs.kstorage)
                implementation(libs.klibs.mikro.core)
                implementation(libs.klibs.mikro.platform)
                implementation(libs.kotlin.coroutines.core)
                implementation(libs.kotlin.serialization.json)
                implementation(libs.moko.resources.core)
                implementation(libs.mppsettings)
                implementation(libs.mvikotlin)
                implementation(projects.modules.features.rating.api)
                implementation(projects.modules.features.rating.impl)
                implementation(projects.modules.features.root.api)
                implementation(projects.modules.features.splash.impl)
                implementation(projects.modules.features.status.api)
                implementation(projects.modules.features.status.impl)
                implementation(projects.modules.features.tabs.impl)
                implementation(projects.modules.features.theme.api)
                implementation(projects.modules.features.theme.impl)
                implementation(projects.modules.features.towns.api)
                implementation(projects.modules.features.towns.impl)
                implementation(projects.modules.features.webview.impl)
                implementation(projects.modules.services.core.common)
                implementation(projects.modules.services.core.resources)
                implementation(projects.modules.services.data.empireapi)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.client.cio)
            }
        }
    }
}
