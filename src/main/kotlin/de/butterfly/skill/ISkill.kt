package de.butterfly.skill

interface ISkill {

    val learnCondition: SkillLearnCondition?
        get() = null  // Default is null for flexibility

    fun execute(skill: Skill) {
        // Default implementation can be provided if needed
    }

    fun canLearn(skill: Skill): Boolean {
        // Use the learnCondition if available, otherwise default to true
        return learnCondition?.canLearn(skill) ?: true
    }
}