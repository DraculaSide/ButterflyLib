package de.butterfly.butterflylibrary.ritual

import de.butterfly.butterflylibrary.annotation.Ritual
import de.butterfly.butterflylibrary.util.ShapePos
import org.bukkit.Material
import org.bukkit.block.data.type.Sapling
import org.bukkit.event.Event
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockIgniteEvent

interface IgniteRitual: IRitual ,Listener{

    private fun findCoreLocation(): ShapePos? {
        val shape: Array<Array<Array<Char>>> = this.getShape()
        var shapePos: ShapePos? = null

        // Retrieve the Ritual annotation
        val annotation = this::class.annotations.find { it is Ritual } as? Ritual
            ?: throw IllegalStateException("Ritual annotation is missing on class ${this::class.simpleName}")

        val coreBlock: Material = annotation.coreBlock
        val ingredient: Char = annotation.ingredient

        search@ for (layer in shape.indices) {
            for (z in shape[layer].indices) {
                for (x in shape[layer][z].indices) {
                    if (shape[layer][z][x] == ingredient && shape[layer + 1][z][x] == 'Y') {
                        shapePos = ShapePos(x, z, layer)
                        break@search
                    }
                }
            }
        }

        return shapePos
    }
    @EventHandler(priority = EventPriority.MONITOR)
    private fun onIgnite(igniteEvent: BlockIgniteEvent): Event {
        val shape: Array<Array<Array<Char>>> = getShape()
        val shapePos = findCoreLocation()

        // NullCheck Player

        val activator = event.player
        // val for Mossblock
        val block = event.blocks[0].block
        val moss = block.getRelative(0, -1, 0)

    }