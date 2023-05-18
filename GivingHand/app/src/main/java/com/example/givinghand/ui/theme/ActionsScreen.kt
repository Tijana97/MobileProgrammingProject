package com.example.givinghand.ui.theme

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.givinghand.R
import com.example.givinghand.model.ActionItem

@Composable
fun ActionListItem(action: ActionItem.DonateActionItem, modifier: Modifier = Modifier){
    Card(){
        Row(){
            Column(){
                Text(
                    text = action.name
                )
                Text(text = action.address)
                Text(text = action.date)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Image(painter = painterResource(id = action.picture), contentDescription = null)
        }

    }

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ActionList(actions: MutableList<ActionItem.DonateActionItem>, modifier: Modifier=Modifier){
    val visibleState = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }
    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy)
        ),
        exit = fadeOut(),
        modifier = Modifier
    )
    {
        LazyColumn{
            itemsIndexed(actions){
                index, action ->
                ActionListItem(
                    action = action,
                    modifier = modifier
                        .padding(horizontal = 16.dp, vertical = 16.dp)
                        .animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = spring(
                                    stiffness = Spring.StiffnessVeryLow,
                                    dampingRatio = Spring.DampingRatioLowBouncy
                                ),
                                initialOffsetY = { it * (index+1)}

                            )
                        )
                )
            }
        }
    }

}