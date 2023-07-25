package com.example.agenda.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "event_table")
data class Event(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val title: String,
    val description: String,
    val date: Date,
    val startTime: Date,
    val endTime: Date,
    val category: String,

    )