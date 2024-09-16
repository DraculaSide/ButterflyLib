package de.butterfly.butterflyapi.ritual

import de.butterfly.util.ShapePos
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
@Suppress("unused")
interface IRitual {
    fun getName(): String?

    fun getShapeIngredients(): Map<Char?, Material?>?

    // Has to be square
    fun getShape(): Array<Array<Array<Char>>>

    fun execute(ritualActivator: Player?, pos: Location?, world: World?, entities: Collection<Entity?>?): Boolean

    fun getPermission(): String?

    fun help(): String?

    // Find where the moss block is in the rituals shape
    fun findMossLocation(): ShapePos? {
        val shape = this.getShape()
        shape.forEachIndexed { layer, layerArray ->
            layerArray.forEachIndexed { z, row ->
                row.forEachIndexed { x, char ->
                    if (char == 'X' && layer + 1 < shape.size && shape[layer + 1][z][x] == 'Y') {
                        return ShapePos(x, z, layer)
                    }
                }
            }
        }
        return null
    }
}