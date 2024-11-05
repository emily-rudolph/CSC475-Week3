package com.example.week3criticalthinking

import android.content.Context
import androidx.lifecycle.MutableLiveData

class ViewModelRepository(context: Context) {
    var dbHelper: DataBaseHelper = DataBaseHelper(context)
    var tasksList: MutableLiveData<MutableList<TaskObj>> = dbHelper.getAllTasks()
}