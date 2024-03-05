package com.gkp.people.presentation.people

import android.widget.Toast
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.gkp.mya.R
import com.gkp.people.domain.model.People

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeopleScreen() {
    val viewMode: PeopleViewModel = viewModel()
    val peopleState = viewMode.peoplePagingData.collectAsLazyPagingItems()

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text(text = "People") }) }
    ) { paddingValues ->

        val context = LocalContext.current
        LaunchedEffect(peopleState.loadState) {
            if (peopleState.loadState.refresh is LoadState.Error) {
                Toast.makeText(
                    context,
                    (peopleState.loadState.refresh as LoadState.Error).error.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        Box(modifier = Modifier.fillMaxSize()) {
            if (peopleState.loadState.refresh is LoadState.Loading) {
                CircularProgressIndicator(
                    Modifier
                        .size(50.dp)
                        .align(Alignment.Center))
            } else {
                LazyColumn(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(peopleState.itemCount) { index ->
                        val people = peopleState[index]
                        people?.let {
                            PeopleItem(
                                modifier = Modifier.fillMaxWidth(),
                                people = it
                            )
                        }
                    }
                    if (peopleState.loadState.append is LoadState.Loading) {
                        item {
                            CircularProgressIndicator(Modifier.size(50.dp))
                        }
                    }
                }
            }
        }

    }

}

@Composable
fun PeopleItem(modifier: Modifier = Modifier, people: People) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = people.name,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(8.dp))
            PeopleInfoRow(
                modifier = Modifier.fillMaxWidth(),
                infoLabel = stringResource(R.string.people_gender),
                info = people.gender
            )
            Spacer(modifier = Modifier.height(8.dp))
            PeopleInfoRow(
                modifier = Modifier.fillMaxWidth(),
                infoLabel = stringResource(R.string.people_height),
                info = "${people.height} cm"
            )
        }
    }

}

@Composable
fun PeopleInfoRow(
    modifier: Modifier = Modifier,
    infoLabel: String,
    info: String,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = infoLabel)
        Text(text = info)
    }
}