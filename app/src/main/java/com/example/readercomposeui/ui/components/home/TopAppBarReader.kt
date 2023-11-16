package com.example.readercomposeui.ui.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FollowTheSigns
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.readercomposeui.R
import com.example.readercomposeui.ui.navigation.ReaderScreens
import com.google.firebase.auth.FirebaseAuth

/**
 * @author : Mingaleev D
 * @data : 15.11.2023
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarReader(
    title: String,
    showProfile: Boolean = true,
    navController: NavController
) {

   TopAppBar(
       title = {
          Row(
              verticalAlignment = Alignment.CenterVertically
          ) {
             if (showProfile) {
                Image(
                    painter = painterResource(id = R.drawable.reader),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(24.dp)
                )
             }

             Spacer(modifier = Modifier.width(6.dp))

             Text(
                 text = title,
                 fontWeight = FontWeight.Bold,
                 color = Color.Black,
                 fontSize = 20.sp
             )

             Spacer(modifier = Modifier.width(150.dp))
          }

       },
       actions = {
          IconButton(onClick = {
             FirebaseAuth.getInstance().signOut().run {
                navController.navigate(route = ReaderScreens.LoginScreen.name)
             }
          }) {
             Image(
                 imageVector = Icons.Default.FollowTheSigns,
                 contentDescription = null,
                 alpha = 0.6f
             )
          }
       },
       colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
       modifier = Modifier.shadow(
           elevation = 4.dp,
           spotColor = Color.DarkGray,
           shape = RoundedCornerShape(3.dp)
       )
   )

}