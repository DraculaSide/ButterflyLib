package de.butterfly.butterflylibrary.annotation

import de.butterfly.butterflylibrary.ritual.RitualType
import org.bukkit.Material
import org.bukkit.block.Block

annotation class Ritual(
    val name: String,
    val coreBlock: Material,
    val ingredient: Char,
    val ritualType: RitualType

    )
