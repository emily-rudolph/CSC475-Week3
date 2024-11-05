package com.example.week3criticalthinking


import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week3criticalthinking.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var myRepository: ViewModelRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        //mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        myRepository = ViewModelRepository(this)
        taskViewModel = ViewModelProvider(
            this, TaskViewModelFactory(myRepository, applicationContext)
        )[TaskViewModel::class.java]

        val buttonClick = mainBinding.taskAddButton
        buttonClick.setOnClickListener {
            NewTaskSheet(null, taskViewModel).show(supportFragmentManager, "newTask")
        }

        makeRecycleView()

    }

    fun makeRecycleView(){
        taskViewModel.tasksList.observe(this){
            mainBinding.recyclerView.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = RecycleViewTaskAdapter(it, taskViewModel)
            }
        }
    }
}
