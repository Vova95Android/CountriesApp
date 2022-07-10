package com.example.countriesapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.countriesapp.R
import com.example.countriesapp.di.appComponent
import com.example.countriesapp.ui.countryDetail.CountryDetailScreen
import com.example.countriesapp.ui.countryList.CountryListScreen
import com.example.countriesapp.ui.theme.CountriesAppTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applicationContext.appComponent.inject(this)
        setContent {
            CountriesAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val viewModel: MainViewModel = viewModel(factory = factory)
                    val list = viewModel.countryList.collectAsState()
                    val query = viewModel.query.collectAsState()
                    val detail = viewModel.countryDetail.collectAsState()
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(colorResource(id = R.color.background))
                    ) {
                        val (listRef, detailRef) = createRefs()
                        val listModifier = Modifier.constrainAs(listRef) {
                            top.linkTo(parent.top, 20.dp)
                            start.linkTo(parent.start, 20.dp)
                            end.linkTo(detailRef.start, 10.dp)
                            bottom.linkTo(parent.bottom, 20.dp)
                            width = Dimension.fillToConstraints
                        }
                        val detailModifier = Modifier.constrainAs(detailRef) {
                            top.linkTo(parent.top, 20.dp)
                            start.linkTo(listRef.end, 10.dp)
                            end.linkTo(parent.end, 20.dp)
                            bottom.linkTo(parent.bottom, 20.dp)
                            width = Dimension.fillToConstraints
                        }
                        CountryListScreen(
                            list = list.value,
                            query = query.value,
                            modifier = listModifier,
                            setQuery = {
                                viewModel.setQuery(it)
                            },
                            countrySelect = {
                                viewModel.selectCountry(it.code)
                            }
                        )
                        CountryDetailScreen(data = detail.value, modifier = detailModifier)
                    }
                }
            }
        }
    }
}
