package de.butterfly.butterflylibrary.blocks

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.block.data.BlockData
import org.bukkit.block.data.Directional
import org.bukkit.block.data.type.Sapling
import org.bukkit.inventory.ItemStack

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
    /**
     * Determines if the Material is an ore block.
     *
     * @return true if the Material is an ore; false otherwise.
     */
    fun Material.isOre(): Boolean {
        return when (this) {
            Material.COAL_ORE,
            Material.COPPER_ORE,
            Material.DEEPSLATE_COAL_ORE,
            Material.DEEPSLATE_COPPER_ORE,
            Material.DEEPSLATE_DIAMOND_ORE,
            Material.DEEPSLATE_EMERALD_ORE,
            Material.DEEPSLATE_GOLD_ORE,
            Material.DEEPSLATE_IRON_ORE,
            Material.DEEPSLATE_LAPIS_ORE,
            Material.DEEPSLATE_REDSTONE_ORE,
            Material.DIAMOND_ORE,
            Material.EMERALD_ORE,
            Material.GOLD_ORE,
            Material.IRON_ORE,
            Material.LAPIS_ORE,
            Material.NETHER_GOLD_ORE,
            Material.NETHER_QUARTZ_ORE,
            Material.REDSTONE_ORE -> true
            else -> false
        }
    }
    /**
     * Checks if the current material is a type of sapling.
     *
     * @return true if the material is a sapling, false otherwise.
     */
    fun Material.isSapling(): Boolean {
       return when (this) {
            Material.ACACIA_SAPLING,
            Material.BIRCH_SAPLING,
            Material.DARK_OAK_SAPLING,
            Material.JUNGLE_SAPLING,
            Material.OAK_SAPLING,
            Material.SPRUCE_SAPLING ->  true
            else -> false
        }
    }
    /**
     * Checks whether the material is a type of log.
     *
     * @return true if the material is one of the predefined log types, false otherwise.
     */
    fun Material.isLog(): Boolean {
        return when (this) {
            Material.ACACIA_LOG,
            Material.BIRCH_LOG,
            Material.DARK_OAK_LOG,
            Material.JUNGLE_LOG,
            Material.OAK_LOG,
            Material.SPRUCE_LOG ->  true
            else -> false
        }
    }
    /**
     * Retrieves the ItemStack that will be dropped when the specified ore material is mined.
     *
     * @param material the ore material to retrieve the drop for
     * @return the ItemStack that will be dropped or null if the material is not a recognized ore
     */
    fun getDropForOre(material: Material): ItemStack? {
        return when (material) {
            Material.COAL_ORE, Material.DEEPSLATE_COAL_ORE -> ItemStack(Material.COAL)
            Material.COPPER_ORE, Material.DEEPSLATE_COPPER_ORE -> ItemStack(Material.RAW_COPPER)
            Material.DIAMOND_ORE, Material.DEEPSLATE_DIAMOND_ORE -> ItemStack(Material.DIAMOND)
            Material.EMERALD_ORE, Material.DEEPSLATE_EMERALD_ORE -> ItemStack(Material.EMERALD)
            Material.GOLD_ORE, Material.DEEPSLATE_GOLD_ORE -> ItemStack(Material.RAW_GOLD)
            Material.IRON_ORE, Material.DEEPSLATE_IRON_ORE -> ItemStack(Material.RAW_IRON)
            Material.LAPIS_ORE, Material.DEEPSLATE_LAPIS_ORE -> ItemStack(Material.LAPIS_LAZULI, (4..8).random())
            Material.REDSTONE_ORE, Material.DEEPSLATE_REDSTONE_ORE -> ItemStack(Material.REDSTONE, (4..5).random())
            Material.NETHER_GOLD_ORE -> ItemStack(Material.GOLD_NUGGET, (2..6).random())
            Material.NETHER_QUARTZ_ORE -> ItemStack(Material.QUARTZ)
            else -> null
        }
}
    /**
     * Transforms a given log block into its corresponding sapling.
     *
     * @param block The block to be transformed. The method will check the type of this block
     * and transform it into the respective sapling type if it is a log.
     */
    fun getSaplingForWood(block: Block) {
        when (block.type) {
            Material.OAK_LOG -> return block.setType(Material.OAK_SAPLING)
            Material.SPRUCE_LOG -> return block.setType(Material.SPRUCE_SAPLING)
            Material.BIRCH_LOG -> return block.setType(Material.BIRCH_SAPLING)
            Material.JUNGLE_LOG -> return block.setType(Material.JUNGLE_SAPLING)
            Material.ACACIA_LOG -> return block.setType(Material.ACACIA_SAPLING)
            Material.DARK_OAK_LOG -> return block.setType(Material.DARK_OAK_SAPLING)
            Material.WARPED_STEM -> return block.setType(Material.WARPED_WART_BLOCK)
            Material.CRIMSON_STEM -> return block.setType(Material.NETHER_WART_BLOCK)
            else -> return
        }
    }

}