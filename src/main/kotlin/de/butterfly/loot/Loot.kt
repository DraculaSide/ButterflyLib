package de.butterfly.butterflyApi.loot

import de.butterfly.butterflyApi.items.ItemBuilder
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

class Loot(
    val item: ItemStack,
    val percentage: Double
) {

    constructor(
        mat: Material,
        name: String,
        percentage: Double,
        enchantment: Enchantment,
        itemFlag: ItemFlag,
        lore  : Array<String> = arrayOf()
    ) : this(
        item = ItemBuilder(mat)
            .setDisplayName(name)
            .addFlag(itemFlag)
            .setLore(*lore)
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
                "Default",
                10.0,
                Enchantment.LOOT_BONUS_BLOCKS,
                ItemFlag.HIDE_ENCHANTS,
                "This is a default dirt"
            )
        }
    }
}