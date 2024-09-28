package de.butterfly.butterflylibrary.quest
@Suppress("unused")
data class Quest(
    val name: String,
    val description: String,
    val id: Int,
    val reward: String,
    val isCompleted: Boolean,
    val isActive: Boolean,
    val level: Int,
    val xp: Int,
    val condition: String,
    val rewardType: String,
    val rewardAmount: Int,
)
