package de.butterfly.skill
@Suppress("unused")
enum class SkillLearnCondition {

    LEVEL{
        // Check if the skill can be learned based on level
        override fun canLearn(skill: Skill): Boolean {
           TODO() // Example level requirement
        }
    },

    REWARD {
        // Check if the skill can be learned as a quest reward
        override fun canLearn(skill: Skill): Boolean {
            // Insert quest-specific logic here
            return true  // Assuming the reward condition is met for simplicity
        }
    },

    FUSION {
        // Check if the skill can be learned via fusion
        override fun canLearn(skill: Skill): Boolean {
            // In your Skill.kt logic, you can use this tag in fuseWith
            // For now, returning true for this placeholder
            return true
        }
    },
    TRAINING{
         override fun canLearn(skill: Skill): Boolean {
            return true
        }
    };


    abstract fun canLearn(skill: Skill): Boolean
}
