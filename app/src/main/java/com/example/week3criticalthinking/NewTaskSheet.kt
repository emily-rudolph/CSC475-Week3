package com.example.week3criticalthinking

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.week3criticalthinking.databinding.FragmentNewTaskSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment



class    NewTaskSheet(var taskobj: TaskObj?, taskViewModel: TaskViewModel) : BottomSheetDialogFragment() {

    private lateinit var fragBinding: FragmentNewTaskSheetBinding
    private var taskViewModel: TaskViewModel = taskViewModel
    private lateinit var myRepository: ViewModelRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myRepository = ViewModelRepository(requireContext())
        fragBinding.saveButton.setOnClickListener{
            saveAction()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_new_task_sheet, container, false)
        return fragBinding.root
    }

    private fun saveAction() {
        val name = fragBinding.name.text.toString()

        if (taskobj == null){
            val newTask = TaskObj(name, false, 42)
            taskViewModel.addTask(newTask)
        }

        fragBinding.name.setText("")
        dismiss()
    }

    private fun newTaskDisplay(){
        fragBinding.taskText.text = "New Task"
    }

    private fun editTaskDisplay(taskobj: TaskObj?){
        fragBinding.taskText.text = "Edit Task"

        //Strings are immutable, so create a new editable type val that holds the value of the name of the taskobj passed to the func.
        val editobj = Editable.Factory.getInstance().newEditable(taskobj!!.name)
        fragBinding.name.text = editobj
    }
}


