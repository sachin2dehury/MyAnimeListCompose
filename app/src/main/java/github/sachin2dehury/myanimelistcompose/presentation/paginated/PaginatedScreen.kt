package github.sachin2dehury.myanimelistcompose.presentation.paginated

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import github.sachin2dehury.myanimelistcompose.presentation.ErrorSection
import kotlinx.coroutines.flow.toList

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaginatedScreen(
    modifier: Modifier = Modifier,
    viewModel: PaginatedViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            val query = viewModel.state.collectAsState().value.query.orEmpty()
            SearchSection(query = query, viewModel = viewModel, scrollBehavior = scrollBehavior)
        },
    ) { paddingValues ->

        val state = viewModel.state.collectAsState().value
        val pagingState = viewModel.pager.collectAsLazyPagingItems()
        val loadState = pagingState.loadState
        val itemCount = pagingState.itemCount

        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalItemSpacing = 8.dp,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item(span = StaggeredGridItemSpan.FullLine) {
                if (itemCount > 0) {
                    FilterSection(state = state, viewModel = viewModel)
                }
            }
            items(itemCount) {
                val item = pagingState[it] ?: return@items
                PaginatedItem(data = item) {
                    navController.navigate("detail/${item.malId}")
                }
            }

            item(span = StaggeredGridItemSpan.FullLine) {
                if (loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading) {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }
            }

            item(span = StaggeredGridItemSpan.FullLine) {
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
            }
        }
    }
}
