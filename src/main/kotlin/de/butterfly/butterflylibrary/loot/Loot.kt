package de.butterfly.butterflylibrary.loot

import de.butterfly.butterflylibrary.items.ItemBuilder
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import kotlin.random.Random

@Suppress("unused")
class Loot(
    private val item: ItemStack,
    val dropRate: Double,
    private val randomizer: Random = Random.Default,
    private val min:Int= 1,
    private val max:Int= 1) {

    constructor(
        mat: Material,
        name: Component,
        droprate: Double,
        enchantment: Enchantment,
        itemFlag: ItemFlag,
        lore: List<Component> = listOf(),
        level: Int = 0,
        levelRestriction: Boolean = false,
        itemLevel: Int = 0,
        elementLore: Component = Component.text("Element Lore", NamedTextColor.BLUE),
        colorLevelTag: NamedTextColor = NamedTextColor.DARK_PURPLE,
        levelColor: NamedTextColor = NamedTextColor.LIGHT_PURPLE
    ) : this(
        item = createItem(mat, name, enchantment, itemFlag, lore, level, levelRestriction, itemLevel, elementLore, colorLevelTag, levelColor),
        dropRate = droprate,
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
            elementLore: Component,
            colorLevelTag: NamedTextColor,
            levelColor: NamedTextColor
        ): ItemStack {
            val itemBuilder = ItemBuilder(mat)
                .setDisplayName(name)
                .addFlag(itemFlag)
                .setLore(lore)
                .addEnchantment(enchantment, level, levelRestriction)

            if (itemLevel > 0) {
                itemBuilder.setLevelLore(
                    loreComponent = Component.text("Level required: $itemLevel").color(NamedTextColor.GRAY),
                    elementLoreComponent = elementLore,
                    colorLevelTag = colorLevelTag,
                    levelColor = levelColor,
                    level = itemLevel
                )
            }

            return itemBuilder.build()
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
    fun tryDropItem(location: Location){
        if (Math.random()  * 101 > dropRate) return
        val amount = randomizer.nextInt(min, max)
        location.world.dropItemNaturally((location), item.clone().apply { this.amount = amount })

    }
}