package de.butterfly.butterflyapi.species

import de.butterfly.butterflyapi.skill.Skill
import de.butterfly.butterflyapi.items.ItemBuilder
import de.butterfly.butterflyapi.util.Element
import org.bukkit.inventory.ItemStack

@Suppress("unused")
class Species(
    val name: String,
    private val rawName: String,
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

    /**
     * Removes an ability (Skill) from this species.
     *
     * @param skill the skill to remove
     * @return true if the skill was removed, false if the skill was not found
     */
    fun removeAbility(skill: Skill): Boolean {
        if (abilities.contains(skill)) {
            abilities = abilities.minus(skill)
            return true
        }
        return false
    }

    /**
     * Checks if the species possesses a particular ability.
     *
     * @param skill the skill to check for
     * @return true if the species has the ability, false otherwise
     */
    fun hasAbility(skill: Skill): Boolean {
        return abilities.contains(skill)
    }

    /**
     * Clears all abilities from this species.
     */
    fun clearAbilities() {
        abilities = listOf()
    }

    /**
     * Returns the count of abilities that the species has.
     *
     * @return the count of abilities
     */
    fun getAbilitiesCount(): Int {
        return abilities.size
    }

    /**
     * Compares this species with another species based on the number of abilities.
     *
     * @param otherSpecies the other species to compare to
     * @return a negative integer, zero, or a positive integer as this species has fewer, equal to, or more abilities than the other species
     */
    fun compareToOtherSpecies(otherSpecies: Species): Int {
        return this.abilities.size.compareTo(otherSpecies.abilities.size)
    }

    /**
     * Provides a string representation of the species.
     *
     * @return a string representation of the species
     */
    override fun toString(): String {
        return "Species(name='$name', rawName='$rawName', elementType=$elementType, abilities=$abilities)"
    }
}