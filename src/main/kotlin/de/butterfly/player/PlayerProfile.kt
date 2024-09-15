package de.butterfly.player

import de.butterfly.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.*




/**
 * Data class representing a player's profile in a game.
 *
 * @property playerid The unique identifier for the player.
 * @property health The current health points of the player.
 * @property mana The current mana points of the player.
 * @property level The current level of the player.
 * @property xp The experience points accumulated by the player.
 * @property inventory A list of items currently held by the player.
 * @property effects A list of status effects currently affecting the player.
 * @property skills A list of skills the player possesses.
 */


@Suppress("unused")
data class PlayerProfile(
    @Serializable(with = UUIDSerializer::class) val playerid: UUID,
    val health: Double,
    val mana: Double,
    val level: Int,
    val xp: Int,
    val inventory: List<String>,
    val effects: List<String>,
    val skills: List<String>
)


