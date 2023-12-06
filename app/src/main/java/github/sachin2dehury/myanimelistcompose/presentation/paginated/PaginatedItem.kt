package github.sachin2dehury.myanimelistcompose.presentation.paginated

import android.graphics.Bitmap
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import coil.compose.AsyncImage
import github.sachin2dehury.myanimelistcompose.domain.model.PaginatedModel
import github.sachin2dehury.myanimelistcompose.domain.orZero

@Composable
fun PaginatedItem(modifier: Modifier = Modifier, data: PaginatedModel, callback: () -> Unit) {
    var itemColor by remember {
        mutableStateOf(ItemColor())
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(16.dp))
            .drawBehind {
                drawRect(itemColor.mutedBgColor)
            }
            .clickable {
                callback.invoke()
            }
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = data.images,
            contentDescription = "Image",
            modifier = Modifier
                .aspectRatio(305f / 425)
                .clip(RoundedCornerShape(12.dp)),
            onSuccess = {
                val palette =
                    Palette.from(it.result.drawable.toBitmap().copy(Bitmap.Config.ARGB_8888, true))
                        .generate()
                val dominantBgColor = Color(palette.dominantSwatch?.rgb.orZero())
                val mutedBgColor = Color(palette.mutedSwatch?.rgb.orZero())
                val dominantTextColor = Color(palette.dominantSwatch?.bodyTextColor.orZero())
                val mutedTextColor = Color(palette.mutedSwatch?.bodyTextColor.orZero())
                itemColor = ItemColor(dominantBgColor, mutedBgColor, dominantTextColor, mutedTextColor)
            },
            contentScale = ContentScale.Crop,
        )
        Text(
            text = data.titleEnglish.ifEmpty { data.title }.trim(),
            style = MaterialTheme.typography.titleMedium,
            color = itemColor.mutedTextColor,
            textAlign = TextAlign.Center,
        )

        Text(
            modifier = Modifier
                .drawBehind {
                    drawRect(itemColor.dominantBgColor)
                },
            text = data.titleJapanese.trim(),
            style = MaterialTheme.typography.titleMedium,
            color = itemColor.dominantTextColor,
            textAlign = TextAlign.Center,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Rating: ${data.score}",
                style = MaterialTheme.typography.labelLarge,
                color = itemColor.mutedTextColor,
            )
            Text(
                text = "Rank: ${data.rank}",
                style = MaterialTheme.typography.labelLarge,
                color = itemColor.mutedTextColor,
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Episodes: ${data.episodes}",
                style = MaterialTheme.typography.labelLarge,
                color = itemColor.mutedTextColor,
            )
            Text(
                text = data.duration,
                style = MaterialTheme.typography.labelLarge,
                color = itemColor.mutedTextColor,
            )
        }
    }
}
