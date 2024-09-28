package de.butterfly.butterflylibrary.util

/**
 * Represents a position in a 3D space, with X, Z coordinates and a layer.
 *
 * @property X The X coordinate.
 * @property Z The Z coordinate.
 * @property LAYER The layer which denotes the height level.
 * original Author @author SarahGreyWolf
 */
@Suppress("unused")
data class ShapePos(var x: Int = 0, var z: Int = 0, var layer: Int = 0) {

    /**
     * Checks if all the properties of the ShapePos instance are zero.
     *
     * @return true if X, Z, and LAYER are all zero; false otherwise.
     */
    fun isZero(): Boolean {
        return x == 0 && z == 0 && layer == 0
    }
}