package com.example.week3criticalthinking

import android.widget.ImageButton
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.week3criticalthinking.databinding.FragmentTaskContainerBinding

class RecycleViewTaskHolder(val taskBinding: FragmentTaskContainerBinding): RecyclerView.ViewHolder(taskBinding.root) {

    val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
    val deleteButton: ImageButton = itemView.findViewById<ImageButton>(R.id.deleteButton)

    fun bindData(taskObj: TaskObj){
        taskBinding.checkBox.text = taskObj.name
    }
}