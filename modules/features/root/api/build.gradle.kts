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
                // Decompose
                implementation(libs.decompose.core)
                // Local
                implementation(projects.modules.services.core.common)
            }
        }
    }
}
android {
    namespace = "${requireProjectInfo.group}.features.root.api"
}
