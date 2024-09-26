package de.butterfly.butterflylibrary.player




import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.util.Vector
import java.util.*


/**
 * Utility class for various player-related operations and extensions.
 */
    @Suppress("unused")
    class PlayerUtil {

        /**
         * Test integer used within the PlayerUtil class.
         */
        val test: Int = 0
        /**
         * Checks if the player is standing in or has their head in water.
         *
         * @return true if the player's feet or head are in water, false otherwise
         */
        private fun Player.isInWater(): Boolean {
            val blockAtFeet = this.location.block
            val blockAtHead = this.location.add(0.0, 1.0, 0.0).block

            return blockAtFeet.type == Material.WATER || blockAtHead.type == Material.WATER
        }

        /**
         * Extension function to check if the player is in lava.
         *
         * @return True if the player is standing in or has their head in lava, false otherwise.
         */
        private fun Player.isInLava(): Boolean {

            val blockAtFeet = this.location.block
            val blockAtHead = this.location.add(0.0, 1.0, 0.0).block

            return blockAtFeet.type == Material.LAVA || blockAtHead.type == Material.LAVA
        }
       /**
        * Retrieves the name of the player associated with the provided UUID.
        *
        * @param uuid The UUID of the player whose name is to be retrieved.
        * @return The name of the player associated with the given UUID, or "null" if the player is not found.
        */
       private fun Player.getNameByUUID(uuid: UUID): String {
           val player = Bukkit.getPlayer(uuid)
           return player!!.name
       }
     private fun Player.getUUIDByName(name: String): UUID {
         val player = Bukkit.getPlayer(name)
         return player!!.uniqueId
     }

        /**
         * Companion object containing utility functions related to the Player class.
         */
        companion object {
            /**
             * Adjusts the player's velocity by multiplying the current velocity with the specified factor.
             *
             * @param oldVelocity The current velocity of the player.
             * @param factor The factor by which to adjust the velocity.
             */
            fun Player.adjustVelocity(oldVelocity: Vector, factor: Double) {

                val newVelocity = oldVelocity.multiply(factor)
                this.velocity = newVelocity
            }
        }



    }
