package de.butterfly.butterflylibrary.items

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BookMeta
import net.kyori.adventure.text.Component

/**
 * Builder object for creating a written book ItemStack in Minecraft.
 */
@Suppress("unused")
object WrittenBookBuilder {

    /**
     * Holds metadata for a written book.
     *
     * This property must be initialized before usage and can be used to set various
     * attributes of a written book such as author, title, pages, display name, lore, and generation.
     */
    private lateinit var bookMeta: BookMeta

    /**
     * Sets the author of the book.
     *
     * @param author The name of the author to be set.
     * @return The current instance of WrittenBookBuilder.
     */
    fun setAuthor(author: String): WrittenBookBuilder {
        bookMeta.author = author
        return this
    }

    /**
     * Sets the title of the book.
     *
     * @param title The title to be set for the book.
     * @return The updated instance of WrittenBookBuilder.
     */
    fun setTitle(title: String): WrittenBookBuilder {
        bookMeta.title = title
        return this
    }

    /**
     * Adds a page with the specified content to the written book.
     *
     * @param pageContent the content to be added as a page in the book
     * @return the current instance of WrittenBookBuilder with the new page added
     */
    fun addPage(pageContent: Component): WrittenBookBuilder {
        bookMeta.addPages(pageContent)
        return this
    }

    /**
     * Sets the display name of the book to the specified component.
     *
     * @param displayName the display name component to set for the book.
     * @return the updated WrittenBookBuilder instance.
     */
    fun setDisplayName(displayName: Component): WrittenBookBuilder {
        bookMeta.displayName(displayName)
        return this
    }

    /**
     * Sets the lore for a written book.
     *
     * @param lore A list of Component objects representing the lore to be added to the book.
     * @return The modified WrittenBookBuilder instance with the updated lore.
     */
    fun addLore(lore :List<Component>): WrittenBookBuilder {

            bookMeta.lore(lore)
            return this

    }

    /**
     * Sets the generation of the book.
     *
     * @param generation the generation to set for the book
     * @return the current instance of WrittenBookBuilder for method chaining
     */
    fun setGeneration(generation: BookMeta.Generation): WrittenBookBuilder {
        bookMeta.generation = generation
        return this
    }



    /**
     * Converts the specified quantity into an ItemStack of written books.
     *
     * @param quantity The number of written books to include in the ItemStack.
     * @return An ItemStack containing the specified quantity of written books.
     */
    fun build(quantity: Int): ItemStack {
        val itemStack = ItemStack(org.bukkit.Material.WRITTEN_BOOK, quantity)
        itemStack.itemMeta = bookMeta
        return itemStack
    }

}

