package com.example.agenda

import android.util.Log
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.ArrayList

object CalendarUtils {
    var selectedDate: LocalDate? = null

    fun formattedDate(date: LocalDate?): String? {
        val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
        return date?.format(formatter)
    }

    fun formattedTime(time: LocalTime?): String? {
        val formatter = DateTimeFormatter.ofPattern("hh:mm:ss a")
        return time?.format(formatter)
    }

    fun formattedShortTime(time: LocalTime?): String? {
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        return time?.format(formatter)
    }

    fun monthYearFromDate(date: LocalDate?): String? {
        val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date?.format(formatter)
    }

    fun monthDayFromDate(date: LocalDate?): String? {
        val formatter = DateTimeFormatter.ofPattern("MMMM d")
        return date?.format(formatter)
    }

    fun daysInMonthArray(): ArrayList<LocalDate?> {
        val daysInMonthArray = ArrayList<LocalDate?>()

        selectedDate?.let {
            val yearMonth = YearMonth.from(it)
            val daysInMonth = yearMonth.lengthOfMonth()

            val prevMonth = it.minusMonths(1)
            val nextMonth = it.plusMonths(1)

            val prevYearMonth = YearMonth.from(prevMonth)
            val prevDaysInMonth = prevYearMonth.lengthOfMonth()

            val firstOfMonth = it.withDayOfMonth(1)
            val dayOfWeek = firstOfMonth.dayOfWeek.value

            for (i in 1..42) {
                when {
                    i <= dayOfWeek -> daysInMonthArray.add(
                        LocalDate.of(
                            prevMonth.year,
                            prevMonth.monthValue,
                            prevDaysInMonth + i - dayOfWeek
                        )
                    )
                    i > daysInMonth + dayOfWeek -> daysInMonthArray.add(
                        LocalDate.of(
                            nextMonth.year,
                            nextMonth.monthValue,
                            i - dayOfWeek - daysInMonth
                        )
                    )
                    else -> daysInMonthArray.add(
                        LocalDate.of(
                            it.year,
                            it.monthValue,
                            i - dayOfWeek
                        )
                    )
                }
            }
        }

        return daysInMonthArray
    }

    fun daysInWeekArray(selectedDate: LocalDate?): ArrayList<LocalDate?> {
        val days = ArrayList<LocalDate?>()
        var current = sundayForDate(selectedDate)
        val endDate = current?.plusWeeks(1)

        while (current != null && current.isBefore(endDate)) {
            days.add(current)
            current = current.plusDays(1)
        }
        return days
    }

    private fun sundayForDate(current: LocalDate?): LocalDate? {
        var oneWeekAgo = current?.minusWeeks(1)
        var newCurrent = current
        while (newCurrent?.isAfter(oneWeekAgo) == true) {
            if (newCurrent.dayOfWeek == DayOfWeek.SUNDAY) {
                return newCurrent
            }
            newCurrent = newCurrent.minusDays(1)
        }
        return null
    }
}
