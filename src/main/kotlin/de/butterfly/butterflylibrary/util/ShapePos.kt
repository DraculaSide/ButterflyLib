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
data class ShapePos(var X: Int = 0, var Z: Int = 0, var LAYER: Int = 0) {

    /**
     * Checks if all the properties of the ShapePos instance are zero.
     *
     * @return true if X, Z, and LAYER are all zero; false otherwise.
     */
    fun isZero(): Boolean {
        return X == 0 && Z == 0 && LAYER == 0
    }
}