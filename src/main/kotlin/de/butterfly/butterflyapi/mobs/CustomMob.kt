package de.butterfly.butterflyapi.mobs

import de.butterfly.butterflyapi.items.ItemBuilder
import de.butterfly.butterflyapi.loot.Loot
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.plugin.java.JavaPlugin
import kotlin.random.Random
@Suppress("unused")
class CustomMob(
    private val name: String,
    private val health: Double,
    private val spawnchance: Double,
    private val entityType: EntityType,
    private val mainItem: ItemBuilder,
    private val armor: Array<ItemBuilder>,
    private val lootTable: List<Loot>
) {

    fun tryDropLoot(location: Location) {
        for (loot in lootTable) {
            loot.tryDropItem(location)
        }
    }

    fun spawn(location: Location, plugin: JavaPlugin) {
        if (Random.nextDouble() > spawnchance) return

        val world = location.world ?: return
        val entity = world.spawnEntity(location, entityType) as? LivingEntity ?: return

        entity.customName = name
        entity.health = health
        entity.equipment?.helmet = armor.getOrNull(0)?.build()
        entity.equipment?.chestplate = armor.getOrNull(1)?.build()
        entity.equipment?.leggings = armor.getOrNull(2)?.build()
        entity.equipment?.boots = armor.getOrNull(3)?.build()
        entity.equipment?.setItemInMainHand(mainItem.build())
    }
}