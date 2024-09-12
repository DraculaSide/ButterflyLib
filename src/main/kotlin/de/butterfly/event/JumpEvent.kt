package de.butterfly.event

import org.bukkit.Location
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

/**
 * Represents an event where a player jumps on a block at a specific location.
 *
 * @property player the player who performs the jump.
 * @property block the block on which the player jumps.
 * @property location the location where the jump occurs.
 */
class JumpEvent(val player: Player, val block:Block, val location: Location) : Event() {
    override fun getHandlers(): HandlerList {
       return HANDLER;
    }
   // private var player: Player = Player()
    companion object {
        @JvmStatic
        val HANDLER = HandlerList()
       fun getHandlerList(): HandlerList {

           return HANDLER;
       }
    }

}