package de.butterfly.butterflylibrary.items

import com.google.gson.JsonArray
import net.kyori.adventure.text.Component
import org.bukkit.inventory.meta.BookMeta

data class WrittenBook(val author: String,
                       val title: String,
                       val pages: List<Component>,
                       val generation: BookMeta.Generation,
                       val lore: JsonArray,
                       val displayName: String?)
