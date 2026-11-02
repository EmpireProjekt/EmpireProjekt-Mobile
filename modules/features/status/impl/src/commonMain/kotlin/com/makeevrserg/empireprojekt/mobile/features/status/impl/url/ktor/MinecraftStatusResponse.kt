package com.makeevrserg.empireprojekt.mobile.features.status.impl.url.ktor

import kotlinx.serialization.Serializable

@Serializable
internal data class MinecraftStatusResponse(
    val description: String,
    val players: Players
) {
    @Serializable
    data class Players(
        val max: Int,
        val online: Int
    )
}
