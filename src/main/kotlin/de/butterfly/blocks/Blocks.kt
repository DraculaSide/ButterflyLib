package de.butterfly.butterflyApi.blocks

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.data.Directional
import org.bukkit.block.data.type.Sapling
import org.bukkit.entity.Player

class Blocks {

    /**
     * Converts the given block to a specified material while preserving directional data.
     *
     * @param block the block to be converted
     * @param player the player performing the conversion
     * @param material the new material for the block
     */
     fun convertToBlocks(block: Block, player: Player, material: Material) {
        // Retrieve the current block data
        val originalData = block.blockData

        // Ensure that block data is directional
        if (originalData is Directional) {
            // Remove the original block and set the new block type
            block.type = material

            // Get the new block data and set the facing direction
            val newData = block.blockData
            if (newData is Directional) {
                newData.facing = originalData.facing
                block.blockData = newData
            }
        }
    }

    fun spawnSapling(location: Location, type: Material) {
        val world = location.world
        val sapling = world?.getBlockAt(location)?.blockData as? Sapling
        if (sapling != null) {
            world.setBlockData(location, sapling)
        }
    }
}