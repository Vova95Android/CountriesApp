package com.example.countriesapp.ui.countryList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.countriesapp.R
import com.example.countriesapp.ui.MainViewModel
import com.example.domain.models.CountriesItem

@Composable
fun CountryListScreen(factory: ViewModelProvider.Factory) {

    val viewModel: MainViewModel = viewModel(factory = factory)
    val list = viewModel.countryList.collectAsState().value
    Column {
        OutlinedTextField(
            value = viewModel.query.collectAsState().value ?: "",
            onValueChange = {
                viewModel.setQuery(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            label = { Text(text = "Search") }
        )
        ListCountries(list)
    }
}

@Composable
fun ListCountries(list: List<CountriesItem>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        items(list.size) {
            ItemCountry(data = list[it])
        }
    }
}

@Composable
fun ItemCountry(data: CountriesItem) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val (image, name, background) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.background_outline),
            contentDescription = null,
            modifier = Modifier.constrainAs(background) {
                top.linkTo(parent.top, 0.dp)
                start.linkTo(parent.start, 0.dp)
                end.linkTo(parent.end, 0.dp)
                bottom.linkTo(parent.bottom, 5.dp)
            }
        )
        Text(
            text = data.emoji,
            modifier = Modifier.constrainAs(image) {
                top.linkTo(background.top, 2.dp)
                start.linkTo(background.start, 2.dp)
                bottom.linkTo(background.bottom, 2.dp)
            }
        )
        Text(
            text = data.name,
            modifier = Modifier.constrainAs(name) {
                top.linkTo(background.top, 0.dp)
                start.linkTo(image.end, 14.dp)
                bottom.linkTo(background.bottom, 0.dp)
            }
        )
    }
}

@Preview
@Composable
fun DrawList() {
    ListCountries(
        list = listOf(
            CountriesItem("1", "sdfsef", "\uD83C\uDDE6\uD83C\uDDE9"),
            CountriesItem("2", "sefsefe", "\uD83C\uDDE6\uD83C\uDDE9")
        )
    )
}