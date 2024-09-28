package de.butterfly.butterflylibrary.skill

interface ISkill {

    val learnCondition: SkillLearnCondition?
        get() = null  // Default is null for flexibility
@Suppress("unused")
    fun execute(skill: Skill) {
        // Default implementation can be provided if needed
    }
@Suppress("unused")

    fun canLearn(skill: Skill,placeHolder: PlaceHolder<*>): Boolean {
        // Use the learnCondition if available, otherwise default to true
        return learnCondition?.canLearn(skill) ?: true
    }
}