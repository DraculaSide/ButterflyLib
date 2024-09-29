package de.butterfly.butterflylibrary.ritual

import de.butterfly.butterflylibrary.annotation.Ritual
import de.butterfly.butterflylibrary.util.RitualUtil
import de.butterfly.butterflylibrary.util.ShapePos
import org.bukkit.Material
import org.bukkit.Registry.MATERIAL
import org.bukkit.block.data.type.Sapling
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.world.StructureGrowEvent


interface TreeRitual: IRitual, Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    private fun onTreeGrow(event : StructureGrowEvent) {

        val shape : Array<Array<Array<Char>>> = getShape()
        val shapePos = RitualUtil.findMossLocation(shape)

        // NullCheck Player
if (event.player == null)return
        val activator = event.player
 // val for Mossblock
        val block = event.blocks[0].block
        val moss = block.getRelative(0,-1,0)

        if (moss.type != Material.MOSS_BLOCK)return
        //check if block sapling
        if (block.blockData !is Sapling ) return
        // flagbool for Ritual
        var foundRitual = false
 RitualUtil.scanShape(shape,shapePos,getShapeIngredients(),block)


    }
}