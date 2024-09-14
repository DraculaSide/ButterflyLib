package de.butterfly.blocks

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.data.BlockData
import org.bukkit.block.data.Directional
import org.bukkit.block.data.type.Sapling

@Suppress("unused")
class Blocks {

    /**
     * Converts the given block to a specified material while preserving directional data.
     *
     * @param block the block to be converted
     * @param material the new material for the block
     */
     fun convertToBlocks(block: Block,  material: Material) {
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

    /**
     * Spawns a sapling of the specified type at the given location.
     *
     * @param location The location where the sapling should be spawned.
     */
    fun spawnSapling(location: Location) {
        val world = location.world
        val sapling = world?.getBlockAt(location)?.blockData as? Sapling
        if (sapling != null) {
            world.setBlockData(location, sapling)
        }
    }
    /**
     * Checks if a block matches the given type and updates its state if matched.
     *
     * @param block the block to check and update
     * @param checkType the type to check for
     * @param newState the new state to set if the block matches
     */
    fun checkAndUpdateBlockState(block: Block, checkType: Material, newState: BlockData) {
        if (block.type == checkType) {
            block.blockData = newState
        }
    }
}