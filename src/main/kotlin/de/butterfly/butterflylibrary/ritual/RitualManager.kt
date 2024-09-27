package de.butterfly.butterflylibrary.ritual

import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin

/**
 * RitualManager is responsible for managing ritual classes within the plugin.
 * It registers rituals and provides ritual information based on annotations.
 *
 * @param plugin The plugin instance associated with this RitualManager.
 */

class RitualManager(private val plugin: Plugin) {

    /**
     * Represents the plugin instance used within the RitualManager class.
     * This instance is required to register events and access plugin-specific functionality.
     */

    /**
     * A mutable list that stores classes of rituals.
     * Rituals are added to this list by the [registerRitual] function
     * if they have the `@RitualInformation` annotation.
     */
    private val rituals = mutableListOf<Class<*>>()

    /**
     * Registers a ritual with the system, adding it to the list of rituals if it has
     * the @RitualInformation annotation. Additionally, if the ritual is an instance
     * of `Listener`, it registers the ritual as an event listener with the Bukkit plugin manager.
     *
     * @param ritual An object representing the ritual to be registered. It should have
     * an associated @RitualInformation annotation to be successfully registered.
     */
    private fun registerRitual(ritual: IRitual) {
        val ritualClass = ritual::class.java
        if (ritualClass.isAnnotationPresent(de.butterfly.butterflylibrary.annotation.RitualInformation::class.java)) {
            rituals.add(ritualClass)
            // Add ritual class to the list if it has the @RitualInformation annotation
            if (ritual is Listener) {
                Bukkit.getPluginManager().registerEvents(ritual, plugin)
            }
        } else {
            println("Failed to register ritual: ${ritualClass.simpleName}. Missing @RitualInformation annotation.")
        }
    }

    /**
     * Retrieves ritual information from the specified class if it contains the @RitualInformation annotation.
     *
     * @param ritualClass The class from which to retrieve the ritual information.
     * @return A string containing the ritual name, description, and ID, or a message indicating that no ritual information was found.
     */
    fun getRitualInfo(ritualClass: Class<*>): String {
        val annotation = ritualClass.getAnnotation(de.butterfly.butterflylibrary.annotation.RitualInformation::class.java)
        return if (annotation != null) {
            "Ritual Name: ${annotation.name}, Description: ${annotation.description}"
        } else {
            "No RitualInformation found for class: ${ritualClass.simpleName}"
        }
    }

    /**
     * Initializes the RitualManager and registers predefined rituals.
     */
    init {
        registerRituals()
        Companion.instance = plugin
    }

    /**
     * Registers predefined rituals.
     */
    private fun registerRituals() {

    }

    /**
     * Adds a new ritual to the list of registered rituals.
     *
     * @param ritual The ritual to be added.
     */
    fun addRitual(ritual: IRitual) {
        registerRitual(ritual)
    }

    /**
     * Retrieves the list of registered rituals.
     *
     * @return The list of registered rituals.
     */
    fun getRituals(): List<Class<*>> {
        return rituals
    }

    companion object {
        lateinit var instance: Plugin


    }


}