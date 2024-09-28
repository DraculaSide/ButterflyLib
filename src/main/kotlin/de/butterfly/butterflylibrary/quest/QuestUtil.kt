package de.butterfly.butterflylibrary.quest
@Suppress("unused")
class QuestUtil {

    private val questProgress:MutableMap<Int,Boolean> = mutableMapOf()
    fun setQuestProgress(questID: Int, isQuestCompleted: Boolean) {
        questProgress[questID] = isQuestCompleted
    }

    fun isQuestCompleted(questID: Int): Boolean {
        return questProgress[questID] ?: false
    }
}