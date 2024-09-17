package de.butterfly.butterflyapi.annotation


import de.butterfly.butterflyapi.skill.SkillLearnCondition
import de.butterfly.butterflyapi.util.Element


@Target( AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)

annotation class SkillInformation(val name:String, val description:String, val id:Int, val skillLearnCondition: SkillLearnCondition, val element:Element, val canFuse:Boolean = false)