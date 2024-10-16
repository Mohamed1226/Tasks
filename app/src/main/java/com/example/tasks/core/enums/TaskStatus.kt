package com.example.tasks.core.enums



enum class TaskStatus {
    Recent, UpComing, Closed;

    companion object {
        fun getStatusByName(name: String) =
            entries.firstOrNull { it.name == name } ?: TaskStatus.Recent
    }
}