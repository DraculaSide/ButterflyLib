package de.butterfly.butterflyApi.menumanager

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
    val owner: Player

    /**
     * Constructor to create a PlayerMenuUtility for a specific player.
     *
     * @param player the player who owns the menu, must not be null
     * @throws IllegalArgumentException if the player is null
     */
    init {
        requireNotNull(player) { "Player cannot be null" }
        this.owner = player
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != javaClass) return false
        val that = o as PlayerMenuUtility
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
