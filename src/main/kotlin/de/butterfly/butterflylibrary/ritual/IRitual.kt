package de.butterfly.butterflylibrary.ritual

import de.butterfly.butterflylibrary.util.ShapePos
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.entity.Entity
import org.bukkit.entity.Player

/**
 * Interface representing a ritual with various attributes and behaviors.
 * original Author @author SarahGreyWolf
 */

interface IRitual {

    fun getName(): String

    fun getShapeIngredients(): Map<Char, Material>


    fun getShape(): Array<Array<Array<Char>>>


    fun execute(ritualActivator: Player?, pos: Location?, world: World?, entities: Collection<Entity?>?): Boolean




    fun help(): String




}
