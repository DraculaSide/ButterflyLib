package de.butterfly.butterflyapi.util

data class ShapePos(var X: Int = 0, var Z: Int = 0, var LAYER: Int = 0) {

    fun isZero(): Boolean {
        return X == 0 && Z == 0 && LAYER == 0
    }
}