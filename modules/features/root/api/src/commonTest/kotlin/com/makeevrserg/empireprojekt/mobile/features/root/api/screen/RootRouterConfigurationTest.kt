@file:Suppress("FunctionNaming")

package com.makeevrserg.empireprojekt.mobile.features.root.api.screen

import com.makeevrserg.empireprojekt.mobile.features.rating.api.model.RatingUserId
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class RootRouterConfigurationTest {
    private val serializer = RootRouter.Configuration.RatingUser.serializer()

    @Test
    fun GIVEN_rating_user_configuration_WHEN_encoded_THEN_user_id_is_a_plain_number() {
        val configuration = RootRouter.Configuration.RatingUser(
            userId = RatingUserId(42),
            userName = "Steve"
        )

        val actual = Json.encodeToString(serializer, configuration)

        assertEquals("""{"userId":42,"userName":"Steve"}""", actual)
    }

    @Test
    fun GIVEN_state_saved_with_a_plain_number_WHEN_decoded_THEN_user_id_is_restored() {
        val actual = Json.decodeFromString(serializer, """{"userId":42,"userName":"Steve"}""")

        assertEquals(RatingUserId(42), actual.userId)
    }
}
