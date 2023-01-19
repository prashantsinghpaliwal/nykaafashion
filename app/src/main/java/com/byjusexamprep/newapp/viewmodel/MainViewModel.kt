package com.byjusexamprep.newapp.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import com.byjusexamprep.newapp.models.Product
import com.byjusexamprep.newapp.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val productRepository: ProductRepository) : ViewModel() {

    val list = productRepository.getProducts()
        .map { pagingData ->
            pagingData.map {
                UiModel.ProductItem(it)
            }
        }.map {
            it.insertSeparators { before, after ->
                if (after == null) {
                    // we're at the end of the list
                    return@insertSeparators UiModel.SeparatorItem("No More Products")
                }

                if (before == null) {
                    // we're at the beginning of the list
                    return@insertSeparators null
                } else {
                    // no separator
                    null
                }
            }
        }.cachedIn(viewModelScope)

}

sealed class UiModel {
    data class ProductItem(val product: Product) : UiModel()
    data class SeparatorItem(val description: String) : UiModel()
}