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
@Suppress("unused")
class RitualManager(private val plugin: Plugin) {
    private val rituals = mutableListOf<Class<*>>()


    private fun registerRitual(ritual: IRitual) {
        val ritualClass = ritual::class.java
        if (ritualClass.isAnnotationPresent(de.butterfly.butterflylibrary.annotation.Ritual::class.java)) {
            rituals.add(ritualClass)
            // Add ritual class to the list if it has the @Ritual annotation
            if (ritual is Listener) {
                Bukkit.getPluginManager().registerEvents(ritual, plugin)
            }
        } else {
            println("Failed to register ritual: ${ritualClass.simpleName}. Missing @Ritual annotation.")
        }
    }

    /**
     * Retrieves ritual information from the specified class if it contains the @Ritual annotation.
     *
     * @param ritualClass The class from which to retrieve the ritual information.
     * @return A string containing the ritual name, description, and ID, or a message indicating that no ritual information was found.
     */
    fun getRitualInfo(ritualClass: Class<*>): String {
        val annotation = ritualClass.getAnnotation(de.butterfly.butterflylibrary.annotation.Ritual::class.java)
       return annotation.name
    }

    /**
     * Initializes the RitualManager and registers predefined rituals.
     */
    init {
        registerRituals()
        instance = plugin
        ritualLs = rituals
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
        lateinit var ritualLs : List<Class<*>>
        lateinit var instance: Plugin


    }


}