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
                implementation(libs.klibs.mikro.core)
                implementation(libs.klibs.mikro.extensions)
                implementation(libs.kotlin.coroutines.core)
                implementation(libs.moko.resources.core)
                implementation(projects.modules.features.webview.api)
                implementation(projects.modules.services.core.common)
                implementation(projects.modules.services.core.resources)
            }
        }
    }
}
