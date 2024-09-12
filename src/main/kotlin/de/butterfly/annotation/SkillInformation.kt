package de.butterfly.annotation
@Target( AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)

annotation class SkillInformation(val name:String,val description:String,val id:Int)
