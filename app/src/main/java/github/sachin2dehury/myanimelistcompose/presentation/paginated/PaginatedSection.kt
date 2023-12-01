package github.sachin2dehury.myanimelistcompose.presentation.paginated

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import coil.compose.AsyncImage
import github.sachin2dehury.myanimelistcompose.domain.model.PaginatedModel
import github.sachin2dehury.myanimelistcompose.domain.orZero

@Composable
fun PaginatedSection(modifier: Modifier = Modifier, data: PaginatedModel, callback: () -> Unit) {
    var dominantBgColor by remember {
        mutableStateOf(Color.Transparent)
    }
    var mutedBgColor by remember {
        mutableStateOf(Color.Transparent)
    }
    var dominantTextColor by remember {
        mutableStateOf(Color.Transparent)
    }
    var mutedTextColor by remember {
        mutableStateOf(Color.Transparent)
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(mutedBgColor)
            .padding(16.dp)
            .clickable {
                callback.invoke()
            }
    ) {
        AsyncImage(
            model = data.images,
            contentDescription = "Image",
            modifier = Modifier
                .aspectRatio(305f / 425)
                .clip(RoundedCornerShape(16.dp)),
            onSuccess = {
                val palette = Palette.from(it.result.drawable.toBitmap()).generate()
                dominantBgColor = Color(palette.dominantSwatch?.rgb.orZero())
                mutedBgColor = Color(palette.mutedSwatch?.rgb.orZero())
                dominantTextColor = Color(palette.dominantSwatch?.bodyTextColor.orZero())
                mutedTextColor = Color(palette.mutedSwatch?.bodyTextColor.orZero())
            }
        )
        Text(
            modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.CenterHorizontally),
            text = data.titleEnglish.ifEmpty { data.title },
            style = MaterialTheme.typography.titleMedium,
            color = mutedTextColor
        )

        Text(
            modifier = Modifier
                .padding(top = 12.dp)
                .background(dominantBgColor)
                .align(Alignment.CenterHorizontally),
            text = data.titleJapanese,
            style = MaterialTheme.typography.titleMedium,
            color = dominantTextColor
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Rating : ${data.score}",
                style = MaterialTheme.typography.bodySmall,
                color = mutedTextColor
            )
            Text(
                text = "Rank : ${data.rank}",
                style = MaterialTheme.typography.bodySmall,
                color = mutedTextColor
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Episodes : ${data.episodes}",
                style = MaterialTheme.typography.bodySmall,
                color = mutedTextColor
            )
            Text(
                text = data.duration,
                style = MaterialTheme.typography.bodySmall,
                color = mutedTextColor
            )
        }
    }
}