package de.butterfly.butterflyapi.player




import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.util.Vector



    /**
     * Utility class for player events.
     */
    @Suppress("unused")
    class PlayerEventUtil {

        val test: Int = 0
        /**
         * Checks if the player is in water.
         *
         * @param player the player to check
         * @return true if the player is in water, false otherwise
         */
        private fun Player.isInWater(player: Player): Boolean {
            val blockAtFeet = player.location.block
            val blockAtHead = player.location.add(0.0, 1.0, 0.0).block

            return blockAtFeet.type == Material.WATER || blockAtHead.type == Material.WATER
        }

        /**
         * Checks if the player is in lava.
         *
         * @param player the player to check
         * @return true if the player is in lava, false otherwise
         */
        private fun Player.isInLava(player: Player): Boolean {
            val blockAtFeet = player.location.block
            val blockAtHead = player.location.add(0.0, 1.0, 0.0).block

            return blockAtFeet.type == Material.LAVA || blockAtHead.type == Material.LAVA
        }


        companion object {
            /**
             * Adjusts the player's velocity by a given factor.
             *
             * @param oldVelocity the player's original velocity
             * @param factor the factor by which to multiply the original velocity
             * @param player the player whose velocity is to be adjusted
             */
            fun adjustPlayerVelocity(oldVelocity: Vector, factor: Double, player: Player) {
                val newVelocity = oldVelocity.multiply(factor)
                player.velocity = newVelocity
            }
        }


        /**
         * Extension function to check if the player is in lava.
         */
        fun Player.isInLava(): Boolean {
            return isInLava(this)
        }
    }
