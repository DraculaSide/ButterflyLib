package de.butterfly.butterflylibrary.annotation
@Target( AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class MaxValue(val max: Int)
