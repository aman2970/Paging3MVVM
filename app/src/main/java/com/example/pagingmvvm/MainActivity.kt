package com.example.pagingmvvm

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.pagingmvvm.adapter.RickMortyPagedAdapter
import com.example.pagingmvvm.databinding.ActivityMainBinding
import com.example.pagingmvvm.viewmodel.RickMortyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var mAdapter:RickMortyPagedAdapter
    private val viewModel:RickMortyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRv()

        loadingData()
    }

    private fun loadingData() {
        lifecycleScope.launch {
            viewModel.listData.collect{ pagingData ->
                mAdapter.submitData(pagingData)
            }
        }
    }

    private fun setUpRv(){
        mAdapter = RickMortyPagedAdapter()
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(
                2,StaggeredGridLayoutManager.VERTICAL
            )

            adapter  = mAdapter
            setHasFixedSize(true)
        }
    }
}