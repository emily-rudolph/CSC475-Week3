package com.example.week3criticalthinking

class TaskObj(
    var name: String,
    var completed: Boolean,
    val id: Int
) {
    fun isCompleted(): Boolean {
        return completed
    }
}
