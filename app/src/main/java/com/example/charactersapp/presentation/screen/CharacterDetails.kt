package com.example.charactersapp.presentation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.charactersapp.R
import com.example.charactersapp.domain.model.CharacterModel
import com.example.charactersapp.domain.model.FilmModel
import com.example.charactersapp.domain.model.SpeciesModel
import com.example.charactersapp.domain.model.StarshipsModel
import com.example.charactersapp.domain.model.VehiclesModel
import com.example.charactersapp.presentation.screen.viewModel.CharacterDetailsState
import com.example.charactersapp.presentation.screen.viewModel.CharacterDetailsViewModel
import com.example.charactersapp.presentation.utils.LocalNavController
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data class CharacterDetails(val id: String)

@Composable
fun CharacterDetailsScreen(
    characterDetails: CharacterDetails
) {

    val navController = LocalNavController.current

    Scaffold(
        topBar = {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(Color.Black)
                    .statusBarsPadding()
                    .fillMaxWidth(),
            ) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_back),
                        tint = Color.White,
                        contentDescription = "",
                    )
                }
                Text(
                    text = stringResource(R.string.information),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            }
        },
        content = {
            CharacterDetails(
                id = characterDetails.id,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            )
        },
    )
}

@Composable
fun CharacterDetails(
    id: String,
    characterDetailsViewModel: CharacterDetailsViewModel = koinViewModel(),
    modifier: Modifier
) {

    val characterDetails by characterDetailsViewModel.characterDetailsState.collectAsState()

    LaunchedEffect(Unit) {
        characterDetailsViewModel.loadCharacter(id)
    }

    Column(
        modifier = modifier
            .background(Color.Black)
    ) {
        when (val data = characterDetails) {

            is CharacterDetailsState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is CharacterDetailsState.Success -> {
                val character = data.character

                CharacterDetailsContent(character = character)
            }

            is CharacterDetailsState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.error_loading),
                            fontSize = 18.sp,
                            color = Color.Red
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { /* retry logic */ }
                        ) {
                            Text(stringResource(R.string.retry))
                        }
                    }
                }
            }

            null -> Unit
        }
    }
}

@Composable
fun CharacterDetailsContent(
    character: CharacterModel?
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Column {
                Text(
                    text = character?.name ?: "",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = listOfNotNull(
                        character?.gender,
                        character?.birthYear
                    ).joinToString(" • "),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        item {
            Card(
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(2.dp),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    DetailRow(
                        label = stringResource(R.string.height),
                        value = "${character?.height} см"
                    )
                    DetailRow(
                        label = stringResource(R.string.mass),
                        value = "${character?.mass} кг"
                    )
                    DetailRow(
                        label = stringResource(R.string.hair_color),
                        value = character?.hairColor ?: ""
                    )
                    DetailRow(
                        label = stringResource(R.string.skin_color),
                        value = character?.skinColor ?: ""
                    )
                    DetailRow(
                        label = stringResource(R.string.eye_color),
                        value = character?.eyeColor ?: ""
                    )
                }
            }
        }

        character?.homeworld?.let { planet ->
            item {
                ExpandableSection(title = stringResource(R.string.homeworld)) {
                    DetailRow(
                        label = stringResource(R.string.name),
                        value = planet.name
                    )
                    DetailRow(
                        label = stringResource(R.string.climate),
                        value = planet.climate
                    )
                    DetailRow(
                        label = stringResource(R.string.terrain),
                        value = planet.terrain
                    )
                    DetailRow(
                        label = stringResource(R.string.population),
                        value = planet.population
                    )
                }
            }
        }

        if (!character?.films.isNullOrEmpty()) {
            item {
                ExpandableSection(title = stringResource(R.string.films)) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        character.films.forEach { film ->
                            FilmCard(film = film)
                        }
                    }
                }
            }
        }

        if (!character?.species.isNullOrEmpty()) {
            item {
                ExpandableSection(title = stringResource(R.string.species)) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        character.species.forEach { species ->
                            SpeciesCard(species = species)
                        }
                    }
                }
            }
        }

        if (!character?.vehicles.isNullOrEmpty()) {
            item {
                ExpandableSection(title = stringResource(R.string.vehicles)) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        character.vehicles.forEach { vehicle ->
                            VehicleCard(vehicle = vehicle)
                        }
                    }
                }
            }
        }

        if (!character?.starships.isNullOrEmpty()) {
            item {
                ExpandableSection(title = stringResource(R.string.starships)) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        character.starships.forEach { starship ->
                            StarshipCard(starship = starship)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DetailRow(
    label: String,
    value: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = value.ifEmpty { stringResource(R.string.unknown) },
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun ExpandableSection(
    title: String,
    modifier: Modifier = Modifier,
    initiallyExpanded: Boolean = false,
    content: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(initiallyExpanded) }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable { expanded = !expanded }
                .padding(16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f),
                    color = Color.White
                )

                Icon(
                    painter = if (expanded) painterResource(R.drawable.ic_arrow_up)
                    else painterResource(R.drawable.ic_arrow_down),
                    contentDescription = null
                )
            }

            AnimatedVisibility(
                visible = expanded,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Column(
                    modifier = Modifier.padding(top = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    content()
                }
            }
        }
    }
}

@Composable
fun FilmCard(film: FilmModel?) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = film?.title ?: "",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = stringResource(R.string.episode_format, film?.episodeId ?: ""),
                fontSize = 12.sp
            )
            Text(
                text = stringResource(R.string.director_format, film?.director ?: ""),
                fontSize = 12.sp
            )
            Text(
                text = stringResource(R.string.release_date_format, film?.releaseDate ?: ""),
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun SpeciesCard(species: SpeciesModel?) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = species?.name ?: "",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.classification_format, species?.classification ?: ""),
                fontSize = 12.sp
            )
            Text(
                text = stringResource(R.string.language_format, species?.language ?: ""),
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun VehicleCard(vehicle: VehiclesModel?) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = vehicle?.name ?: "",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.model_format, vehicle?.model ?: ""),
                fontSize = 12.sp
            )
            Text(
                text = stringResource(R.string.vehicle_class_format, vehicle?.vehicleClass ?: ""),
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun StarshipCard(starship: StarshipsModel?) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = starship?.name ?: "",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.model_format, starship?.model ?: ""),
                fontSize = 12.sp
            )
            Text(
                text = stringResource(R.string.starship_class_format, starship?.starshipClass ?: ""),
                fontSize = 12.sp
            )
        }
    }
}