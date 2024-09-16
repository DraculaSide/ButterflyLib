package de.butterfly.butterflyapi.util

@Suppress("unused")
enum class ConsoleColors(private val ansiCode: String) {
    RESET("\u001B[0m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m");

    private fun format(message: String) : String = "$ansiCode$message${RESET.ansiCode}"

    fun coloredMessage(message: String) = print(format(message))

    companion object {
        fun info(message: String) = print(CYAN.format("INFO: ") + WHITE.format(message))
        fun error(message: String) = print(RED.format("ERROR: ") + WHITE.format(message))
        fun success(message: String) = print(GREEN.format("SUCCESS: ") + WHITE.format(message))
        fun warning(message: String) = print(YELLOW.format("WARNING: ") + WHITE.format(message))
        fun log(message: String) = print(WHITE.format(message))
    }
}