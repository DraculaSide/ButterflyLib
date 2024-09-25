package de.butterfly.butterflylibrary.items

import org.bukkit.DyeColor
import org.bukkit.Material
import org.bukkit.block.banner.Pattern
import org.bukkit.block.banner.PatternType
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BannerMeta

/**
 * An object responsible for the creation and customization of banners in Minecraft.
 */
object BannerBuilder
{
    /**
     * Holds the ItemStack representing the banner with the chosen color.
     * The banner is created and assigned based on the selected `BannerColor` in the `createBanner` function.
     */
    private lateinit var banner:ItemStack
    /**
     * Stores metadata information for a banner, including patterns and colors.
     *
     * Initialized and used within the functions to add patterns or to build the final ItemStack.
     */
    private lateinit var bannerMeta:BannerMeta
    /**
     * Creates an ItemStack representing a banner of the specified color.
     *
     * @param color The color of the banner to be created, specified by the BannerColor enum.
     * @return An ItemStack representing the created banner.
     */
    fun createBanner(color: BannerColor):ItemStack{

        when(color){
            BannerColor.RED -> banner = ItemStack(Material.RED_BANNER)
            BannerColor.BLACK -> banner = ItemStack(Material.BLACK_BANNER)
            BannerColor.BLUE -> banner = ItemStack(Material.BLUE_BANNER)
            BannerColor.GREEN -> banner = ItemStack(Material.GREEN_BANNER)
            BannerColor.WHITE -> banner = ItemStack(Material.WHITE_BANNER)
            BannerColor.YELLOW -> banner = ItemStack(Material.YELLOW_BANNER)
            BannerColor.PURPLE -> banner = ItemStack(Material.PURPLE_BANNER)
            BannerColor.ORANGE -> banner = ItemStack(Material.ORANGE_BANNER)
            BannerColor.GRAY -> banner = ItemStack(Material.GRAY_BANNER)
            BannerColor.BROWN -> banner = ItemStack(Material.BROWN_BANNER)
            BannerColor.CYAN -> banner = ItemStack(Material.CYAN_BANNER)
            BannerColor.MAGENTA -> banner = ItemStack(Material.MAGENTA_BANNER)
            BannerColor.LIGHTBLUE -> banner = ItemStack(Material.LIGHT_BLUE_BANNER)
            BannerColor.LIGHTGRAY -> banner = ItemStack(Material.LIGHT_GRAY_BANNER)
            BannerColor.LIME -> banner = ItemStack(Material.LIME_BANNER)
            BannerColor.PINK -> banner = ItemStack(Material.PINK_BANNER)


        }
        return banner

    }


    /**
     * Adds a new pattern to the banner with the specified pattern type and dye color.
     *
     * @param patternType The type of the pattern to add.
     * @param dyeColor The color of the dye to use in the pattern.
     * @return The updated BannerBuilder instance.
     */
    fun addPattern(patternType: PatternType, dyeColor: DyeColor):BannerBuilder {
        bannerMeta.addPattern(Pattern(dyeColor,patternType))
        return this
    }
    /**
     * Builds the ItemStack with the current banner meta.
     *
     * @return the built ItemStack with the updated banner meta.
     */
    fun build():ItemStack{

        banner.itemMeta = this.bannerMeta
        return banner
    }
}