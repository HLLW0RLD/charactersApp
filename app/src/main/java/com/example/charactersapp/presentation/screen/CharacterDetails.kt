package com.example.charactersapp.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.charactersapp.domain.model.CharacterModel
import com.example.charactersapp.domain.model.FilmModel
import com.example.charactersapp.domain.model.SpeciesModel
import com.example.charactersapp.domain.model.StarshipsModel
import com.example.charactersapp.domain.model.VehiclesModel
import com.example.charactersapp.presentation.screen.viewModel.CharacterDetailsState
import com.example.charactersapp.presentation.screen.viewModel.CharacterDetailsViewModel
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
data class CharacterDetails(val id: String)

@Composable
fun CharacterDetailsScreen(
    characterDetails: CharacterDetails
) {

    Scaffold(
        topBar = {

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
                            text = "Ошибка загрузки",
                            fontSize = 18.sp,
                            color = Color.Red
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { /* retry logic */ }
                        ) {
                            Text("Повторить")
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
        // Основная информация
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = character?.name ?: "",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    DetailRow(label = "Рост", value = "${character?.height} см")
                    DetailRow(label = "Масса", value = "${character?.mass} кг")
                    DetailRow(label = "Цвет волос", value = character?.hairColor ?: "")
                    DetailRow(label = "Цвет кожи", value = character?.skinColor ?: "")
                    DetailRow(label = "Цвет глаз", value = character?.eyeColor ?: "")
                    DetailRow(label = "Год рождения", value = character?.birthYear ?: "")
                    DetailRow(label = "Пол", value = character?.gender ?: "")
                }
            }
        }

        // Планета
        character?.homeworld?.let { planet ->
            item {
                DetailsSection(title = "Родная планета") {
                    DetailRow(label = "Название", value = planet.name)
                    DetailRow(label = "Климат", value = planet.climate)
                    DetailRow(label = "Террейн", value = planet.terrain)
                    DetailRow(label = "Население", value = planet.population)
                }
            }
        }

        // Фильмы
//        if (character?.films?.isNotEmpty()) {
//            item {
//                DetailsSection(title = "Фильмы") {
//                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
//                        character?.films?.forEach { film ->
//                            FilmCard(film = film)
//                        }
//                    }
//                }
//            }
//        }
//
//        // Виды
//        if (character?.species?.isNotEmpty()) {
//            item {
//                DetailsSection(title = "Виды") {
//                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
//                        character?.species?.forEach { species ->
//                            SpeciesCard(species = species)
//                        }
//                    }
//                }
//            }
//        }
//
//        // Транспорт
//        if (character?.vehicles?.isNotEmpty()) {
//            item {
//                DetailsSection(title = "Транспорт") {
//                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
//                        character?.vehicles?.forEach { vehicle ->
//                            VehicleCard(vehicle = vehicle)
//                        }
//                    }
//                }
//            }
//        }
//
//        // Звездолеты
//        if (character?.starships?.isNotEmpty()) {
//            item {
//                DetailsSection(title = "Звездолеты") {
//                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
//                        character?.starships?.forEach { starship ->
//                            StarshipCard(starship = starship)
//                        }
//                    }
//                }
//            }
//        }
    }
}

@Composable
fun DetailRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value.ifEmpty { "неизвестно" },
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun DetailsSection(
    title: String,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(12.dp))

            content()
        }
    }
}

@Composable
fun FilmCard(film: FilmModel?) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = film?.title ?: "",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Эпизод ${film?.episodeId}",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = "Режиссер: ${film?.director}",
                fontSize = 12.sp
            )
            Text(
                text = "Дата выхода: ${film?.releaseDate}",
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
                text = "Классификация: ${species?.classification}",
                fontSize = 12.sp
            )
            Text(
                text = "Язык: ${species?.language}",
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
                text = "Модель: ${vehicle?.model}",
                fontSize = 12.sp
            )
            Text(
                text = "Класс: ${vehicle?.vehicleClass}",
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
                text = "Модель: ${starship?.model}",
                fontSize = 12.sp
            )
            Text(
                text = "Класс: ${starship?.starshipClass}",
                fontSize = 12.sp
            )
        }
    }
}