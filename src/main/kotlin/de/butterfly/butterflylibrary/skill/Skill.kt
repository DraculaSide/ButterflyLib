package de.butterfly.butterflylibrary.skill

/**
 * Represents a skill with a name, description, and unique identifier.
 *
 * @property name The name of the skill.
 * @property description A brief description of the skill.
 * @property id A unique identifier for the skill.
 */

data class Skill(
    val name: String,
    val description: String,
    val id: Int,
    val learnCodition: SkillLearnCondition,
    val element: de.butterfly.butterflylibrary.util.Element
)
