package com.example.vanillastarter.shared

enum class SortType {
    NAME_ASC,
    NAME_DESC,
    FREQUENCY_ASC,
    FREQUENCY_DESC
}

fun SortType.toText(): String {
    return when (this) {
        SortType.NAME_ASC -> "Name (Ascending)"
        SortType.NAME_DESC -> "Name (Descending)"
        SortType.FREQUENCY_ASC -> "Frequency (Ascending)"
        SortType.FREQUENCY_DESC -> "Frequency (Descending)"
    }
}