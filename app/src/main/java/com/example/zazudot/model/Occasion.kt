package com.example.zazudot.model

import java.time.LocalDate

enum class OccasionType(
    val label: String,
    val icon: String
) {
    BIRTHDAY("Birthday", "🎂"),
    WEDDING_ANNIVERSARY("Wedding Anniversary", "💍"),
    WORK_ANNIVERSARY("Work Anniversary", "💼"),
    REMEMBRANCE("Remembrance", "🕊️"),
    FESTIVAL("Festival", "🎉"),
    OTHER("Other", "⭐")
}


data class Occasion(
    val name: String,
    val date: LocalDate,
    val type: OccasionType
)
