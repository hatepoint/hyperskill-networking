package org.hyperskill.networking.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import org.hyperskill.networking.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // Declare binding and view model variables
    private lateinit var binding: ActivityMainBinding
    private val vm by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = OrderAdapter()

        binding.recyclerView.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(context)
        }

        adapter.items = vm.drinks

    }
}