package de.butterfly.butterflyapi.menumanager


import de.butterfly.butterflyapi.items.ItemBuilder
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack
import net.kyori.adventure.text.Component
/**
 * Abstract class that manages the creation and handling of custom menus in Minecraft.
 *
 * @constructor Requires an instance of PlayerMenuUtility.
 * @param playerMenu Utility class for managing player menus.
 */
@Suppress("unused")
abstract class MenuManager(playerMenu: PlayerMenuUtility?) : InventoryHolder {

    /**
     * Holds the utility instance to manage player-specific menu operations.
     */
    open var playerMenuUtility: PlayerMenuUtility? = null
    /**
     * Represents the inventory managed by the `MenuManager`.
     * This variable is used to store and manipulate the inventory
     * for various menu functionalities.
     *
     * @see open
     * @see getInventory
     * @see setFillerMaterial
     * @see addMenuBorder
     */
    protected var inventory: Inventory? = null
    /**
     * Represents a default filler item used to fill empty slots in the menu's inventory.
     * This item is created using white stained-glass as its material and has no display name.
     */
    protected val fillerItem: ItemStack = createFillerItem(Material.WHITE_STAINED_GLASS)


    init {
        requireNotNull(playerMenu) { "PlayerMenuUtility must not be null" }
        this.playerMenuUtility = playerMenu
    }

    /**
     * The display name of the menu as a [Component]. This name is used as the title of the inventory
     * when the menu is opened.
     */
    abstract val menuName: Component

    /**
     * The number of slots in the menu.
     */
    abstract val slots: Int

    /**
     * Handles the events generated from an inventory click in the menu.
     *
     * @param e the event triggered when an inventory slot is clicked
     */
    abstract fun handleMenu(e: InventoryClickEvent)

    /**
     * Abstract method to set up the items in the menu.
     * This method is called when the menu is opened and is responsible
     * for populating the inventory with the desired items.
     * Derived classes should override this method to specify the menu items.
     */
    abstract fun setMenuItems()

    /**
     * Opens an inventory for the player associated with this menu.
     *
     * This method initializes the inventory with a specified number of slots and a designated menu name.
     * It then sets the menu items using the `setMenuItems` method and opens the inventory for the player
     * specified in `playerMenuUtility`.
     *
     * @throws IllegalStateException if `inventory` is null when attempting to open it
     */
    fun open() {
        inventory = Bukkit.createInventory(this, slots, menuName)
        this.setMenuItems()
        playerMenuUtility?.owner?.openInventory(inventory!!)
    }

    /**
     * Retrieves the inventory managed by this MenuManager instance.
     *
     * @return the Inventory instance that this MenuManager controls
     */
    override fun getInventory(): Inventory {
        return inventory!!
    }

    /**
     * Sets the filler material for the inventory slots that are currently empty.
     *
     * @param material the material to be set as the filler item
     */
    fun setFillerMaterial(material: Material) {

        val item = createFillerItem(material)
        for (i in 0 until slots) {
            if (inventory!!.getItem(i) == null) {
                inventory!!.setItem(i, item)
            }
        }
    }

    /**
     * Creates a filler item with a specified material.
     *
     * @param material the material for the filler item
     * @return the created filler ItemStack
     */
    private fun createFillerItem(material: Material): ItemStack {
        return ItemBuilder(material)
            .setDisplayName(Component.text(" "))
            .build()
    }

}