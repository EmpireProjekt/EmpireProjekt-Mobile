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
    unitKind.set("api")
    rootPackage.set("com.makeevrserg.empireprojekt.mobile.features.root.api")
}

kotlin {
    androidLibrary {
        withHostTest {}
    }
    applyDefaultHierarchyTemplate()
    sourceSets {
        getByName("commonMain") {
            dependencies {
                implementation(libs.decompose.core)
                implementation(libs.essenty)
                implementation(libs.kotlin.coroutines.core)
                implementation(libs.kotlin.serialization.json)
                implementation(projects.modules.features.rating.api)
                implementation(projects.modules.features.towns.api)
                implementation(projects.modules.features.webview.api)
                implementation(projects.modules.services.core.common)
            }
        }
        getByName("androidHostTest") {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
        getByName("commonTest") {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.kotlin.serialization.json)
                implementation(projects.modules.features.rating.api)
            }
        }
    }
}
