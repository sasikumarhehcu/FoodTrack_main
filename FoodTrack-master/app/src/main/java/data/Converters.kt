package com.example.foodtrack.data

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromString(value: String): List<String> {
        return value.split(",").map { it.trim() }
    }

    @TypeConverter
    fun listToString(list: List<String>): String {
        return list.joinToString(",")
    }
}
