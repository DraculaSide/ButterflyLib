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
        lore: List<Component> = listOf()
    ) : this(
        item = ItemBuilder(mat)
            .setDisplayName(name)
            .addFlag(itemFlag)
            .setLore(lore)
            .addEnchantment(enchantment, 1, true)
            .build(),
        percentage = percentage
    )

    companion object {
        /**
         * Factory method to create a default Loot instance.
         */
        @JvmStatic
        fun getDefaultLoot(): Loot {
            return Loot(
                Material.DIRT,
                Component.text("Default Loot", NamedTextColor.GOLD),
                10.0,
                Enchantment.WIND_BURST,
                ItemFlag.HIDE_ENCHANTS,
                listOf(
                    Component.text("This is a default loot", NamedTextColor.GRAY)
                )

            )
        }
    }
}