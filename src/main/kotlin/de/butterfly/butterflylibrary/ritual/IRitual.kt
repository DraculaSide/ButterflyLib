package de.butterfly.butterflylibrary.ritual

import de.butterfly.butterflylibrary.annotation.Ritual
import de.butterfly.butterflylibrary.blocks.AmethystBud
import de.butterfly.butterflylibrary.util.RitualUtil
import de.butterfly.butterflylibrary.util.ShapePos
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.block.data.type.AmethystCluster
import org.bukkit.block.data.type.Sapling
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.world.StructureGrowEvent

/**
 * Interface representing a ritual with various attributes and behaviors.
 * original Author @author SarahGreyWolf
 */

open class IRitual: Listener {

    fun getName(): String {return " "}

    fun getShapeIngredients(): Map<Char, Material> { return mapOf()}


    fun getShape(): Array<Array<Array<Char>>> {return arrayOf()}


    fun execute(ritualActivator: Player?, pos: Location?, world: World?, entities: Collection<Entity?>?): Boolean {
        return false
    }


    fun help(): String { return ""}

    private fun getRitualType(){
    val annotation = this::class.annotations.find { it is Ritual } as? Ritual
        ?: throw IllegalStateException("Ritual annotation is missing on class ${this::class}")
            val type = annotation.ritualType
            when (type) {
                RitualType.TREE_RITUAL -> {
                    @EventHandler(priority = EventPriority.MONITOR)
                    fun onTreeGrow(evt: StructureGrowEvent) {
                        if (evt.player == null) return
                        val activator = evt.player
                        val block = evt.blocks[0].block

                        // Check if the block beneath the sapling is a Moss Block
                        val moss = block.getRelative(0, -1, 0)
                        if (moss.type != Material.MOSS_BLOCK) return

                        // Make sure it was a sapling that grew
                        if (block.blockData !is Sapling) return

                        var foundRitual = false

                        // Try to find the correct ritual
                        ritual@ for (ritual in RitualManager.ritualLs) {
                            // Check if the activator has necessary permissions

                            /** Start of duplication **/

                            val shape = ritual.getShape()
                            val shapePos = RitualUtil.findMossLocation(shape)

                            // Check the x-axis alignment
                            val lineLengthX = shape[shapePos.layer][shapePos.z].size
                            for (x in 0 - (lineLengthX / 2) until (lineLengthX / 2) + 1) {
                                val letter = shape[shapePos.layer][shapePos.z][x + (lineLengthX / 2)]
                                if (letter == ' ' || x == 0) continue
                                val mat = ritual.getShapeIngredients()[letter]
                                if (moss.getRelative(x, 0, 0).type != mat) continue@ritual
                            }

                            // Check the z-axis alignment
                            val lineLengthZ = shape[shapePos.layer].size
                            for (z in 0 - (lineLengthZ / 2) until (lineLengthZ / 2) + 1) {
                                val letter = shape[shapePos.layer][z + (lineLengthZ / 2)][shapePos.x]
                                if (letter == ' ' || z == 0) continue
                                val mat = ritual.getShapeIngredients()[letter]
                                if (moss.getRelative(0, 0, z).type != mat) continue@ritual
                            }

                            // Check the rest of the layers
                            val bottomLayerRelative = -shapePos.layer
                            for (layer in shape.indices) {
                                for (x in 0 - (lineLengthX / 2) until (lineLengthX / 2) + 1) {
                                    for (z in 0 - (lineLengthZ / 2) until (lineLengthZ / 2) + 1) {
                                        val letter = shape[layer][z + (lineLengthZ / 2)][x + (lineLengthX / 2)]
                                        if (letter == ' ' || (x + (lineLengthX / 2) == shapePos.x && z + (lineLengthZ / 2) == shapePos.z && layer >= shapePos.layer)) continue
                                        val mat = ritual.getShapeIngredients()[letter]
                                        if (moss.getRelative(
                                                x,
                                                layer + bottomLayerRelative,
                                                z
                                            ).type != mat
                                        ) continue@ritual
                                    }
                                }
                            }

                            foundRitual = true

                            val entities: Collection<Entity> = evt.location.getNearbyEntities(
                                (lineLengthX / 2) + 1.0,
                                (shape.size / 2) + 1.0,
                                (lineLengthZ / 2) + 1.0
                            )
                            val success = ritual.execute(activator, evt.location, evt.world, entities)
                            evt.isCancelled = !success

                            /** End of duplication **/
                        }

                        if (!foundRitual) {

                        }
                    }
                }
                RitualType.WATER_RITUAL -> TODO()
                RitualType.IGNITE_RITUAL -> {
                    @EventHandler(priority = EventPriority.MONITOR)
                     fun onTreeGrow(event : StructureGrowEvent) {}
                }
                RitualType.EARTH_RITUAL -> {
                    @EventHandler(priority = EventPriority.MONITOR)
                    fun onAmethystGrow(event: StructureGrowEvent) {

                        val shape: Array<Array<Array<Char>>> = getShape()
                        val shapePos = RitualUtil.findMossLocation(shape)

                        // NullCheck Player
                        if (event.player == null) return
                        val activator = event.player
                        // val for Mossblock
                        val block = event.blocks[0].block
                        val amethyst = block.getRelative(0, -1, 0)

                        if (amethyst.type != Material.BUDDING_AMETHYST) return
                        // check if block Amethyst Bud
                        if (block.blockData !is AmethystBud) return
                        // flagbool for Ritual
                        var foundRitual = false
                        RitualUtil.scanShape(shape, shapePos, getShapeIngredients(), block)
                    }
                }
                RitualType.AIR_RITUAL -> TODO()
                RitualType.LIGHT_RITUAL -> TODO()
                RitualType.DARKNESS_RITUAL -> TODO()
                RitualType.DROP_RITUAL -> TODO()
                RitualType.FIRE_RITUAL -> TODO()
                RitualType.LAVA_RITUAL -> TODO()
            }

}


}
