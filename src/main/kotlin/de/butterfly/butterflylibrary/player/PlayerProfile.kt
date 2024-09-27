package de.butterfly.butterflylibrary.player

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import de.butterfly.butterflylibrary.skill.Skill
import org.bukkit.Bukkit
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.util.UUID

open class PlayerProfile(plugin: JavaPlugin, playerName: String) {
    private var playerProfileAllowed = false
    private val playerId: UUID = Bukkit.getPlayer(playerName)!!.uniqueId
//    private val playerName: String= Bukkit.getPlayer(playerId)!!.name
    private var health = 0.0
    private var level = 0
    private var xp = 0
    private var skills = listOf<Skill>()
    private var inventory = mutableMapOf<Int, ItemStack>()
    open val data = PlayerData(playerId, health, level, xp, inventory, skills)
    private val pluginFolder = plugin.dataFolder
    private val playerProfileDir = File(pluginFolder, "PlayerData")

    private val playerProfileJson: String = "$playerId.json"

    fun isAllowed(): Boolean {
        // Implementation omitted for brevity
        return false
    }

    fun extractData(): PlayerData {
        return data
    }

    fun update(){

            val gson = Gson()
            val playerFile = File(playerProfileDir, playerProfileJson)

            if (playerFile.exists()) {
                // Read existing JSON data
                val playerData = playerFile.bufferedReader().use { it.readText() }
                val playerDataType = object : TypeToken<PlayerData>() {}.type
                val data: PlayerData = gson.fromJson(playerData, playerDataType)

                // Modify the data as needed
                data.health = health
                data.level = level
                data.xp = xp
                data.inventory = inventory
                data.skills = skills

                // Write updated JSON back to file
                val updatedJson = gson.toJson(data)
                playerFile.bufferedWriter().use { it.write(updatedJson) }

            } else {
                // Create new file with initial data if it does not exist
                val newDataJson = gson.toJson(data)
                playerFile.bufferedWriter().use { it.write(newDataJson) }
            }
        }


    fun delete() {
        if (File(playerProfileDir, playerProfileJson).exists()) {
            File(playerProfileDir, playerProfileJson).delete()
        }
    }

    fun createJsonFile() {
        if(!playerProfileDir.exists()){ playerProfileDir.mkdirs()}
        if(!File(playerProfileDir, playerProfileJson).exists()){ File(playerProfileDir, playerProfileJson).createNewFile()
        }

    }
}