package de.butterfly.butterflylibrary.annotation


import de.butterfly.butterflylibrary.skill.SkillLearnCondition
import de.butterfly.butterflylibrary.util.Element


@Target( AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)

annotation class SkillInformation(val name:String,
                                  val description:String,
                                  val id:Int,
                                  val skillLearnCondition: SkillLearnCondition,
                                  val element:Element,
                                  val canFuse:Boolean = false)