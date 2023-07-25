package com.example.agenda.repository

import androidx.lifecycle.LiveData
import com.example.agenda.dao.EventDao
import com.example.agenda.models.Event
import java.util.concurrent.Flow

class EventRepository(private val eventDao: EventDao) {

    val allEvents: LiveData<List<Event>> = eventDao.getAllEvents()

    suspend fun insert(event: Event) {
        eventDao.insert(event)
    }


}