package com.example.week3criticalthinking

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.lifecycle.MutableLiveData

private const val taskTabelName = "task_table"
private const val id = "ID"
private const val name = "Name"
private const val compTime = "Completed"

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, "taskList.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {

        val createTableStatement: String
        createTableStatement = "CREATE TABLE $taskTabelName ($id INTEGER PRIMARY KEY AUTOINCREMENT,$name TEXT NOT NULL ,$compTime TEXT)"
        db.execSQL(createTableStatement)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun addTask(taskObj: TaskObj) : Boolean {
        val db: SQLiteDatabase = this.writableDatabase
        var cv: ContentValues = ContentValues()

        cv.put(name, taskObj.name)
        cv.put(id, taskObj.id)
        cv.put(compTime, taskObj.completed.toString())

        val success: Long = db.insert(taskTabelName,null,cv)
        val l: Long = -1

        return success != l
    }

   /* fun getTask(taskObj: TaskObj) : Boolean {
        val db: SQLiteDatabase = this.readableDatabase
        val l: Long = -1
        return success != l
    } */

    fun getAllTasks() : MutableLiveData<MutableList<TaskObj>> {
        val db: SQLiteDatabase = this.readableDatabase

        var list: MutableList<TaskObj> = mutableListOf()
        val queryString: String = "SELECT * FROM $taskTabelName"
        val cursor: Cursor = db.rawQuery(queryString, null)

        if (cursor.moveToFirst()) {
            do {
                val id: Int = cursor.getInt(0)
                val name: String = cursor.getString(1)
                var getCompleted: String? = cursor.getString(2)
                var completed: Boolean = false
                if (!getCompleted.isNullOrBlank() && getCompleted != "null") {
                    completed = getCompleted.toBoolean()
                }

                val task: TaskObj = TaskObj(name, completed, id)

                list.add(task)

            } while (cursor.moveToNext())
        }

        var returnList: MutableLiveData<MutableList<TaskObj>> = MutableLiveData()
        returnList.value = list

        cursor.close()

        return returnList
    }

    fun deleteTask(taskObj: TaskObj): Boolean{
        val db: SQLiteDatabase = this.readableDatabase
        val ident: Int = taskObj.id
        val success: Int = db.delete(taskTabelName, "$id = $ident", null )

        return success != -1
    }
}