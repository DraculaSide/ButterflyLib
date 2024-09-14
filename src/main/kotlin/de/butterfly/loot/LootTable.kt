package de.butterfly.loot

class LootTable {
    val lootTable = mutableListOf<Loot>()
    val percentage = lootTable.sumOf { it.percentage }
    /*
    LootTable
    persantage >= or == 100
     */
    fun isPercentageValid() : Boolean {
        return percentage >= 100
    }
}