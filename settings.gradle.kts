pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        mavenLocal()
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://oss.sonatype.org/content/repositories/snapshots")
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        mavenLocal()
        google()
        maven("https://oss.sonatype.org/content/repositories/snapshots")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/public/p/ktor/eap")
    }
}
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "EmpireProjekt-Mobile"
// Instances
include(":instances:androidApp")
include(":instances:wearApp")
// Services
include(":modules:services:core:resources")
include(":modules:services:core:ui:common")
include(":modules:services:core:ui:dialog")
include(":modules:services:core:ui:sheet")
include(":modules:services:core:ui:theme")
include(":modules:services:core:ui:option")
include(":modules:services:core:ui:common")
include(":modules:services:core:common")
include(":modules:services:wear-messenger:api")
include(":modules:services:wear-messenger:ping-wear")
include(":modules:services:wear-messenger:common")
include(":modules:services:core:build-konfig")
include(":modules:services:data:empireapi")
// Feature
include(":modules:features:root:api")
include(":modules:features:root:impl")
include(":modules:features:root:ui")
include(":modules:features:tabs:impl")
include(":modules:features:tabs:ui")
include(":modules:features:splash:impl")
include(":modules:features:splash:ui")
include(":modules:features:theme:api")
include(":modules:features:theme:impl")
include(":modules:features:theme:ui")
include(":modules:features:status:api")
include(":modules:features:status:impl")
include(":modules:features:status:ui")
include(":modules:features:rating:api")
include(":modules:features:rating:impl")
include(":modules:features:rating:ui")
include(":modules:features:towns:api")
include(":modules:features:towns:impl")
include(":modules:features:towns:ui")
include(":modules:features:info:ui")
include(":modules:features:map:ui")
