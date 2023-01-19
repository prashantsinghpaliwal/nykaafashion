package com.byjusexamprep.newapp.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.filter
import androidx.paging.map
import androidx.recyclerview.widget.GridLayoutManager
import com.byjusexamprep.newapp.databinding.ActivityMainBinding
import com.byjusexamprep.newapp.paging.FooterAdapter
import com.byjusexamprep.newapp.paging.ProductPagingAdapter
import com.byjusexamprep.newapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private var pagingAdapter: ProductPagingAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        with(binding) {
            setContentView(root)
            setUpObservers()
            setUpList()
        }
    }

    private fun setUpList() {
        pagingAdapter = ProductPagingAdapter()
        val footerAdapter = FooterAdapter {
            pagingAdapter?.retry()
        }

        val concatAdapter = pagingAdapter?.withLoadStateFooter(footer = footerAdapter)
        val layoutManager = GridLayoutManager(this@MainActivity, 2)

        with(binding) {
            progressBar.visibility = View.VISIBLE
            productList.layoutManager = layoutManager
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if ((position == concatAdapter?.itemCount?.minus(1))) {
                        // if it is the last position and we have a footer
                        2
                    } else {
                        1
                    }
                }
            }

            productList.adapter = concatAdapter
            productList.setHasFixedSize(true)

            pagingAdapter?.addLoadStateListener { loadStates ->
                progressBar.isVisible = loadStates.source.refresh is LoadState.Loading
                retryButton.isVisible = loadStates.source.refresh is LoadState.Error
            }

            retryButton.setOnClickListener {
                pagingAdapter?.retry()
            }
        }

    }

    private fun setUpObservers() {
        mainViewModel.list.observe(this@MainActivity) {
            pagingAdapter?.submitData(lifecycle = lifecycle, pagingData = it)
            binding.progressBar.visibility = View.GONE
        }
    }
}