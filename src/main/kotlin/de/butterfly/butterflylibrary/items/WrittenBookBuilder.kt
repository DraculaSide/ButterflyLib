package de.butterfly.butterflylibrary.items

import com.google.common.base.Preconditions
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import net.kyori.adventure.text.Component
import org.apache.commons.lang3.ArrayUtils.add
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BookMeta


/**
 * A builder for constructing and manipulating `WrittenBook` objects.
 */
@Suppress("unused")
object WrittenBookBuilder {

    /**
     * Creates an instance of BookMeta from an ItemStack of type WRITTEN_BOOK.
     *
     * @return a BookMeta instance extracted from the ItemStack.
     */
    private fun createBookMeta(): BookMeta {
        val item = ItemStack(Material.WRITTEN_BOOK)
        return item.itemMeta as BookMeta
    }

    /**
     * Sets the author of the given book and returns a new instance of the book with the updated author.
     *
     * @param author the name of the author to set
     * @param book the original book to update
     * @return a new instance of WrittenBook with the updated author
     */
    fun setAuthor(author: String, book: WrittenBook): WrittenBook {
        return book.copy(author = author)
    }

    /**
     * Sets the title of the given book.
     *
     * @param title The new title to set for the book
     * @param book The book for which the title is to be set
     * @return A new WrittenBook instance with the updated title
     */
    fun setTitle(title: String, book: WrittenBook): WrittenBook {
        return book.copy(title = title)
    }

    /**
     * Adds a new page to the given written book.
     *
     * @param pageContent the content of the page to be added
     * @param book the book to which the page will be added
     * @return a new instance of WrittenBook with the added page
     */
    fun addPage(pageContent: String, book: WrittenBook): WrittenBook {
        val newPage = Component.text(pageContent)
        val newPages = book.pages + newPage // Benutzt + Operator, um eine neue Liste zu erzeugen
        return book.copy(pages = newPages)
    }

    /**
     * Sets the display name of a WrittenBook and returns a new instance with the updated value.
     *
     * @param displayName the new display name for the book
     * @param book the original WrittenBook instance to be updated
     * @return a new WrittenBook instance with the updated display name
     */
    fun setDisplayName(displayName: String, book: WrittenBook): WrittenBook {
        return book.copy(displayName = displayName)
    }

    /**
     * Adds a new lore line to the given WrittenBook's lore.
     *
     * @param loreLine the lore line to be added
     * @param book the WrittenBook to which the lore line will be added
     * @return a new WrittenBook instance with the updated lore
     */
    fun addLore(loreLine: String, book: WrittenBook): WrittenBook {
        val newLore = book.lore.apply { add(JsonPrimitive(loreLine)) }
        return book.copy(lore = newLore)
    }

    /**
     * Sets the generation of the provided WrittenBook.
     *
     * @param generation the new generation to be set for the book
     * @param book the WrittenBook instance to be modified
     * @return a new WrittenBook instance with the updated generation
     */
    fun setGeneration(generation: BookMeta.Generation, book: WrittenBook): WrittenBook {
        return book.copy(generation = generation)
    }

    /**
     * Converts a JSON object into a WrittenBook object.
     *
     * @param bookObject The JSON object representing a book, which must contain an array of pages.
     * @return A WrittenBook object with data extracted from the given JSON object.
     */
    fun fromJson(bookObject: JsonObject): WrittenBook {
        Preconditions.checkArgument(
            bookObject.has("pages") && bookObject.get("pages").isJsonArray,
            "The provided JSON object must have a pages array!"
        )

        val author = bookObject.get("author")?.asString ?: ""
        val title = bookObject.get("title")?.asString ?: ""
        val pages = bookObject.getAsJsonArray("pages").map { Component.text(it.asString) }
        val generation = bookObject.get("generation")?.asInt?.let { intToGeneration(it) }
            ?: BookMeta.Generation.ORIGINAL
        val lore = JsonArray()
        val displayName = bookObject.get("displayName")?.asString

        return WrittenBook(author, title, pages, generation, lore, displayName)
    }

    /**
     * Transforms an integer representation of a book generation to its corresponding
     * `BookMeta.Generation` enum.
     *
     * @param generation An integer value representing a book's generation.
     *                   - 1 corresponds to `COPY_OF_ORIGINAL`
     *                   - 2 corresponds to `COPY_OF_COPY`
     *                   - 3 corresponds to `TATTERED`
     *                   - Any other value defaults to `ORIGINAL`
     * @return The corresponding `BookMeta.Generation` enum value.
     */
    private fun intToGeneration(generation: Int): BookMeta.Generation {
        return when (generation) {
            1 -> BookMeta.Generation.COPY_OF_ORIGINAL
            2 -> BookMeta.Generation.COPY_OF_COPY
            3 -> BookMeta.Generation.TATTERED
            else -> BookMeta.Generation.ORIGINAL
        }
    }

    /**
     * Converts a WrittenBook object into an ItemStack.
     *
     * @param tag.book The WrittenBook object containing details such as author, title, and pages.
     * @param tag.quantity The number of ItemStacks to create.
     * @return ItemStack representing the book with the provided details.
     */
    fun toItemStack(book: WrittenBook, quantity: Int): ItemStack {
        val item = ItemStack(Material.WRITTEN_BOOK, quantity)
        val meta = createBookMeta()

        meta.author = book.author
        meta.title = book.title
        meta.pages(book.pages)
        item.itemMeta = meta

        return item
    }

    /**
     * Calculates the percentage of completion based on the number of pages in the book.
     *
     * @param book the book object containing pages information
     * @return the percentage of the book that is completed
     */
    fun percentFull(book: WrittenBook): Float {
        // Assuming maximum pages count as 100 for this example
        val maxPages = 100
        return book.pages.size / maxPages.toFloat()
    }
}