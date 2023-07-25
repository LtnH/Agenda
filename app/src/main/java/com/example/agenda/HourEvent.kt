package com.example.agenda

import java.time.LocalTime

class HourEvent(var time: LocalTime, var events: ArrayList<Event>) {

    // No need to define getters and setters in Kotlin, as properties are automatically generated.
    // You can directly access time and events properties using the dot notation.
    // If you need custom behavior for getters/setters, you can use custom property accessors in Kotlin.
}