package com.byjusexamprep.newapp.paging

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.byjusexamprep.newapp.databinding.ItemFooterBinding

class FooterAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<FooterAdapter.FooterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): FooterViewHolder {
        return FooterViewHolder(
            ItemFooterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FooterViewHolder, loadState: LoadState) {
        holder.bind1(loadState)
    }


    inner class FooterViewHolder(private val binding: ItemFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            with(binding) {
                if (loadState.endOfPaginationReached) {
                    message.text = "No more products"
                    progressBar.visibility = View.GONE
                } else {
                    progressBar.visibility = View.VISIBLE
                    message.text = "Loading more products"
                }
            }
        }

        fun bind1(loadState: LoadState) {
            with(binding) {
                if (loadState is LoadState.Error) {
                    message.text = loadState.error.localizedMessage
                } else {
                    message.text = "Loading more products"
                }

                progressBar.isVisible = loadState is LoadState.Loading
                retryButton.isVisible = loadState is LoadState.Error
                retryButton.setOnClickListener { retry.invoke() }

//                Log.v("LoadState in footer", "${loadState}")
            }
        }

    }

}