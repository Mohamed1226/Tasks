package com.example.tasks.data.task.local_data_base

import androidx.room.TypeConverter

class ColorListConverter {

    @TypeConverter
    fun fromColorList(colorList: List<Int>): String {
        return colorList.joinToString(",") { it.toString() }
    }

    @TypeConverter
    fun toColorList(colorString: String): List<Int> {
        if (colorString.isEmpty()) {
            return emptyList()
        }

        return colorString.split(",").mapNotNull {
            try {
                it.toInt()
            } catch (e: NumberFormatException) {
                null // Ignore invalid numbers
            }
        }
    }
}