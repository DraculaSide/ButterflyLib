package de.butterfly.butterflylibrary.mobs

import de.butterfly.butterflylibrary.items.ItemBuilder
import de.butterfly.butterflylibrary.loot.Loot
import net.kyori.adventure.text.Component
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.plugin.java.JavaPlugin
import kotlin.random.Random
@Suppress("unused")
class CustomMob(
    private val name: Component,
    private val health: Double,
    private val spawnChance: Double,
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

    fun spawn(location: Location) {
        if (Random.nextDouble() > spawnChance) return

        val world = location.world ?: return
        val entity = world.spawnEntity(location, entityType) as? LivingEntity ?: return

        entity.customName(name)
        entity.health = health
        entity.equipment?.helmet = armor.getOrNull(0)?.build()
        entity.equipment?.chestplate = armor.getOrNull(1)?.build()
        entity.equipment?.leggings = armor.getOrNull(2)?.build()
        entity.equipment?.boots = armor.getOrNull(3)?.build()
        entity.equipment?.setItemInMainHand(mainItem.build())
    }
}