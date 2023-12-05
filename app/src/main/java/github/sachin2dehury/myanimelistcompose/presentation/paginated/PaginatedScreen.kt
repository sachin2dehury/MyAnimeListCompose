package github.sachin2dehury.myanimelistcompose.presentation.paginated

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import github.sachin2dehury.myanimelistcompose.presentation.ErrorSection

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaginatedScreen(
    modifier: Modifier = Modifier,
    viewModel: PaginatedViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection), topBar = {
        val query = viewModel.state.collectAsState().value.query.orEmpty()
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth(),
            title = {
                BasicTextField(
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    value = query,
                    textStyle = MaterialTheme.typography.bodySmall,
                    onValueChange = {
                        viewModel.updateState(viewModel.state.value.copy(query = it))
                    },
                    singleLine = true,
                )
            },
            navigationIcon = {
                Image(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search"
                )
            },
            scrollBehavior = scrollBehavior
        )
    }) {
        val state = viewModel.state.collectAsState().value
        val pagingState = viewModel.pager.collectAsLazyPagingItems()
        val loadState = pagingState.loadState
        if (loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
        (loadState.refresh as? LoadState.Error)?.let {
            ErrorSection(error = it.error.localizedMessage ?: "Something went wrong!!") {
                pagingState.refresh()
            }
        }
        (loadState.append as? LoadState.Error)?.let {
            ErrorSection(error = it.error.localizedMessage ?: "Something went wrong!!") {
                pagingState.retry()
            }
        }
        val itemCount = pagingState.itemCount
        if (itemCount > 0) {
            LazyVerticalStaggeredGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 48.dp),
                columns = StaggeredGridCells.Fixed(2)
            ) {
                item(span = StaggeredGridItemSpan.FullLine) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp, 12.dp)
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        var dialogType by remember {
                            mutableStateOf<String?>(null)
                        }
                        Button(onClick = {
                            dialogType = "Sorting basis"
                        }) {
                            Text(text = state.sortingBasis)
                        }

                        Button(onClick = {
                            dialogType = "Type"
                        }) {
                            Text(text = state.type)
                        }

                        Button(onClick = {
                            dialogType = "Rating"
                        }) {
                            Text(text = state.rating)
                        }
                        if (!dialogType.isNullOrEmpty()) {
                            val (selected, map) = when (dialogType) {
                                "Sorting basis" -> state.sortingBasis to FilterUiModel.SORTING_BASIS
                                "Type" -> state.type to FilterUiModel.TYPE
                                "Rating" -> state.rating to FilterUiModel.RATING
                                else -> state.sortingBasis to FilterUiModel.SORTING_BASIS
                            }
                            FilterDialog(selected = selected,
                                map = map,
                                onDismissCallback = { dialogType = null }) {
                                when (map) {
                                    FilterUiModel.SORTING_BASIS -> viewModel.updateState(
                                        viewModel.state.value.copy(
                                            sortingBasis = it
                                        )
                                    )

                                    FilterUiModel.TYPE -> viewModel.updateState(
                                        viewModel.state.value.copy(
                                            type = it
                                        )
                                    )

                                    FilterUiModel.RATING -> viewModel.updateState(
                                        viewModel.state.value.copy(
                                            rating = it
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
                items(itemCount) {
                    val item = pagingState[it] ?: return@items
                    PaginatedSection(data = item) {
                        navController.navigate("detail/${item.malId}")
                    }
                }
            }
        }
    }
}

@Composable
fun FilterDialog(
    modifier: Modifier = Modifier,
    mExpanded: Boolean = true,
    selected: String,
    map: Map<String, String?>,
    onDismissCallback: () -> Unit,
    callback: (String) -> Unit
) {
    var expanded by remember {
        mutableStateOf(mExpanded)
    }
    DropdownMenu(modifier = modifier
        .padding(32.dp)
        .clip(RoundedCornerShape(16.dp))
        .wrapContentSize(),
        expanded = expanded,
        onDismissRequest = {
            expanded = false
            onDismissCallback.invoke()
        }) {
        map.keys.forEach {
            Row(modifier = Modifier.clickable {
                callback.invoke(it)
                expanded = false
            }, verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = selected == it, onClick = {
                    callback.invoke(it)
                    expanded = false
                })
                Text(text = it)
            }
        }
    }
}
