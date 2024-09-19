package de.butterfly.butterflyapi.items

import com.google.common.base.Preconditions
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BookMeta


@Suppress("unused")
object WrittenBookBuilder {

    private fun createBookMeta(): BookMeta {
        val item = ItemStack(Material.WRITTEN_BOOK)
        return item.itemMeta as BookMeta
    }

    fun setAuthor(author: String, book: WrittenBook): WrittenBook {
        return book.copy(author = author)
    }

    fun setTitle(title: String, book: WrittenBook): WrittenBook {
        return book.copy(title = title)
    }

    fun addPage(pageContent: String, book: WrittenBook): WrittenBook {
        val newPages = book.pages.apply { add(JsonPrimitive(pageContent)) }
        return book.copy(pages = newPages)
    }

    fun setDisplayName(displayName: String, book: WrittenBook): WrittenBook {
        return book.copy(displayName = displayName)
    }

    fun addLore(loreLine: String, book: WrittenBook): WrittenBook {
        val newLore = book.lore.apply { add(JsonPrimitive(loreLine)) }
        return book.copy(lore = newLore)
    }

    fun setGeneration(generation: BookMeta.Generation, book: WrittenBook): WrittenBook {
        return book.copy(generation = generation)
    }

    fun fromJson(bookObject: JsonObject): WrittenBook {
        Preconditions.checkArgument(
            bookObject.has("pages") && bookObject.get("pages").isJsonArray,
            "The provided JSON object must have a pages array!"
        )

        val author = bookObject.get("author")?.asString ?: ""
        val title = bookObject.get("title")?.asString ?: ""
        val pages = bookObject.getAsJsonArray("pages")
        val generation = bookObject.get("generation")?.asInt?.let { intToGeneration(it) }
            ?: BookMeta.Generation.ORIGINAL
        val lore = JsonArray()
        val displayName = bookObject.get("displayName")?.asString

        return WrittenBook(author, title, pages, generation, lore, displayName)
    }

    private fun intToGeneration(generation: Int): BookMeta.Generation {
        return when (generation) {
            1 -> BookMeta.Generation.COPY_OF_ORIGINAL
            2 -> BookMeta.Generation.COPY_OF_COPY
            3 -> BookMeta.Generation.TATTERED
            else -> BookMeta.Generation.ORIGINAL
        }
    }

    fun toItemStack(book: WrittenBook, quantity: Int): ItemStack {
        val item = ItemStack(Material.WRITTEN_BOOK, quantity)
        val meta = createBookMeta()

        meta.author = book.author
        meta.title = book.title
        meta.pages = book.pages.map { it.asString }.toList()
        item.itemMeta = meta

        return item
    }

    fun percentFull(book: WrittenBook): Float {
        // Assuming maximum pages count as 100 for this example
        val maxPages = 100
        return book.pages.size() / maxPages.toFloat()
    }
}