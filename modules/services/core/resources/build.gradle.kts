@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.property.util.requireProjectInfo

plugins {
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.kotlin.multiplatform")
    id("ru.astrainteractive.gradleplugin.android.namespace")
    id("ru.astrainteractive.gradleplugin.android.sdk")
    id("ru.astrainteractive.gradleplugin.java.version")
    id("dev.icerock.mobile.multiplatform-resources")
    alias(libs.plugins.klibs.rules)
}

linter {
    unitKind.set("library")
    rootPackage.set("com.makeevrserg.empireprojekt.mobile.core.resources")
}

kotlin {
    androidLibrary {}
    applyDefaultHierarchyTemplate()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.moko.resources.core)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.android.material)
                implementation(libs.androidx.splash)
            }
        }
    }
}

multiplatformResources {
    resourcesPackage.set("${requireProjectInfo.group}.core.resources")
}