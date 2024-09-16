package de.butterfly.butterflyapi.menumanager

import org.bukkit.entity.Player
import java.util.*



class PlayerMenuUtility(player: Player) {
    /**
     * Gets the player who owns the menu.
     *
     * @return the player who owns the menu
     */
    /**
     * Utility class for managing player menus.
     */
    val owner: Player = player

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != javaClass) return false
        val that = other as PlayerMenuUtility
        return owner == that.owner
    }

    override fun hashCode(): Int {
        return Objects.hash(owner)
    }

    override fun toString(): String {
        return "PlayerMenuUtility{" +
                "owner=" + owner +
                '}'
    }
}
