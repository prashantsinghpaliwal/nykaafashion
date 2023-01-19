package com.byjusexamprep.newapp.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.byjusexamprep.newapp.ui.composables.home.Footer
import com.byjusexamprep.newapp.ui.composables.home.ProductCard
import com.byjusexamprep.newapp.viewmodel.MainViewModel
import com.byjusexamprep.newapp.viewmodel.UiModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeComposable(viewModel = hiltViewModel())
        }
    }

    //    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun HomeComposable(modifier: Modifier = Modifier, viewModel: MainViewModel) {
        val list = viewModel.list.collectAsLazyPagingItems()

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Surface {


                LazyColumn {
                    items(list) { model ->
                        when (model) {
                            is UiModel.ProductItem -> {
                                ProductCard(product = model.product)
                            }

                            is UiModel.SeparatorItem -> {
                                Footer(
                                    footer =
                                    com.byjusexamprep.newapp.models.Footer(
                                        model.description,
                                        "",
                                        false
                                    )
                                ) {

                                }
                            }
                        }

                    }

                    when {

                        list.loadState.refresh is LoadState.Loading -> {
                            item {
                                Column(
                                    modifier = modifier,
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }

                        list.loadState.append is LoadState.NotLoading -> Unit

                        list.loadState.append is LoadState.Loading -> {
                            item {
                                Footer(
                                    footer =
                                    com.byjusexamprep.newapp.models.Footer(
                                        "",
                                        "",
                                        true
                                    )
                                ) {
                                    // handle button click
                                }
                            }
                        }

                        list.loadState.append is LoadState.Error -> {
                            item {
                                Footer(
                                    footer =
                                    com.byjusexamprep.newapp.models.Footer(
                                        (list.loadState.append as LoadState.Error).error.message.toString(),
                                        "Retry",
                                        false
                                    )
                                ) {
                                    // handle button click
                                    list.retry()
                                }
                            }
                        }

                    }


                    when (list.loadState.refresh) {

                    }
                }


            }
        }
    }
}