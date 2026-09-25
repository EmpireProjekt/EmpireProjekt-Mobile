@file:Suppress("UnusedPrivateMember")

plugins {
    id("com.android.kotlin.multiplatform.library")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.plugin.compose")
    id("ru.astrainteractive.gradleplugin.android.namespace")
    id("ru.astrainteractive.gradleplugin.android.sdk")
    id("ru.astrainteractive.gradleplugin.java.version")
}
kotlin {
    androidLibrary {
        withHostTest {
            isIncludeAndroidResources = true
        }
    }
    applyDefaultHierarchyTemplate()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.materialIconsExtended)
                implementation(libs.composeext.shimmer)
                implementation(libs.decompose.compose)
                implementation(libs.decompose.core)
                implementation(libs.jetbrains.compose.foundation)
                implementation(libs.jetbrains.compose.material)
                implementation(libs.jetbrains.compose.material3)
                implementation(libs.jetbrains.compose.preview)
                implementation(libs.jetbrains.compose.runtime)
                implementation(libs.jetbrains.compose.tooling)
                implementation(libs.jetbrains.compose.ui)
                implementation(libs.moko.resources.compose)
                implementation(libs.moko.resources.core)
                implementation(projects.modules.services.core.common)
                implementation(projects.modules.services.core.resources)
                implementation(projects.modules.services.core.ui.common)
                implementation(projects.modules.services.core.ui.theme)
            }
        }
        val androidHostTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(kotlin("test-junit"))
                implementation(libs.androidx.compose.ui.test.manifest)
                implementation(libs.jetbrains.compose.ui.test)
                implementation(libs.tests.espresso.core)
                implementation(libs.tests.robolectric)
            }
        }
    }
}

tasks.withType<Test>().configureEach {
    jvmArgs(
        "--add-exports=java.base/jdk.internal.access=ALL-UNNAMED",
        "--add-opens=java.base/java.io=ALL-UNNAMED"
    )
}
