package de.butterfly.butterflylibrary.util

import de.butterfly.butterflylibrary.annotation.MaxValue
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

@Suppress("unused")
object AnnotationValidator {
    fun validateMaxValue(obj: Any):List<String> {
        val errors = mutableListOf<String>()
        for(property in obj::class.memberProperties){
            val maxValue = property.findAnnotation<MaxValue>()

            if(maxValue != null){
                val value = property.getter.call(obj)
                if (value is Int && value > maxValue.max){
                    errors.add("Property ${property.name} exceeds maximum value of ${maxValue.max}")
                }
            }
        }
        return errors
    }
}