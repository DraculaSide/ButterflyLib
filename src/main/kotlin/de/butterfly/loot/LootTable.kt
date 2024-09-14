package de.butterfly.loot
@Suppress("unused")
class LootTable {
   private val lootTable = mutableListOf<Loot>()
    private val percentage = lootTable.sumOf { it.percentage }
    /*
    LootTable
    persantage >= or == 100
     */
    fun isPercentageValid() : Boolean {
        return percentage >= 100
    }
}