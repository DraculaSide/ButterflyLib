package de.butterfly.butterflylibrary.skill

import de.butterfly.butterflylibrary.player.PlayerData
import org.bukkit.Bukkit
import java.util.*

@Suppress("unused")
enum class SkillLearnCondition {




    LEVEL {
        // Check if the skill can be learned based on level
        override fun canLearn(skill: Skill): Boolean {

            val player = Bukkit.getPlayer(playerId)
            val requiredLevel = skill.requiredLevel // Replace with the appropriate level check logic
            return player!!.level >= requiredLevel

        }
    },

    REWARD {
        // Check if the skill can be learned as a quest reward
        override fun canLearn(skill: Skill): Boolean {
            // Insert quest-specific logic here
            return true  // Assuming the reward condition is met for simplicity
        }
    },


    TRAINING{
         override fun canLearn(skill: Skill): Boolean {
            return true
        }
    };


    abstract fun canLearn(skill: Skill): Boolean

    private val playerProfile: PlayerData? = null
    private val playerId: UUID? = playerProfile?.playerId
}
