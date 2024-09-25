import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BookMeta
import com.google.gson.JsonObject
import de.butterfly.butterflylibrary.items.WrittenBook
import net.kyori.adventure.text.Component

/**
 * Ein Builder zum Erstellen und Manipulieren von `WrittenBook` Objekten.
 */
@Suppress("unused")
object WrittenBookBuilder {

    lateinit var bookMeta: BookMeta

    fun setAuthor(author: String, book: WrittenBook): WrittenBookBuilder {
        bookMeta.author = author
        return this
    }

    fun setTitle(title: String, book: WrittenBook): WrittenBookBuilder {
        bookMeta.title = title
        return this
    }

    fun addPage(pageContent: Component, book: WrittenBook): WrittenBookBuilder {
        bookMeta.addPages(pageContent)
        return this
    }

    fun setDisplayName(displayName: Component, book: WrittenBook): WrittenBookBuilder {
        bookMeta.displayName(displayName)
        return this
    }

    fun addLore(loreLine: String, book: WrittenBook): WrittenBook {
        val lore = bookMeta.lore ?: mutableListOf()
        lore.add(loreLine)
        bookMeta.lore = lore
        return book
    }

    fun setGeneration(generation: BookMeta.Generation, book: WrittenBook): WrittenBookBuilder {
        bookMeta.generation = generation
        return this
    }

    fun fromJson(bookObject: JsonObject): WrittenBookBuilder {
        // Implementation der JSON-Verarbeitung ausgelassen
        return this
    }

    fun convertToItemStack(book: WrittenBook, quantity: Int): ItemStack {
        val itemStack = ItemStack(org.bukkit.Material.WRITTEN_BOOK, quantity)
        itemStack.itemMeta = bookMeta
        return itemStack
    }
}

