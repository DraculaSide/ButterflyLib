package de.butterfly.menumanager


import de.butterfly.items.ItemBuilder
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack
import net.kyori.adventure.text.Component

abstract class MenuManager(playerMenu: PlayerMenuUtility?) : InventoryHolder {

    protected open var playerMenuUtility: PlayerMenuUtility? = null
    protected var inventory: Inventory? = null
    protected val FILLER_GLASS: ItemStack = createFillerItem(Material.WHITE_STAINED_GLASS)


    init {
        requireNotNull(playerMenu) { "PlayerMenuUtility must not be null" }
        this.playerMenuUtility = playerMenu
    }

    /**
     * @return Name of the menu
     */
    abstract val menuName: Component

    /**
     * @return Number of slots in the menu
     */
    abstract val slots: Int

    /**
     * Handles menu interaction
     * @param e InventoryClickEvent instance, must not be null.
     */
    abstract fun handleMenu(e: InventoryClickEvent)

    /**
     * Configures items in the menu
     */
    abstract fun setMenuItems()

    /**
     * Opens the menu
     */
    fun open() {
        inventory = Bukkit.createInventory(this, slots, menuName)
        this.setMenuItems()
        playerMenuUtility?.owner?.openInventory(inventory!!)
    }

    override fun getInventory(): Inventory {
        return inventory!!
    }

    /**
     * Sets glass as filler for empty slots in the menu
     * @param material Material to use as the filler, must not be null.
     */
    fun setFillerMaterial(material: Material) {
        requireNotNull(material) { "Material must not be null" }

       val item = createFillerItem(material)
        for (i in 0 until slots) {
            if (inventory!!.getItem(i) == null) {
                inventory!!.setItem(i, item)
            }
        }
    }

    /**
     * Helper method to create an ItemStack
     * @param material Material to use
     * @param displayName Display name for the item
     * @param lore Lore for the item
     * @return Configured ItemStack
     */
    protected fun createFillerItem(material: Material): ItemStack {
        return ItemBuilder(material)
            .setDisplayName(Component.text(" "))
            .build()
    }

}