package de.butterfly.butterflylibrary.util

import de.butterfly.butterflylibrary.annotation.Ritual
import de.butterfly.butterflylibrary.ritual.IRitual
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Entity
import org.bukkit.entity.Player



/**
 * Utility class for handling rituals.
 */
class RitualUtil {
    /**
     * Companion object containing utility methods and properties for manipulating shapes
     * in a three-dimensional array of characters.
     */
    companion object {
        var LengthZ: Int = 0
        var LengthX: Int = 0

        /**
         * Finds the core position in the provided 3D shape array.
         */
        fun findCore(shape: Array<Array<Array<Char>>>): ShapePos {
            val shapeAnnotation = this::class.annotations.find { it is Ritual } as? Ritual
                ?: throw IllegalStateException("Missing @Ritual annotation on class")

            val coreChar = shapeAnnotation.ingredient
            for (layer in shape.indices) {
                for (z in shape[0].indices) {
                    for (x in shape[0][0].indices) {
                        if (shape[layer][z][x] == coreChar && shape.getOrNull(layer + 1)?.getOrNull(z)?.getOrNull(x) == 'Y') {
                            return ShapePos(x, layer, z)
                        }
                    }
                }
            }
            return ShapePos(0, 0, 0)
        }

        /**
         * Finds the location of moss in the given 3D shape array.
         */
        fun findMossLocation(shape: Array<Array<Array<Char>>>): ShapePos {
            for (layer in shape.indices) {
                for (z in shape[0].indices) {
                    for (x in shape[0][0].indices) {
                        if (shape[layer][z][x] == 'X' && shape.getOrNull(layer + 1)?.getOrNull(z)?.getOrNull(x) == 'Y') {
                            return ShapePos(x, layer, z)
                        }
                    }
                }
            }
            return ShapePos(0, 0, 0)
        }

        /**
         * Scans a 3D shape and checks if it matches the given core block type and specified ingredient materials.
         */
        fun scanShape(
            shape: Array<Array<Array<Char>>>,
            shapePos: ShapePos,
            getShapeIngredients: Map<Char, Material>,
            core: Block
        ) {
            val bottomLayer = -shapePos.layer
            for (layer in shape.indices) {
                for (z in shape[0].indices) {
                    for (x in shape[0][0].indices) {
                        val letter = shape[layer][z][x]
                        if (letter == ' ') continue
                        val expectedMaterial = getShapeIngredients[letter]
                        val relativeBlock = core.getRelative(x - shapePos.x, layer + bottomLayer, z - shapePos.z)
                        if (relativeBlock.type != expectedMaterial) {
                            throw IllegalStateException("Shape does not match. Expected $expectedMaterial but got ${relativeBlock.type} at $x, $z, $layer.")
                        }
                    }
                }
            }
        }

        /**
         * Retrieves the material associated with a specific position in the shape,
         * while considering specific conditions to skip certain checks.
         */
        private fun getMaterialForLetter(
            shape: Array<Array<Array<Char>>>,
            shapePos: ShapePos,
            ritual: IRitual,
            layer: Int,
            x: Int,
            z: Int,
            lineLengthX: Int,
            lineLengthZ: Int
        ): Material? {
            val letter = shape[layer][z + (lineLengthZ / 2)][x + (lineLengthX / 2)]
            if (letter == ' ' || (x + (lineLengthX / 2) == shapePos.x &&
                        z + (lineLengthZ / 2) == shapePos.z &&
                        layer >= shapePos.layer)) {
                return null
            }
            return ritual.getShapeIngredients()[letter]
        }

        /**
         * Performs a scan over the shape's letters in various alignments and positions.
         */
        fun letterScan(
            shape: Array<Array<Array<Char>>>,
            shapePos: ShapePos,
            ritual: IRitual,
            core: Block
        ): Boolean {
            // Check the x-axis alignment
            val lineLengthX = shape[shapePos.layer][shapePos.z].size
            for (x in 0 - (lineLengthX / 2) until (lineLengthX / 2) + 1) {
                val mat = getMaterialForLetter(shape, shapePos, ritual, shapePos.layer, x, shapePos.z, lineLengthX, LengthZ)
                if (mat != null && core.getRelative(x, 0, 0).type != mat) return false
            }

            // Check the z-axis alignment
            val lineLengthZ = shape[shapePos.layer].size
            for (z in 0 - (lineLengthZ / 2) until (lineLengthZ / 2) + 1) {
                val mat = getMaterialForLetter(shape, shapePos, ritual, shapePos.layer, shapePos.x, z, lineLengthX, LengthZ)
                if (mat != null && core.getRelative(0, 0, z).type != mat) return false
            }

            // Check the rest of the layers
            val bottomLayerRelative = -shapePos.layer
            for (layer in shape.indices) {
                for (x in 0 - (lineLengthX / 2) until (lineLengthX / 2) + 1) {
                    for (z in 0 - (lineLengthZ / 2) until (lineLengthZ / 2) + 1) {
                        val mat = getMaterialForLetter(shape, shapePos, ritual, layer, x, z, lineLengthX, LengthZ)
                        if (mat != null && core.getRelative(x, layer + bottomLayerRelative, z).type != mat) return false
                    }
                }
            }

            return true
        }
    }

    fun checkXAxisAlignment(
        shape: Array<Array<Array<Char>>>,
        shapePos: ShapePos,
        ritual: IRitual,
        core: Block
    ): Boolean {
        for (x in shape[shapePos.layer][0].indices) {
            val posX = shapePos.x + x
            val material = ritual.getShapeIngredients()[shape[shapePos.layer][shapePos.z][posX]]
            if (core.getRelative(posX - shapePos.x, 0, 0).type != material) {
                return false
            }
        }
        return true
    }

    fun checkZAxisAlignment(
        shape: Array<Array<Array<Char>>>,
        shapePos: ShapePos,
        ritual: IRitual,
        core: Block
    ): Boolean {
        for (z in shape[shapePos.layer].indices) {
            val posZ = shapePos.z + z
            val material = ritual.getShapeIngredients()[shape[shapePos.layer][posZ][shapePos.x]]
            if (core.getRelative(0, 0, posZ - shapePos.z).type != material) {
                return false
            }
        }
        return true
    }

    fun checkLayers(
        shape: Array<Array<Array<Char>>>,
        shapePos: ShapePos,
        ritual: IRitual,
        core: Block
    ): Boolean {
        for (layer in shape.indices) {
            for (z in shape[layer].indices) {
                for (x in shape[layer][z].indices) {
                    val letter = shape[layer][z][x]
                    if (letter != ' ') {
                        val material = ritual.getShapeIngredients()[letter]
                        if (core.getRelative(x - shapePos.x, layer - shapePos.layer, z - shapePos.z).type != material) {
                            return false
                        }
                    }
                }
            }
        }
        return true
    }

    fun findNearbyEntities(
        location: Location,
        rangeX: Double,
        rangeY: Double,
        rangeZ: Double
    ): Collection<Entity> {
        return location.getNearbyEntities(rangeX, rangeY, rangeZ)
    }

    fun executeRitual(
        activator: Player?,
        evtLocation: Location,
        evtWorld: World?,
        entities: Collection<Entity>,
        ritual: IRitual
    ): Boolean {
        return ritual.execute(activator, evtLocation, evtWorld, entities)
    }
}