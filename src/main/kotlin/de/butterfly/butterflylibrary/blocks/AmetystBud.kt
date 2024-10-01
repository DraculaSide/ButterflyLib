package de.butterfly.butterflylibrary.blocks

import org.bukkit.block.data.BlockData
import org.bukkit.block.data.Directional
import org.bukkit.block.data.Waterlogged

interface AmethystBud:BlockData, Waterlogged, Directional {
    fun getStage(): Int

    fun setStage(var1: Int)

    fun getMaximumStage(): Int

}