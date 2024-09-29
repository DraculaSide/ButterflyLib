package de.butterfly.butterflylibrary.annotation

import org.bukkit.Material
import org.bukkit.block.Block

annotation class Ritual(
    val name: String,
    val coreBlock: Material,
    val ingredient: Char

)
