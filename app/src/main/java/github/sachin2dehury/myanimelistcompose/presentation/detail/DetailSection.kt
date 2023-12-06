package github.sachin2dehury.myanimelistcompose.presentation.detail

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import coil.compose.AsyncImage
import github.sachin2dehury.myanimelistcompose.domain.model.DetailModel
import github.sachin2dehury.myanimelistcompose.domain.orZero

@Composable
fun DetailSection(modifier: Modifier = Modifier, data: DetailModel) {
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
            .verticalScroll(rememberScrollState())
            .background(mutedBgColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp),

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
                dominantBgColor = Color(palette.dominantSwatch?.rgb.orZero())
                mutedBgColor = Color(palette.mutedSwatch?.rgb.orZero())
                dominantTextColor = Color(palette.dominantSwatch?.bodyTextColor.orZero())
                mutedTextColor = Color(palette.mutedSwatch?.bodyTextColor.orZero())
            },
            contentScale = ContentScale.Crop,
        )
        Text(
            text = data.titleEnglish.ifEmpty { data.title },
            style = MaterialTheme.typography.titleLarge,
            color = mutedTextColor,
            textAlign = TextAlign.Center,
        )

        Text(
            modifier = Modifier
                .background(dominantBgColor),
            text = data.titleJapanese,
            style = MaterialTheme.typography.titleLarge,
            color = dominantTextColor,
            textAlign = TextAlign.Center,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Rating: ${data.score}",
                style = MaterialTheme.typography.labelMedium,
                color = mutedTextColor,
            )
            Text(
                text = "Rank: ${data.rank}",
                style = MaterialTheme.typography.labelMedium,
                color = mutedTextColor,
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "Episodes: ${data.episodes}",
                style = MaterialTheme.typography.labelMedium,
                color = mutedTextColor,
            )
            Text(
                text = data.duration,
                style = MaterialTheme.typography.labelMedium,
                color = mutedTextColor,
            )
        }

        if (data.aired.isNotEmpty()) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Air: ${data.aired}",
                style = MaterialTheme.typography.labelMedium,
                color = mutedTextColor,
            )
        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Genres: ${data.genres}",
            style = MaterialTheme.typography.labelMedium,
            color = mutedTextColor,
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Rating: ${data.rating}",
            style = MaterialTheme.typography.labelMedium,
            color = mutedTextColor,
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Background: \n\t\t${data.background}",
            style = MaterialTheme.typography.labelLarge,
            color = mutedTextColor,
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Synopsis: \n\t\t${data.synopsis}",
            style = MaterialTheme.typography.labelLarge,
            color = mutedTextColor,
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Opening: \n${data.openingTheme.joinToString("\n")}",
            style = MaterialTheme.typography.labelMedium,
            color = mutedTextColor,
        )

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Ending: \n${data.endingTheme.joinToString("\n")}",
            style = MaterialTheme.typography.labelMedium,
            color = mutedTextColor,
        )
    }
}
