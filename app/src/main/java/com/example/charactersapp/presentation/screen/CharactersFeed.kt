package com.example.charactersapp.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.charactersapp.R
import com.example.charactersapp.domain.model.CharacterFeedModel
import com.example.charactersapp.presentation.screen.viewModel.CharactersFeedViewModel
import com.example.charactersapp.presentation.utils.LocalNavController
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Serializable
object CharactersFeed

@Composable
fun CharactersFeedScreen() {

    Scaffold(
        topBar = {

        },
        content = {
            CharactersFeed(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            )
        },
    )
}

@Composable
fun CharactersFeed(
    modifier: Modifier,
    charactersFeedViewModel: CharactersFeedViewModel = koinViewModel()
) {

    val navController = LocalNavController.current

    val charactersPagingItems = charactersFeedViewModel.characters.collectAsLazyPagingItems()

    Column(
        modifier = modifier
    ) {
        when (val state = charactersPagingItems.loadState.refresh) {

            is LoadState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is LoadState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("err")
                }
            }

            else -> {
                if (charactersPagingItems.itemCount == 0) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("empty")
                    }
                } else {
                    LazyColumn() {
                        items(
                            count = charactersPagingItems.itemCount,
                            key = { index -> charactersPagingItems[index]?.name ?: index }
                        ) { index ->
                            val character = charactersPagingItems[index]

                            character?.let {
                                CharacterItem(it)
                            }
                        }

                        if (charactersPagingItems.loadState.append is LoadState.Loading) {
                            item { BottomLoadingIndicator() }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterItem(
    character: CharacterFeedModel,
    modifier: Modifier = Modifier
) {

    val navController = LocalNavController.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable {
                navController.navigate(CharacterDetails(character.id))
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = character.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    InfoChip(
                        label = "Рост",
                        value = "${character.height} см"
                    )
                    InfoChip(
                        label = "Вес",
                        value = "${character.mass} кг"
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    InfoChip(
                        label = "Цвет волос",
                        value = character.hairColor
                    )
                    InfoChip(
                        label = "Цвет глаз",
                        value = character.eyeColor
                    )
                }
            }

            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun InfoChip(
    label: String,
    value: String
) {
    Column {
        Text(
            text = label,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun BottomLoadingIndicator() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(32.dp),
            strokeWidth = 3.dp
        )
    }
}