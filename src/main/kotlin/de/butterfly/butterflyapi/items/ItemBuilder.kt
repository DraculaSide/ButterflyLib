package de.butterfly.butterflyapi.items

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta


/**
 * A builder class to create and configure [ItemStack] instances.
 */
open class ItemBuilder (mat: Material) {
    private val itemStack = ItemStack(mat)
    private val meta: ItemMeta = itemStack.itemMeta

    /**
     * Sets the display name of the item.
     *
     * @param name the display name
     * @return the current ItemBuilder instance for method chaining
     */
    fun setDisplayName(name:Component): ItemBuilder {
        meta.displayName(name)
        return this
    }

    /**
     * Sets the lore for the item.
     *
     * @param lore the lore lines
     * @return the current ItemBuilder instance for method chaining
     */
    @Suppress("unused")
    fun setLore(vararg lore: List<Component>): ItemBuilder {
        meta.lore()
        return this
    }


    fun setLevelLore(
        loreComponent: Component,
        colorLevelTag: TextColor,
        levelColor: TextColor,
        level: Int,
        elementLoreComponent: Component
    ): ItemBuilder {

        val levelLoreComponent = Component.text("Level: ").color(colorLevelTag)
            .append(Component.text(level.toString()).color(levelColor))
        meta.lore(listOf(loreComponent, levelLoreComponent))
        return this

    }
    /**
     * Sets the custom model data for the item.
     *
     * @param modelData the model data value
     * @return the current ItemBuilder instance for method chaining
     */
    @Suppress("unused")
    fun setModelData(modelData: Int): ItemBuilder {
        meta.setCustomModelData(modelData)
        return this
    }

    /**
     * Sets the amount for the item.
     *
     * @param amount the amount
     * @return the current ItemBuilder instance for method chaining
     */
    @Suppress("unused")
    fun setAmount(amount: Int): ItemBuilder {
        itemStack.amount = amount
        return this
    }

    /**
     * Adds an item flag to the item.
     *
     * @param flags the item flag to add
     * @return the current ItemBuilder instance for method chaining
     */
    fun addFlag(vararg flags: ItemFlag): ItemBuilder {
        meta.addItemFlags(*flags)
        return this
    }




    /**
     * Adds an enchantment to the item.
     *
     * @param enchantment the enchantment to add
     * @param level the level of the enchantment
     * @param ignoreLevelRestriction whether to ignore item level restrictions
     * @return the current ItemBuilder instance for method chaining
     */
    fun addEnchantment(enchantment: Enchantment, level: Int, ignoreLevelRestriction: Boolean): ItemBuilder {
        meta.addEnchant(enchantment, level, ignoreLevelRestriction)
        return this
    }

    /**
     * Builds the configured [ItemStack] instance.
     *
     * @return the configured ItemStack
     */
    open fun build(): ItemStack {
        itemStack.itemMeta = meta
        return itemStack
    }
}