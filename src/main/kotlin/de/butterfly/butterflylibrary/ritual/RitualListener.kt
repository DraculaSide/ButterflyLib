package dev.sarahgreywolf.redsorcery.listeners


import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.block.data.type.Sapling
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.world.StructureGrowEvent

class RitualListener : Listener {
  /*  @EventHandler(priority = EventPriority.MONITOR)
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
        ritual@ for (ritual in plugin.getRituals()) {
            // No point checking for that ritual if they don't have permission to use it
            // anyway, this may get moved so we can give permission errors
            if (!activator!!.hasPermission("redsorcery.rituals." + ritual.getPermission())) continue

            val shape: Array<Array<CharArray>> = ritual.getShape()
            val shapePos = ritual.findMossLocation() ?: continue

            // Check if the blocks on the x and z axis with the moss block align
            // (Quick exit to avoid checking the whole ritual if these parts aren't correct)
            val lineLengthX = shape[shapePos.layer][shapePos.z].size
            for (x in 0 - (lineLengthX / 2) until (lineLengthX / 2) + 1) {
                val letter = shape[shapePos.layer][shapePos.z][x + (lineLengthX / 2)]
                if (letter == ' ' || x == 0) continue
                val mat = ritual.getShapeIngredients()[letter]
                if (moss.getRelative(x, 0, 0).type != mat) continue@ritual
            }
            val lineLengthZ = shape[shapePos.layer].size
            for (z in 0 - (lineLengthZ / 2) until (lineLengthZ / 2) + 1) {
                val letter = shape[shapePos.layer][z + (lineLengthZ / 2)][shapePos.x]
                if (letter == ' ' || z == 0) continue
                val mat = ritual.getShapeIngredients()[letter]
                if (moss.getRelative(0, 0, z).type != mat) continue@ritual
            }
            val bottomLayerRelative = -shapePos.layer
            for (layer in shape.indices) {
                for (x in 0 - (lineLengthX / 2) until (lineLengthX / 2) + 1) {
                    for (z in 0 - (lineLengthZ / 2) until (lineLengthZ / 2) + 1) {
                        val letter = shape[layer][z + (lineLengthZ / 2)][x + (lineLengthX / 2)]
                        if (letter == ' '
                            || (x + (lineLengthX / 2) == shapePos.x && z + (lineLengthZ / 2) == shapePos.z && layer >= shapePos.layer)
                        ) continue
                        val mat = ritual.getShapeIngredients()[letter]
                        if (moss.getRelative(x, layer + bottomLayerRelative, z).type != mat) continue@ritual
                    }
                }
            }
            foundRitual = true

            val entities = evt.location.getNearbyEntities(
                ((lineLengthX / 2) + 1).toDouble(),
                ((shape.size / 2) + 1).toDouble(),
                ((lineLengthZ / 2) + 1).toDouble()
            )
            val success = ritual.execute(activator, evt.location, evt.world, entities)
            evt.isCancelled = !success
        }

        if (!foundRitual) activator.sendMessage(
            Component.text(RedSorcery.prefix + " No ritual found, please check the structure and try again")
        )
    }

    companion object {
        private val plugin: RedSorcery = RedSorcery.plugin
    }

   */
}