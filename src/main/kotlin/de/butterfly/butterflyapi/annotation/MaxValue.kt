package de.butterfly.butterflyapi.annotation
@Target( AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class MaxValue(val max: Int)
