package de.butterfly.annotation

import de.butterfly.skill.SkillLearnCondition


@Target( AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)

annotation class SkillInformation(val name:String,val description:String,val id:Int,val skillLearnCondition:SkillLearnCondition,val canFuse:Boolean = false)