package com.example.zazudot.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "occasions")
data class OccasionEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val name: String,

    /**
     * Stored as ISO string: yyyy-MM-dd
     */
    val date: String,

    /**
     * Stored as enum name: BIRTHDAY, FESTIVAL, etc.
     */
    val type: String
)
