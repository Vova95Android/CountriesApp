package com.example.countriesapp.ui.countryDetail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.countriesapp.R
import com.example.domain.models.ContentData
import com.example.domain.models.CountryDetail

@OptIn(ExperimentalFoundationApi::class, ExperimentalUnitApi::class)
@Composable
fun CountryDetailScreen(data: CountryDetail?, modifier: Modifier) {
    data ?: return
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Text(
            text = stringResource(id = R.string.text_info),
            color = colorResource(id = R.color.title),
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 20.dp)
        )
        //TODO API does not have a field to get the flag image, only emoji
        Text(
            text = data.emoji,
            fontSize = TextUnit(64f, TextUnitType.Sp),
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 20.dp)
        )
        Text(
            text = data.name.uppercase(),
            color = colorResource(id = R.color.title),
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 20.dp),

        )

        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            modifier = Modifier.height(300.dp)
        ) {
            items(data.listData.size) {
                CountryDetailDataItem(
                    title = stringResource(
                        id = when (data.listData[it].first) {
                            ContentData.Continent -> R.string.text_continent
                            ContentData.Capital -> R.string.text_capital
                            ContentData.Code -> R.string.text_code
                            ContentData.Phone -> R.string.text_phone
                            ContentData.Currency -> R.string.text_currency
                            ContentData.Language -> R.string.text_language
                        }
                    ),
                    body = data.listData[it].second
                )
            }
        }
    }
}

@Composable
fun CountryDetailDataItem(title: String, body: String) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .border(1.dp, colorResource(id = R.color.middle_grey), RoundedCornerShape(5.dp))
    ) {
        ConstraintLayout(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .background(colorResource(id = R.color.white))
        ) {
            val (titleRef, bodyRef, circle) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.ic_circle),
                contentDescription = null,
                modifier = Modifier.constrainAs(circle) {
                    top.linkTo(parent.top, 20.dp)
                    start.linkTo(parent.start, 20.dp)
                }

            )
            Text(
                text = title,
                color = colorResource(id = R.color.title),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.constrainAs(titleRef) {
                    top.linkTo(circle.top, 0.dp)
                    start.linkTo(circle.end, 21.dp)
                    end.linkTo(parent.end, 10.dp)
                    bottom.linkTo(circle.bottom, 2.dp)
                    width = Dimension.fillToConstraints
                }
            )
            Text(
                text = body,
                color = colorResource(id = R.color.body),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.constrainAs(bodyRef) {
                    top.linkTo(titleRef.bottom, 16.dp)
                    start.linkTo(titleRef.start, 0.dp)
                    end.linkTo(parent.end, 10.dp)
                    bottom.linkTo(parent.bottom, 20.dp)
                    width = Dimension.fillToConstraints
                }
            )
        }
    }
}


@Preview
@Composable
fun DrawLCountryDetail() {
    CountryDetailDataItem("sddfgh", "sdfgh")
}