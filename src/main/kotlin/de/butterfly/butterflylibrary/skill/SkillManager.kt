package de.butterfly.butterflylibrary.skill



import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin


/**
 * SkillManager is responsible for managing skill classes within the plugin.
 * It registers skills and provides skill information based on annotations.
 *
 * @param plugin The plugin instance associated with this SkillManager.
 */

@Suppress("unused")
class SkillManager(private val plugin:Plugin) {
    /**
     * Represents the plugin instance used within the SkillManager class.
     * This instance is required to register events and access plugin-specific functionality.
     */

    /**
     * A mutable list that stores classes of skills.
     * Skills are added to this list by the [registerSkill] function
     * if they have the `@SkillInformation` annotation.
     */
    private val skills= mutableListOf<Class<*>>()
    /**
     * Registers a skill with the system, adding it to the list of skills if it has
     * the @SkillInformation annotation. Additionally, if the skill is an instance
     * of `Listener`, it registers the skill as an event listener with the Bukkit plugin manager.
     *
     * @param skill An object representing the skill to be registered. It should have
     * an associated @SkillInformation annotation to be successfully registered.
     */
    private fun registerSkill(skill: ISkill) {
        val skillClass = skill::class.java
        if (skillClass.isAnnotationPresent(de.butterfly.butterflylibrary.annotation.SkillInformation::class.java)) {
            skills.add(skillClass)
        // Add skill class to the list if it has the @Skillinformation annotation
            if (skill is Listener) {
                Bukkit.getPluginManager().registerEvents(skill,plugin)
            }
        } else {
            println("Failed to register skill: ${skillClass.simpleName}. Missing @Skillinformation annotation.")
        }
    }




    /**
     * Retrieves skill information from the specified class if it contains the @SkillInformation annotation.
     *
     * @param skillClass The class from which to retrieve the skill information.
     * @return A string containing the skill name, description, and ID, or a message indicating that no skill information was found.
     */
    @Suppress("unused")
    fun getSkillInfo(skillClass:Class<*>):String {

        val  annotation = skillClass.getAnnotation(de.butterfly.butterflylibrary.annotation.SkillInformation::class.java)
        return if (annotation != null){
            "Skill Name: ${annotation.name}, Description: ${annotation.description}, ID: ${annotation.id},Condition: ${annotation.skillLearnCondition},ElementType: ${annotation.element},Canfuse ${annotation.canFuse}"
        } else {
            "No Skillinformation found for class: ${skillClass.simpleName}"

        }


    }
    @Suppress("unused")
    fun canFuse(skillClass: Class<*>): Boolean {
        val skillInfo = skillClass.getAnnotation(de.butterfly.butterflylibrary.annotation.SkillInformation::class.java)
        return skillInfo?.canFuse ?: false
    }
    //TODO() code addSkill function
}