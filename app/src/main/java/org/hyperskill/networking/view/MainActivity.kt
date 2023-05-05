package org.hyperskill.networking.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import org.hyperskill.networking.R
import org.hyperskill.networking.databinding.ActivityMainBinding
import org.hyperskill.networking.model.DrinkRepository
import org.hyperskill.networking.model.models.Drink

class MainActivity : AppCompatActivity() {
    // Declare binding and view model variables
    private lateinit var binding: ActivityMainBinding
    private lateinit var vmFactory: MainViewModelFactory
    private val vm by viewModels<MainViewModel>(factoryProducer = { vmFactory })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize view model
        val repository = DrinkRepository()
        vmFactory = MainViewModelFactory(repository)
        //vm = ViewModelProvider(this, vmFactory).get(MainViewModel::class.java)

        val adapter = OrderAdapter()

        binding.recyclerView.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(context)
        }

        // Use the lifecycleScope to launch a coroutine when the activity is started
        lifecycleScope.launchWhenStarted {
            vm.uiState.collect {
                when (it) {
                    is MainState.Success -> {
                        setProgressBar(false)
                        adapter.items = it.drinks
                    }
                    is MainState.Error -> {
                        setProgressBar(false)
                        showSnackbar(it.error)
                    }
                    is MainState.Loading -> {
                        setProgressBar(true)
                    }
                }
            }
        }
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(
            findViewById(R.id.layout),
            "Error: $message",
            Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun setProgressBar(visibility: Boolean) {
        binding.progressBar.visibility = if (visibility) {
            android.view.View.VISIBLE
        } else {
            android.view.View.GONE
        }
    }
}
