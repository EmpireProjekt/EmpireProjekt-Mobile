package com.makeevrserg.empireprojekt.mobile.features.votes.ui.model

import dev.icerock.moko.resources.ImageResource

data class VoteUrl(
    val name: String,
    val url: String,
    val hasPrize: Boolean,
    val image: ImageResource
)
