package de.butterfly.items

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import java.util.UUID

class SkullCreator {

    fun createSkullByUUID(id: UUID): ItemStack {
        val skull = ItemStack(Material.PLAYER_HEAD)
        val meta = skull.itemMeta as SkullMeta
        meta.owningPlayer = Bukkit.getOfflinePlayer(id)
        skull.itemMeta = meta
        return skull
    }
}