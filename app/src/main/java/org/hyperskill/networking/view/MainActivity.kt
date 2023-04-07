package org.hyperskill.networking.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.hyperskill.networking.R
import org.hyperskill.networking.databinding.ActivityMainBinding
import org.hyperskill.networking.model.DrinkRepository

class MainActivity : AppCompatActivity() {
    // Declare binding and view model variables
    private lateinit var binding: ActivityMainBinding
    private lateinit var vm: MainViewModel
    private lateinit var vmFactory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize view model
        val repository = DrinkRepository()
        vmFactory = MainViewModelFactory(repository)
        vm = ViewModelProvider(this, vmFactory).get(MainViewModel::class.java)

        val adapter = OrderAdapter()

        binding.recyclerView.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(context)
        }

        adapter.items = vm.drinks

    }
}