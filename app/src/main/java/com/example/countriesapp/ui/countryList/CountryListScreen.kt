package com.example.countriesapp.ui.countryList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.countriesapp.R
import com.example.domain.models.CountriesItem

@Composable
fun CountryListScreen(
    list: List<CountriesItem>,
    query: String?,
    setQuery: (String) -> Unit,
    countrySelect: (CountriesItem) -> Unit,
    modifier: Modifier
) {
    Card(
        modifier = modifier
            .background(colorResource(id = R.color.background))
            .padding(10.dp)
            .shadow(7.dp, RoundedCornerShape(10.dp), true)
            .border(1.dp, colorResource(id = R.color.middle_grey))
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .background(colorResource(id = R.color.white))
        ) {
            Text(
                text = stringResource(id = R.string.text_countries),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .border(1.dp, colorResource(id = R.color.middle_grey))
                    .background(colorResource(id = R.color.background_title))
                    .padding(vertical = 10.dp)
            )
            TextField(
                value = query ?: "",
                onValueChange = { setQuery.invoke(it) },
                colors = TextFieldDefaults.textFieldColors(
                    disabledTextColor = Color.Transparent,
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .border(
                        1.dp,
                        colorResource(id = R.color.middle_grey),
                        RoundedCornerShape(100.dp)
                    ),
                label = { Text(text = stringResource(id = R.string.text_search)) },
                trailingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = null
                    )
                }
            )
            ListCountries(list, countrySelect)
        }
    }
}

@Composable
fun ListCountries(
    list: List<CountriesItem>,
    countrySelect: (CountriesItem) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        items(list.size) {
            ItemCountry(data = list[it], countrySelect = countrySelect)
        }
    }
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun ItemCountry(data: CountriesItem, countrySelect: (CountriesItem) -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .padding(start = 20.dp, top = 5.dp, end = 20.dp)
            .wrapContentHeight()
            .fillMaxWidth()
            .border(1.dp, colorResource(id = R.color.middle_grey), RoundedCornerShape(5.dp))
            .background(colorResource(id = if (data.isSelected) R.color.selected else R.color.white))
            .clickable { countrySelect.invoke(data) }
    ) {
        val (image, name) = createRefs()
        //TODO API does not have a field to get the flag image, only emoji
        Text(
            text = data.emoji,
            fontSize = TextUnit(32f, TextUnitType.Sp),
            modifier = Modifier.constrainAs(image) {
                top.linkTo(parent.top, 2.dp)
                start.linkTo(parent.start, 2.dp)
                bottom.linkTo(parent.bottom, 2.dp)
            }
        )
        Text(
            text = data.name,
            color = colorResource(id = R.color.title),
            modifier = Modifier.constrainAs(name) {
                top.linkTo(parent.top, 0.dp)
                start.linkTo(image.end, 14.dp)
                bottom.linkTo(parent.bottom, 0.dp)
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
        ), {}
    )
}