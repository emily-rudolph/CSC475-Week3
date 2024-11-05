package com.example.week3criticalthinking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week3criticalthinking.databinding.FragmentTaskContainerBinding

class RecycleViewTaskAdapter(private val tasksList: List<TaskObj>, val taskViewModel: TaskViewModel): RecyclerView.Adapter<RecycleViewTaskHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycleViewTaskHolder {
        val taskBinding = FragmentTaskContainerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecycleViewTaskHolder(taskBinding)
    }

    override fun getItemCount(): Int {
       return tasksList.size
    }

    override fun onBindViewHolder(holder: RecycleViewTaskHolder, position: Int) {
        val item = tasksList[position]
        holder.checkBox.isChecked = item.isCompleted()

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            taskViewModel.completeTask(item, isChecked)
        }

        holder.deleteButton.setOnClickListener{
            taskViewModel.deleteTask(item)
        }

        holder.bindData(tasksList[position])
    }
}