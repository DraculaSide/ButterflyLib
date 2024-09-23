package de.butterfly.butterflylibrary.skill



class FusionSkill : ISkill {

    fun execute() {
        println("Fusion skill activated!")
        // Add logic for the fusion skill here
    }

    fun fuseWith(skill1: ISkill, skill2: ISkill): FusionSkill {
        return FusionSkill().apply {
            if (skill1.learnCondition == SkillLearnCondition.FUSION && skill2.learnCondition == SkillLearnCondition.FUSION) {
                println("Fusion Skill created from $skill1 and ${skill2}.")
            } else {
                println("Both skills must be fusion compatible.")
            }
        }
    }
}