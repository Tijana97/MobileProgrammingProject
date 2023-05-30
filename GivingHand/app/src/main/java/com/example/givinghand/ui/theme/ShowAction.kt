package com.example.givinghand.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn

import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.givinghand.R
import com.example.givinghand.data.Action
import com.example.givinghand.data.Category
import com.example.givinghand.datasource.DataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf

@Composable
fun ShowAction(actionId: Int, modifier: Modifier = Modifier){
    val actionViewModel: ActionViewModel = viewModel(
        factory = ActionViewModel.factory,
        viewModelStoreOwner = LocalViewModelStoreOwner.current!!
    )
    val actions by actionViewModel.getActionById(actionId).collectAsState(initial = emptyList())
    val action = actions.firstOrNull()
    Column(

        modifier = Modifier.padding(8.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            modifier = Modifier.fillMaxWidth()


        ){
            Image(painter = painterResource(id = R.drawable.social), contentDescription = null)
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.fillMaxWidth()
                .padding(28.dp)){
                if (action != null) {
                    Text(text = action.name)
                    Text(text = action.description)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = action.address)
                    Text(text = action.date)
                }

            }

        }

        androidx.compose.material3.Button(
            onClick = {},
            Modifier.widthIn(min = 250.dp)

        ) {
            Text("Sign Up for the Action")
        }

    }

}
private val sampleAction = Action(1, "Sample Description", 30, "Adresa", "desc", 6, "5-14-2023", 6)
class ActionsFlowProvider : PreviewParameterProvider<Flow<List<Action>>> {
    override val values: Sequence<Flow<List<Action>>> = sequenceOf(
        flowOf(listOf(sampleAction)), // Provide a flow with a list containing the sample action
        flowOf(emptyList()) // Provide an empty flow
    )
}

