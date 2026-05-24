@file:Suppress("UnusedPrivateMember")

import ru.astrainteractive.gradle.property.api.klibsGradleProperty
import ru.astrainteractive.gradleplugin.property.util.requireInt
import ru.astrainteractive.gradleplugin.property.util.requireProjectInfo

plugins {
    id("com.android.kotlin.multiplatform.library")
    id("com.github.gmazzo.buildconfig")
    id("org.jetbrains.kotlin.multiplatform")
    id("ru.astrainteractive.gradleplugin.android.namespace")
    id("ru.astrainteractive.gradleplugin.android.sdk")
    id("ru.astrainteractive.gradleplugin.java.version")
}

buildConfig {
    className("BuildKonfig") // forces the class name. Defaults to 'BuildConfig'
    packageName("${requireProjectInfo.group}.buildkonfig") // forces the package. Defaults to '${project.group}'
    useKotlinOutput { internalVisibility = false }
    buildConfigField(
        name = "VERSION_CODE",
        value = "${klibsGradleProperty("project.version.code").requireInt}"
    )
    buildConfigField(
        name = "VERSION_NAME",
        value = "${requireProjectInfo.versionString}"
    )
    buildConfigField(
        name = "PROD_URL",
        value = "https://empireapi.astrainteractive.ru"
    )
}
kotlin {
    androidLibrary {}
    applyDefaultHierarchyTemplate()
}
