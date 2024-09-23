package de.butterfly.butterflylibrary.items

import org.bukkit.DyeColor
import org.bukkit.Material
import org.bukkit.block.banner.Pattern
import org.bukkit.block.banner.PatternType
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BannerMeta

object BannerBuilder
{
    private lateinit var banner:ItemStack
    private lateinit var bannerMeta:BannerMeta
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


    fun addPattern(patternType: PatternType,dyeColor: DyeColor):BannerBuilder {
        bannerMeta.addPattern(Pattern(dyeColor,patternType))
        return this
    }
    fun build():ItemStack{

        banner.itemMeta = this.bannerMeta
        return banner
    }
}