package com.example.agenda.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agenda.models.Event
import com.example.agenda.repository.EventRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers

class EventViewModel(private val repository: EventRepository) : ViewModel() {

    val allEvents = repository.allEvents

    fun insert(event: Event) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(event)
    }


}
