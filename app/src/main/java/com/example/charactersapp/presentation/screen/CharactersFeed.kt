package com.example.charactersapp.presentation.screen

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(Color.Black)
                    .statusBarsPadding()
                    .padding(horizontal = 16.dp)
                    .height(48.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = stringResource(R.string.characters_title),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            }
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
            .background(Color.Black)
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
                    Text(stringResource(R.string.error_loading))
                }
            }

            else -> {
                if (charactersPagingItems.itemCount == 0) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(stringResource(R.string.empty_list))
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
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = character.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    InfoChip(
                        label = stringResource(R.string.height),
                        value = "${character.height} см"
                    )
                    InfoChip(
                        label = stringResource(R.string.mass),
                        value = "${character.mass} кг"
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    InfoChip(
                        label = stringResource(R.string.hair_color),
                        value = character.hairColor
                    )
                    InfoChip(
                        label = stringResource(R.string.eye_color),
                        value = character.eyeColor
                    )
                }
            }

            Icon(
                painter = painterResource(id = R.drawable.ic_character),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Composable
private fun InfoChip(
    label: String,
    value: String
) {
    Column(
        modifier = Modifier
            .width(110.dp)
            .background(
                color = Color.Black,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 8.dp, vertical = 6.dp)
    ) {
        Text(
            text = label,
            fontSize = 11.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            fontSize = 13.sp,
            color = Color.White,
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