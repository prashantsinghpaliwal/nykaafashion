package com.byjusexamprep.newapp.paging

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.byjusexamprep.newapp.databinding.ItemFooterBinding
import com.byjusexamprep.newapp.databinding.ItemProduct1Binding
import com.byjusexamprep.newapp.models.Product
import com.byjusexamprep.newapp.viewmodel.UiModel
import java.util.*


const val TYPE_PRODUCT = 1
const val TYPE_NO_MORE_DATA = 2

class ProductPagingAdapter : PagingDataAdapter<UiModel,
        RecyclerView.ViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == TYPE_PRODUCT) ProductViewHolder(
            ItemProduct1Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ) else FooterViewHolder(
            ItemFooterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is UiModel.ProductItem -> TYPE_PRODUCT
            is UiModel.SeparatorItem -> TYPE_NO_MORE_DATA
            null -> throw UnsupportedOperationException("Unknown view")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val uiModel = getItem(position)
        uiModel.let {
            when (uiModel) {
                is UiModel.ProductItem -> (holder as ProductViewHolder).bind(uiModel.product)
                is UiModel.SeparatorItem -> (holder as FooterViewHolder).bind(uiModel.description)
                else -> {

                }
            }
        }
    }


    inner class ProductViewHolder(val binding: ItemProduct1Binding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            with(binding) {
                Glide.with(itemView.context).load(product.image_url).into(productImage)
                rating.rating = product.rating.toFloat()
                ratingCount.text = "(${product.rating_count})"
                productPrice.text = "₹ ${product.price}"
                productDetail.text = product.name
                title.text = product.name
                ctaBtn.text = product.button_text
                createRandomBg()
            }
        }

        private fun createRandomBg() {
            val shape = GradientDrawable()
            shape.cornerRadius = 16f
            val list = listOf(
                "#E4F2FB", "#E0C9CC", "#B7BAC4",
                "#E4F2FB", "#E0C9CC", "#B7BAC4",
                "#E4F2FB", "#E0C9CC", "#B7BAC4",
                "#E4F2FB", "#E0C9CC", "#B7BAC4",
                "#E4F2FB", "#E0C9CC", "#B7BAC4"
            )

            shape.setColor(
                Color.parseColor(list.random())
            )
            binding.card.background = shape

            val shape2 = GradientDrawable()
            shape2.cornerRadius = 5f

            val rnd = Random()
            shape2.setColor(
                Color.argb(
                    255,
                    rnd.nextInt(256),
                    rnd.nextInt(256),
                    rnd.nextInt(256)
                )
            )

            binding.tag.background = shape2
        }


    }

    inner class FooterViewHolder(private val binding: ItemFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(description: String) {
            with(binding) {
                message.text = description
                progressBar.visibility = View.GONE
                retryButton.visibility = View.GONE
            }
        }
    }


    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<UiModel>() {
            override fun areItemsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
                return (oldItem is UiModel.ProductItem && newItem is UiModel.ProductItem &&
                        oldItem.product.id == newItem.product.id) ||
                        (oldItem is UiModel.SeparatorItem && newItem is UiModel.SeparatorItem &&
                                oldItem.description == newItem.description)
            }

            override fun areContentsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
                return oldItem == newItem
            }
        }
    }

}