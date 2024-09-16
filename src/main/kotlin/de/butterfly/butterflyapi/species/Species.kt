package de.butterfly.butterflyapi.species

import de.butterfly.butterflyapi.skill.Skill
import de.butterfly.butterflyapi.items.ItemBuilder
import de.butterfly.butterflyapi.util.Element
import org.bukkit.inventory.ItemStack
@Suppress("unused")
class Species(
    val name: String,
    val rawName: String,
    private val speciesIconBuilder: ItemBuilder,
    private val elementType: Element // Integrated elementType as a property
) {
    private var abilities: List<Skill> = listOf()

    /**
     * Checks if this species is part of the given list of species.
     *
     * @param speciesList the list of species to check against
     * @return true if this species is part of the given list, false otherwise
     */
    fun isPartOfSkill(speciesList: List<Species>): Boolean {
        return this in speciesList
    }

    /**
     * Displays details about this species.
     */
    fun displayDetail() {
        println("Species name: $name, Element Type: $elementType, Abilities: $abilities")
    }

    /**
     * Adds an ability (Skill) to this species.
     *
     * @param skill the skill to add
     */
    fun addAbility(skill: Skill) {
        abilities = abilities.plus(skill)
    }

    /**
     * Retrieves the species icon as an ItemStack.
     *
     * @return the ItemStack representing this species icon
     */
    fun getSpeciesIcon(): ItemStack {
        return speciesIconBuilder.build()
    }

    /**
     * Retrieves the list of abilities (Skills) of this species.
     *
     * @return the list of abilities
     */
    fun getAbilities(): List<Skill> {
        return abilities
    }
}