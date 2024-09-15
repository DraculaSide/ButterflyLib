package de.butterfly.loot

import de.butterfly.items.ItemBuilder
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

@Suppress("unused")
class Loot(
    private val item: ItemStack,
    val percentage: Double
) {

    constructor(
        mat: Material,
        name: Component,
        percentage: Double,
        enchantment: Enchantment,
        itemFlag: ItemFlag,
        lore: List<Component> = listOf(),
        level: Int = 0,
        levelRestriction: Boolean = false,
        itemLevel: Int = 0
    ) : this(
        item = createItem(mat, name, enchantment, itemFlag, lore, level, levelRestriction, itemLevel),
        percentage = percentage
    )

    companion object {
        private fun createItem(
            mat: Material,
            name: Component,
            enchantment: Enchantment,
            itemFlag: ItemFlag,
            lore: List<Component>,
            level: Int,
            levelRestriction: Boolean,
            itemLevel: Int,
            component: Component,
            textColor: NamedTextColor
        ): ItemStack {
            return if (itemLevel > 0) {
              LevelItemBuilder(mat,itemLevel)
                  .setDisplayName(name)
                  .setLevelLore(component,textColor,itemLevel)
                  .addFlag(itemFlag)
                  .build()
            } else {
                ItemBuilder(mat)
                    .setDisplayName(name)
                    .addFlag(itemFlag)
                    .setLore(lore)
                    .addEnchantment(enchantment, level, levelRestriction)
                    .build()
            }
        }

        /**
         * Factory method to create a default Loot instance.
         */
        @JvmStatic
        fun getDefaultLoot(): Loot {
            return Loot(
                Material.DIRT,
                Component.text("Default Loot", NamedTextColor.GOLD),
                10.0,
                Enchantment.FEATHER_FALLING, // Example enchantment
                ItemFlag.HIDE_ENCHANTS,
                listOf(
                    Component.text("This is a default loot", NamedTextColor.GRAY)
                )
            )
        }
    }
}