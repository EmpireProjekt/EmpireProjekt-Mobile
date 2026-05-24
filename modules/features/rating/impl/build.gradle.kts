@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradleplugin.property.util.requireProjectInfo

plugins {
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("ru.astrainteractive.gradleplugin.android.namespace")
    id("ru.astrainteractive.gradleplugin.android.sdk")
    id("ru.astrainteractive.gradleplugin.java.version")
    id("dev.icerock.mobile.multiplatform-resources")
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
                implementation(libs.klibs.mikro.extensions)
                implementation(libs.klibs.mikro.platform)
                implementation(libs.klibs.paging)
                implementation(libs.kotlin.coroutines.core)
                implementation(libs.moko.resources.core)
                implementation(libs.mppsettings)
                implementation(libs.mvikotlin)
                implementation(libs.mvikotlin.coroutines)
                implementation(libs.mvikotlin.main)
                implementation(projects.modules.features.rating.api)
                implementation(projects.modules.services.core.common)
                implementation(projects.modules.services.core.resources)
                implementation(projects.modules.services.data.empireapi)
            }
        }
    }
}

multiplatformResources {
    resourcesPackage.set("${requireProjectInfo.group}.rating")
    resourcesClassName.set("RR")
}
