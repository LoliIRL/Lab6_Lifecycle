package com.example.unscramble

fun getUnscrambledWord(scrambledWord: String): String {
    val allWords = listOf(
        "apple", "banana", "cherry", "date", "elderberry",
        "fig", "grape", "honeydew", "kiwi", "lemon"
    )
    val sortedChars = scrambledWord.toCharArray().sortedArray()
    return allWords.first { it.toCharArray().sortedArray().contentEquals(sortedChars) }
}