@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.property.extension.ModelPropertyValueExt.requireProjectInfo

plugins {
    id("dev.icerock.mobile.multiplatform-resources")
    alias(libs.plugins.android.library)
    kotlin("multiplatform")
    id("ru.astrainteractive.gradleplugin.java.version")
    id("ru.astrainteractive.gradleplugin.android.sdk")
    alias(libs.plugins.klibs.gradle.android.namespace)
}

kotlin {
    androidTarget()
    applyDefaultHierarchyTemplate()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.moko.resources.core)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.splash)
                implementation(libs.android.material)
            }
        }
    }
}

multiplatformResources {
    resourcesPackage.set("${requireProjectInfo.group}.core.resources")
}