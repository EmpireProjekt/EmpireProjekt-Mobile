package com.makeevrserg.empireprojekt.mobile.features.ui.votes.model

import dev.icerock.moko.resources.ImageResource

data class VoteUrl(
    val name: String,
    val url: String,
    val hasPrize: Boolean,
    val image: ImageResource
)
