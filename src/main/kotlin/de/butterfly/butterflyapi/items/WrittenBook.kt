package de.butterfly.butterflyapi.items

import com.google.gson.JsonArray
import org.bukkit.inventory.meta.BookMeta

data class WrittenBook(val author: String,
                       val title: String,
                       val pages: JsonArray,
                       val generation: BookMeta.Generation,
                       val lore: JsonArray,
                       val displayName: String?)
