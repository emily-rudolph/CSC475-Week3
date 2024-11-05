package com.example.week3criticalthinking

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TaskViewModel(myRepository: ViewModelRepository, context: Context): ViewModel() {

    var tasksList: MutableLiveData<MutableList<TaskObj>> = myRepository.tasksList
    val databasehelper: DataBaseHelper = DataBaseHelper(context)

    fun addTask(taskObj: TaskObj){
        databasehelper.addTask(taskObj)

        var currentList = tasksList.value?: mutableListOf()
        currentList.add(taskObj)
        tasksList.value = currentList

    }

    fun deleteTask(taskObj: TaskObj){
        databasehelper.deleteTask(taskObj)

        var currentList = tasksList.value?: mutableListOf()
        currentList.remove(taskObj)
        tasksList.value = currentList

    }

    fun completeTask(taskObj: TaskObj, isChecked: Boolean) {
        if (isChecked) {
            taskObj.completed = true
        } else {
            taskObj.completed = false
        }
    }
}

class TaskViewModelFactory(private val myRepository: ViewModelRepository, private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            return TaskViewModel(myRepository, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

