package github.sachin2dehury.myanimelistcompose.presentation.paginated

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
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
                        .height(48.dp)
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    value = query,
                    textStyle = MaterialTheme.typography.bodySmall,
                    onValueChange = viewModel::updateState,
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
        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .fillMaxSize(),
            columns = StaggeredGridCells.Fixed(2)
        ) {
            items(itemCount) {
                val item = pagingState[it] ?: return@items
                PaginatedSection(data = item) {
                    navController.navigate("detail/${item.malId}")
                }
            }
        }
    }
}
