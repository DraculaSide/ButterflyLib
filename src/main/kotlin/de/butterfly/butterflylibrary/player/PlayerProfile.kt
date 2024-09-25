package de.butterfly.butterflylibrary.player

import com.google.gson.JsonParser
import de.butterfly.butterflylibrary.skill.Skill
import org.bukkit.Bukkit
import org.bukkit.inventory.ItemStack
import java.util.*

open class PlayerProfile {
    private var playerProfileAllowed:Boolean = false
    private var playerId: UUID = UUID.randomUUID()
    private var playerName: String? = Bukkit.getPlayer(playerId)?.name
    private var health:Double = 0.0
    private var level:Int = 0
    private var xp:Int = 0
    private var skills:List<Skill> = listOf()
    //int = slot Itemstack Item
    private var inventory:MutableMap<Int,ItemStack> = mutableMapOf()
    open val data:PlayerData= PlayerData(playerId,health,level,xp,inventory,skills)
    private val jsonParser = JsonParser()
    fun isAllowed(allowed:Boolean): Boolean {
        playerProfileAllowed = allowed
        return playerProfileAllowed

    }
    fun extratData():PlayerData{
        return data
    }
    fun exist(){

    }
    fun update(){

    }
    fun delete(){

    }
    fun create(){

    }
}