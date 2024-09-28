package de.butterfly.butterflylibrary.player

import de.butterfly.butterflylibrary.skill.Skill
import de.butterfly.butterflylibrary.util.UUIDSerializer
import kotlinx.serialization.Serializable
import org.bukkit.inventory.ItemStack
import java.util.*




/**
 * Data class representing a player's profile in a game.
 *
 * @property playerId The unique identifier for the player.
 * @property health The current health points of the player.
 * @property level The current level of the player.
 * @property xp The experience points accumulated by the player.
 * @property inventory A list of items currently held by the player.
 * @property skills A list of skills the player possesses.
 */


@Suppress("unused")
data class PlayerData(
    @Serializable(with = UUIDSerializer::class)
    val playerId: UUID,
    var health: Double,
 //   val mana: Double,
    var level: Int,
    var xp: Int,
    var inventory: Map<Int,ItemStack>,
   // val effects: List<String>,
    var skills: List<Skill>
)


