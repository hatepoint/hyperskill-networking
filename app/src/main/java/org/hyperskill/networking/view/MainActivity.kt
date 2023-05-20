package org.hyperskill.networking.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import org.hyperskill.networking.R
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

        // Use the lifecycleScope to launch a coroutine when the activity is started
        lifecycleScope.launchWhenStarted {
            vm.uiState.collect {
                when (it) {
                    is MainState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        adapter.items = it.drinks
                    }
                    is MainState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        showSnackbar(it.error)
                    }
                    is MainState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
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
}
