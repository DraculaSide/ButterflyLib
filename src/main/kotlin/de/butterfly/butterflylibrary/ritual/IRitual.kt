package de.butterfly.butterflylibrary.ritual

import de.butterfly.butterflylibrary.util.ShapePos
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.block.BlockType
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.Event

/**
 * Interface representing a ritual with various attributes and behaviors.
 * original Author @author SarahGreyWolf
 */
@Suppress("unused")
interface IRitual {
    /**
     * Retrieves the name of the ritual.
     *
     * @return The name of the ritual as a String, or null if the name is not set.
     */
    fun getName(): String?

    /**
     * Retrieves the ingredients required for the shape of the ritual.
     *
     * @return a map where each key represents a character in the ritual shape and each value represents the corresponding material needed.
     *         Returns null if no ingredients are required or defined.
     */
    @Suppress("unstable_collections")
    fun getShapeIngredients(): Map<Char?, Material>

    /**
     * Retrieve a 3D array representing the shape of the ritual.
     * The shape is always square.
     *
     * @return A 3D array of characters where each layer's rows are square.
     */
// Has to be square
    fun getShape(): Array<Array<Array<Char>>>

    /**
     * Executes the ritual at the given location in the specified world with the provided entities.
     *
     * @param ritualActivator The player who activates the ritual. Can be null.
     * @param pos The location where the ritual is performed. Can be null.
     * @param world The world in which the ritual is performed. Can be null.
     * @param entities A collection of entities involved in the ritual. Can be null.
     * @return True if the ritual execution was successful, false otherwise.
     */
    fun execute(ritualActivator: Player?, pos: Location?, world: World?, entities: Collection<Entity?>?): Boolean

    /**
     * Retrieves the permission associated with this ritual.
     *
     * @return a string representing the required permission, or null if no permission is needed.
     */
    fun getPermission(): String?

    /**
     * Provides a description or information about the ritual.
     *
     * @return A string containing the help information, or null if no information is available.
     */
    fun help(): String?

    /**
     * Finds and returns the location of the moss block ('X') in the ritual's shape.
     * The moss block is determined to be at the location where it sits directly
     * above another block marked as 'Y'.
     *
     * @return `ShapePos?` indicating the position of the moss block in the ritual's shape, or null if no such block is found.
     */
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
    /**
     * Finds and returns the location of a specific block within the ritual's shape,
     * where the block is identified by the character `blockChar` with another block
     * directly above it identified by the character `blockCharUp`.
     *
     * @param blockChar The character representing the block to locate.
     * @param blockCharUp The character representing the block that should be directly above `blockChar`.
     * @return `ShapePos?` indicating the position of the specified block in the ritual's shape, or null if no such block is found.
     */
    fun findBlockLocation(blockChar: Char, blockCharUp:Char): ShapePos? {
        val shape = this.getShape()
        shape.forEachIndexed { layer, layerArray ->
            layerArray.forEachIndexed { z, row ->
                row.forEachIndexed { x, char ->
                    if (char == blockChar&& layer + 1 < shape.size && shape[layer + 1][z][x] == blockCharUp) {
                        return ShapePos(x, z, layer)
                    }
                }
            }
        }
        return null
    }

    fun setRitualType(ritualType: RitualType) {
       lateinit var listener:IRitualListener
       var event:Event
        when(ritualType){
            RitualType.DROP -> listener.onPlayerDropItem(event)//link to the ritual
            RitualType.IGNITE -> listener.onBlockIgnite(event)
            RitualType.GROW_TREE -> listener.onTreeGrow(event)
        }

    }
}
