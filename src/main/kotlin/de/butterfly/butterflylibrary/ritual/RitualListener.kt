package de.butterfly.butterflylibrary.ritual

import de.butterfly.butterflylibrary.ritual.IRitual
import de.butterfly.butterflylibrary.ritual.RitualType
import de.butterfly.butterflylibrary.util.ShapePos
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.data.type.Sapling
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockIgniteEvent
import org.bukkit.event.entity.EntityExplodeEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.world.StructureGrowEvent
import org.bukkit.plugin.Plugin

class RitualListener() : Listener {
    private val instance = RitualManager.instance

    @EventHandler(priority = EventPriority.MONITOR)
    fun onPlayerDropItem(event: PlayerDropItemEvent) {
        val player = event.player
        handleRitual(player, RitualType.DROP)
    }

    @EventHandler(priority = EventPriority.MONITOR)
    fun onBlockIgnite(event: BlockIgniteEvent) {
        val player = event.player ?: return
        handleRitual(player, RitualType.IGNITE)
    }

    @EventHandler(priority = EventPriority.MONITOR)
    fun onTreeGrow(event: StructureGrowEvent) {
        val player = event.player ?: return
        val block = event.blocks.firstOrNull()?.block ?: return

        // Check if the block beneath the sapling is a Moss Block
        val mossBlock = block.getRelative(0, -1, 0)
        if (mossBlock.type != Material.MOSS_BLOCK) return

        // Make sure it was a sapling that grew
        if (block.blockData !is Sapling) return

        handleRitual(player, RitualType.GROW_TREE)
    }



    private fun handleRitual(player: Player, ritualType: RitualType) {
        var foundRitual = false

        for (ritual in instance) {
            if (!player.hasPermission("redsorcery.rituals.${ritual.getPermission()}")) continue

            val shape = ritual.getShape()
            val shapePos = ritual.findMossLocation() ?: continue

            if (!checkAlignment(player.location.block, ritual, shape, shapePos)) continue
            if (!checkRitualStructure(player.location.block, ritual, shape, shapePos)) continue

            foundRitual = true

            val entities: Collection<Entity> = player.location.getNearbyEntities(5.0, 5.0, 5.0)
            val success = ritual.execute(player, player.location, player.world, entities)
            player.sendMessage(
                if (success)
                    Component.text("Ritual executed successfully!")
                else
                    Component.text("Ritual failed to execute.")
            )
        }

        if (!foundRitual) {
            player.sendMessage(Component.text("No ritual found, please check the structure and try again"))
        }
    }

    private fun checkAlignment(moss: Block, ritual: IRitual, shape: Array<Array<Array<Char>>>, shapePos: ShapePos): Boolean {
        val layer = shape[shapePos.LAYER]
        val z = shapePos.Z
        val lineLengthX = layer[z].size

        for (x in -(lineLengthX / 2) until (lineLengthX / 2) + 1) {
            val letter = layer[z][x + (lineLengthX / 2)]
            if (letter == ' ' || x == 0) continue
            val mat = ritual.getShapeIngredients()[letter] ?: continue
            if (moss.getRelative(x, 0, 0).type != mat) return false
        }

        val lineLengthZ = layer.size
        for (z in -(lineLengthZ / 2) until (lineLengthZ / 2) + 1) {
            val letter = layer[z + (lineLengthZ / 2)][shapePos.X]
            if (letter == ' ' || z == 0) continue
            val mat = ritual.getShapeIngredients()[letter] ?: continue
            if (moss.getRelative(0, 0, z).type != mat) return false
        }

        return true
    }

    private fun checkRitualStructure(moss: Block, ritual: IRitual, shape: Array<Array<Array<Char>>>, shapePos: ShapePos): Boolean {
        val bottomLayerRelative = -shapePos.LAYER
        for (layer in shape.indices) {
            val layerContent = shape[layer]
            for (x in -(layerContent[shapePos.Z].size / 2) until (layerContent[shapePos.Z].size / 2) + 1) {
                for (z in -(layerContent.size / 2) until (layerContent.size / 2) + 1) {
                    val letter = layerContent[z + (layerContent.size / 2)][x + (layerContent[shapePos.Z].size / 2)]
                    if (letter == ' ') continue
                    if (x + (layerContent[shapePos.Z].size / 2) == shapePos.X &&
                        z + (layerContent.size / 2) == shapePos.Z &&
                        layer >= shapePos.LAYER
                    ) continue
                    val mat = ritual.getShapeIngredients()[letter] ?: continue
                    if (moss.getRelative(x, layer + bottomLayerRelative, z).type != mat) return false
                }
            }
        }
        return true
    }
}