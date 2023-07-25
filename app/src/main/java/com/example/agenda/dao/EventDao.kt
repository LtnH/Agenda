package com.example.agenda.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.agenda.models.Event

@Dao
interface EventDao {
    @Query("SELECT * FROM event_table ORDER BY date ASC")
    fun getAllEvents(): LiveData<List<Event>>

    @Insert
     fun insert(event: Event)

    @Update
     fun update(event: Event)

    @Delete
     fun delete(event: Event)
}