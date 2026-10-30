@file:Suppress("UnusedPrivateMember")

plugins {
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("ru.astrainteractive.gradleplugin.android.namespace")
    id("ru.astrainteractive.gradleplugin.android.sdk")
    id("ru.astrainteractive.gradleplugin.java.version")
    alias(libs.plugins.klibs.rules)
}

linter {
    unitKind.set("library")
    rootPackage.set("com.makeevrserg.empireprojekt.mobile.features.theme")
}

kotlin {
    androidLibrary {}
    applyDefaultHierarchyTemplate()
    sourceSets {
        getByName("commonMain") {
            dependencies {
                implementation(libs.decompose.core)
                implementation(libs.klibs.kstorage)
                implementation(libs.klibs.mikro.core)
                implementation(libs.kotlin.coroutines.core)
                implementation(libs.mppsettings)
                implementation(projects.modules.features.theme.api)
                implementation(projects.modules.services.core.common)
            }
        }
    }
}
