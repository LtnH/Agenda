package com.example.agenda

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.ArrayList

object CalendarUtils {
    var selectedDate: LocalDate? = null
    fun formattedShortTime(time: LocalTime?): String? {
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        return time?.format(formatter)
    }

    fun monthDayFromDate(date: LocalDate?): String? {
        val formatter = DateTimeFormatter.ofPattern("MMMM d")
        return date?.format(formatter)
    }

    fun formattedDate(date: LocalDate?): String? {
        val formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
        return date?.format(formatter)
    }

    fun formattedTime(time: LocalTime?): String? {
        val formatter = DateTimeFormatter.ofPattern("hh:mm:ss a")
        return time?.format(formatter)
    }

    fun monthYearFromDate(date: LocalDate?): String? {
        val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date?.format(formatter)
    }

    fun daysInMonthArray(): ArrayList<LocalDate> {
        val daysInMonthArray = ArrayList<LocalDate>()

        val yearMonth = YearMonth.from(selectedDate)
        val daysInMonth = yearMonth.lengthOfMonth()

        val prevMonth = selectedDate!!.minusMonths(1)
        val nextMonth = selectedDate!!.plusMonths(1)

        val prevYearMonth = YearMonth.from(prevMonth)
        val prevDaysInMonth = prevYearMonth.lengthOfMonth()

        val firstOfMonth = selectedDate!!.withDayOfMonth(1)
        val dayOfWeek = firstOfMonth.dayOfWeek.value

        for (i in 1..42) {
            when {
                i <= dayOfWeek -> daysInMonthArray.add(
                    LocalDate.of(
                        prevMonth.year,
                        prevMonth.month,
                        prevDaysInMonth + i - dayOfWeek
                    )
                )
                i > daysInMonth + dayOfWeek -> daysInMonthArray.add(
                    LocalDate.of(
                        nextMonth.year,
                        nextMonth.month,
                        i - dayOfWeek - daysInMonth
                    )
                )
                else -> daysInMonthArray.add(
                    LocalDate.of(
                        selectedDate!!.year,
                        selectedDate!!.month,
                        i - dayOfWeek
                    )
                )
            }
        }
        return daysInMonthArray
    }

    fun daysInWeekArray(selectedDate: LocalDate?): ArrayList<LocalDate?> {
        val days = ArrayList<LocalDate?>()
        var current = sundayForDate(selectedDate)
        val endDate = current?.plusWeeks(1)
        Log.d("test", endDate.toString())
        Log.d("test", current.toString())
        Log.d("test", current?.isBefore(endDate).toString())
        Log.d("test", days.toString())

        while (current?.isBefore(endDate) == true) {
            Log.d("test", "pourquoi tu passe connard")
            days.add(current)
            current = current.plusDays(1)
        }
        return days
    }

    private fun sundayForDate(current: LocalDate?): LocalDate? {
        var oneWeekAgo = current?.minusWeeks(1)
        var newCurrent = current
        Log.d("test2", newCurrent.toString())
        Log.d("test2", current.toString())
        Log.d("test2", oneWeekAgo.toString())

        while (newCurrent?.isAfter(oneWeekAgo) == true) {
            if (newCurrent.dayOfWeek == DayOfWeek.SUNDAY) {
                return newCurrent
            }
            newCurrent = newCurrent.minusDays(1)
        }
        return null
    }
}
