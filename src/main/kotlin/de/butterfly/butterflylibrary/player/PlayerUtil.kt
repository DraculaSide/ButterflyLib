package de.butterfly.butterflylibrary.player




import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.util.Vector



/**
 * Utility class for various player-related operations and extensions.
 */
    @Suppress("unused")
    class PlayerUtil {

        /**
         * Test integer used within the PlayerUtil class.
         */

        /**
         * Checks if the player is standing in or has their head in water.
         *
         * @return true if the player's feet or head are in water, false otherwise
         */
        private fun Player.isStandingInWater(): Boolean {
            val blockAtFeet = this.location.block
            val blockAtHead = this.location.add(0.0, 1.0, 0.0).block

            return blockAtFeet.type == Material.WATER || blockAtHead.type == Material.WATER
        }

        /**
         * Extension function to check if the player is in lava.
         *
         * @return True if the player is standing in or has their head in lava, false otherwise.
         */
        private fun Player.isStandingInLava(): Boolean {

            val blockAtFeet = this.location.block
            val blockAtHead = this.location.add(0.0, 1.0, 0.0).block

            return blockAtFeet.type == Material.LAVA || blockAtHead.type == Material.LAVA
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
