package com.dnc.buendeal.core.utils

import androidx.room.TypeConverter
import java.util.*

object DateConverter {
    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return dateLong?.let { Date(it) }
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
}
