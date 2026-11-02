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
    unit(ru.astrainteractive.klibs.rules.core.unit.model.UnitRole.Impl())
    rootPackage.set("com.makeevrserg.empireprojekt.mobile.features.root.impl")
}

kotlin {
    androidLibrary {}
    applyDefaultHierarchyTemplate()
    sourceSets {
        getByName("commonMain") {
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
                implementation(projects.modules.features.root.api)
                implementation(projects.modules.features.towns.api)
                implementation(projects.modules.features.webview.api)
                implementation(projects.modules.services.core.common)
            }
        }
        getByName("androidMain") {
            dependencies {
                implementation(libs.ktor.client.cio)
            }
        }
    }
}
