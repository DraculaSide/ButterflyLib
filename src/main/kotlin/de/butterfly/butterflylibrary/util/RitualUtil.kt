package de.butterfly.butterflylibrary.util

import de.butterfly.butterflylibrary.annotation.Ritual
import org.bukkit.Material
import org.bukkit.block.Block

class RitualUtil {
    companion object {



        fun findCore(shape: Array<Array<Array<Char>>>): ShapePos {
            val annotation = this::class.annotations.find { it is Ritual } as? Ritual
            ?: throw IllegalStateException("Ritual annotation is missing on class ${this::class.simpleName}")
            val ingredient = annotation.ingredient
            for (layer in shape.indices) {
                for (z in shape[layer].indices) {
                    for (x in shape[layer][z].indices) {
                        if (shape[layer][z][x] == ingredient && layer + 1 < shape.size && shape[layer + 1][z][x] == 'Y') {
                            return ShapePos(x, z, layer)
                        }
                    }
                }
            }
            return ShapePos()
        }
        fun findMossLocation(shape: Array<Array<Array<Char>>>): ShapePos {
            for (layer in shape.indices) {
                for (z in shape[layer].indices) {
                    for (x in shape[layer][z].indices) {
                        if (shape[layer][z][x] == 'X' && layer + 1 < shape.size && shape[layer + 1][z][x] == 'Y') {
                            return ShapePos(x, z, layer)
                        }
                    }
                }
            }
            return ShapePos()
        }

        fun scanShape(
            shape: Array<Array<Array<Char>>>,
            shapePos: ShapePos,
            getShapeIngredients: Map<Char, Material>,
            core: Block
        ) {
            val lineLengthX = shape[shapePos.layer][shapePos.z].size
            for (x in 0 - (lineLengthX / 2) until (lineLengthX / 2) + 1) {
                val letter = shape[shapePos.layer][shapePos.z][x + (lineLengthX / 2)]
                if (letter == ' ' || x == 0) continue
                val mat = getShapeIngredients[letter]
                if (core.getRelative(x, 0, 0).type != mat) continue

            }
            val lineLengthZ = shape[shapePos.layer].size
            for (z in 0 - (lineLengthZ / 2) until (lineLengthZ / 2) + 1) {
                val letter = shape[shapePos.layer][z + (lineLengthZ / 2)][shapePos.x]
                if (letter == ' ' || z == 0) continue
                val mat = getShapeIngredients[letter]
                if (core.getRelative(0, 0, z).type != mat) return

            }
            val bottomLayerRelative = -shapePos.layer
            for (Layer in shape.indices) {
                for (x in 0 - (lineLengthX / 2) until (lineLengthX / 2) + 1) {
                    for (z in 0 - (lineLengthZ / 2) until (lineLengthZ / 2) + 1) {
                        val letter = shape[Layer][z + (lineLengthZ / 2)][x + (lineLengthX / 2)]
                        if (letter == ' ' || (x + (lineLengthX / 2) == shapePos.x && z + (lineLengthZ / 2) == shapePos.z && Layer >= shapePos.layer)) continue
                        val mat = getShapeIngredients[letter]
                        if (core.getRelative(x, Layer + bottomLayerRelative, z).type != mat) continue
                    }
                }
            }
        }
    }
}
