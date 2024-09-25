package de.butterfly.butterflylibrary.items


import net.kyori.adventure.text.Component
import org.bukkit.DyeColor
import org.bukkit.FireworkEffect
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.FireworkMeta
@Suppress("unused")
object FireworkBuilder {
    private lateinit var firework:ItemStack

    private lateinit var fireworkMeta: FireworkMeta

    fun setDisplayName(name: Component) :FireworkBuilder{
        fireworkMeta.displayName(name)
        return this
    }
    fun setPower(power: Int) :FireworkBuilder{
        fireworkMeta.power = power
        return this
    }
    fun addEffect(effect: FireworkEffect) :FireworkBuilder{
        fireworkMeta.addEffect(effect)
        return this
    }
    fun build() :ItemStack{

        firework.itemMeta = this.fireworkMeta
        return firework
    }

}