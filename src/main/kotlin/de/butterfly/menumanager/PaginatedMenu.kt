package de.butterfly.menumanager

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import de.butterfly.items.ItemBuilder
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
@Suppress("unused")
abstract class PaginatedMenu(override var playerMenuUtility: PlayerMenuUtility?) : MenuManager(playerMenuUtility) {

    // Keep track of what page the menu is on
    protected var page: Int = 0

    // 28 is max items because with the border set below,
    // 28 empty slots are remaining.
    private val maxItemsPerPage: Int = 28

    // The index represents the index of the slot
    // that the loop is on
    protected var index: Int = 0

    // Set the border and menu buttons for the menu
    fun addMenuBorder() {
        inventory?.setItem(48, makeItem(Material.DARK_OAK_BUTTON, Component.text("Left", NamedTextColor.GREEN)))
        inventory?.setItem(49, makeItem(Material.BARRIER, Component.text("Close", NamedTextColor.DARK_RED)))
        inventory?.setItem(50, makeItem(Material.DARK_OAK_BUTTON, Component.text("Right", NamedTextColor.GREEN)))

        for (i in 0 until 10) {
            if (inventory?.getItem(i) == null) {
                inventory?.setItem(i, super.fillerItem)
            }
        }

        inventory?.setItem(17, super.fillerItem)
        inventory?.setItem(18, super.fillerItem)
        inventory?.setItem(26, super.fillerItem)
        inventory?.setItem(27, super.fillerItem)
        inventory?.setItem(35, super.fillerItem)
        inventory?.setItem(36, super.fillerItem)

        for (i in 44 until 54) {
            if (inventory?.getItem(i) == null) {
                inventory?.setItem(i, super.fillerItem)
            }
        }
    }

    fun getMaxItemsPerPage(): Int {
        return maxItemsPerPage
    }

    private fun makeItem(material: Material, name: Component): ItemStack {
        return ItemBuilder(material)
            .setDisplayName(name)
            .build()
    }
}