package de.butterfly.skill

import de.butterfly.annotation.MaxValue
import de.butterfly.annotation.SkillInformation
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

/**
 * SkillManager is responsible for managing skill classes within the plugin.
 * It registers skills and provides skill information based on annotations.
 *
 * @param plugin The plugin instance associated with this SkillManager.
 */
class SkillManager(plugin:Plugin) {
    /**
     * Represents the plugin instance used within the SkillManager class.
     * This instance is required to register events and access plugin-specific functionality.
     */
    var plugin: Plugin = plugin
    /**
     * A mutable list that stores classes of skills.
     * Skills are added to this list by the [registerSkill] function
     * if they have the `@SkillInformation` annotation.
     */
    val skills= mutableListOf<Class<*>>()
    /**
     * Registers a skill with the system, adding it to the list of skills if it has
     * the @SkillInformation annotation. Additionally, if the skill is an instance
     * of `Listener`, it registers the skill as an event listener with the Bukkit plugin manager.
     *
     * @param skill An object representing the skill to be registered. It should have
     * an associated @SkillInformation annotation to be successfully registered.
     */
    fun registerSkill(skill: Any) {
        val skillClass = skill::class.java
        if (skillClass.isAnnotationPresent(SkillInformation::class.java)) {
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
    fun getSkillInfo(skillClass:Class<*>):String {

        val  annotation = skillClass.getAnnotation(SkillInformation::class.java)
        return if (annotation != null){
            "Skill Name: ${annotation.name}, Description: ${annotation.description}, ID: ${annotation.id}"
        } else {
            "No Skillinformation found for class: ${skillClass.simpleName}"

        }


    }
}