package com.example.readercomposeui.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.readercomposeui.data.model.BookModel
import com.example.readercomposeui.ui.components.home.BookRating
import com.example.readercomposeui.ui.components.home.FABContent
import com.example.readercomposeui.ui.components.home.RoundedButton
import com.example.readercomposeui.ui.components.home.TitleSelection
import com.example.readercomposeui.ui.components.home.TopAppBarReader
import com.example.readercomposeui.ui.navigation.ReaderScreens
import com.example.readercomposeui.ui.theme.seed
import com.google.firebase.auth.FirebaseAuth

/**
 * @author : Mingaleev D
 * @data : 14.11.2023
 */

val urlImagee = "https://books.google.com/books/content?id=6KceEAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api"

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {

   Scaffold(
       topBar = {
          TopAppBarReader(
              title = "Приложение",
              navController = navController
          )
       },
       floatingActionButton = {
          FABContent(
              onTab = {
                 //todo; click fab
              }
          )
       }
   ) {

      Surface(
          modifier = Modifier
              .fillMaxWidth()
              .padding(it)
      ) {
         HomeContent(
             navController = navController
         )
      }
   }
}

@Preview
@Composable
fun HomeContent(navController: NavController = NavController(LocalContext.current)) {

   val currentUseName = if (!FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty())
      FirebaseAuth.getInstance().currentUser?.email?.split('@')?.get(0)
   else "N/A"

   Column(
       modifier = Modifier.padding(2.dp),
       verticalArrangement = Arrangement.SpaceEvenly
   ) {
      Row(
          modifier = Modifier.align(alignment = Alignment.Start),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.Center
      ) {
         TitleSelection(label = "Вот что вы читаете... \uD83D\uDE07")

         Spacer(modifier = Modifier.fillMaxWidth(.6f))

         Column(
             horizontalAlignment = Alignment.CenterHorizontally
         ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                       navController.navigate(route = ReaderScreens.StatsScreen.name)
                    }
                    .size(32.dp)
            )
            Text(
                text = currentUseName!!,
                modifier = Modifier.padding(2.dp),
                style = MaterialTheme.typography.labelSmall.copy(
                    color = androidx.compose.ui.graphics.Color.Black,
                    fontSize = 15.sp,
                ),
                maxLines = 1,
                overflow = TextOverflow.Clip
            )
            Divider()
         }
      }

      ListCard()

      Spacer(modifier = Modifier.size(6.dp))
      Divider()

      TitleSelection(label = "Список читаемых.. \uD83D\uDE42")

      BookListArea(listOfBooks = emptyList<BookModel>(), navController = navController)
   }
}

@Composable fun BookListArea(listOfBooks: List<BookModel>, navController: NavController) {

   HorizontalScrollableComponent(listOfBooks = listOfBooks, onCardPressed = {})
}

@Composable fun HorizontalScrollableComponent(
    listOfBooks: List<BookModel>,
    onCardPressed: (String) -> Unit
) {
   val scrollState = rememberScrollState()

   Row(
       modifier = Modifier
           .fillMaxWidth()
           .heightIn(280.dp)
           .horizontalScroll(scrollState)
   ) {
      for (book in listOfBooks) {
         ListCard(book) {
            onCardPressed(it)
         }
      }
   }
}

@Composable
fun ReadingRightNowArea(
    books: List<BookModel>,
    navController: NavController
) {

}

@Preview
@Composable
fun ListCard(
    book: BookModel = BookModel(
        id = 1.toString(),
        title = "Title 1",
        author = "Author1",
        notes = "Demo notes 1"
    ),
    onPressDetails: (String) -> Unit = {}
) {

   val context = LocalContext.current
   val resource = context.resources
   val displayMetrics = resource.displayMetrics
   val screenWidth = displayMetrics.widthPixels / displayMetrics.density
   val spacing = 10.dp

   Card(
       shape = RoundedCornerShape(29.dp),
       modifier = Modifier
           .padding(10.dp)
           //.height(230.dp)
           .width(202.dp)
           .clickable { onPressDetails(book.title.toString()) },
       elevation = CardDefaults.cardElevation(
           defaultElevation = 10.dp
       )
   ) {
      Column(
          modifier = Modifier.width(screenWidth.dp - (spacing * 2)),
          horizontalAlignment = Alignment.Start
      ) {
         Row(
             horizontalArrangement = Arrangement.Center,
             verticalAlignment = Alignment.CenterVertically
         ) {

            Image(
                painter = rememberImagePainter(data = urlImagee),
                contentDescription = null,
                modifier = Modifier
                    .height(126.dp)
                    .width(130.dp)
                    .padding(top = 10.dp, start = 10.dp)
            )

            Spacer(modifier = Modifier.width(15.dp))

            Column(
                modifier = Modifier.padding(top = 5.dp, end = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
               Image(
                   imageVector = Icons.Default.FavoriteBorder,
                   contentDescription = null,
                   modifier = Modifier.padding(bottom = 1.dp)
               )

               BookRating(score = 3.5)
            }
         }

         Text(
             text = book.title.toString(),
             modifier = Modifier.padding(4.dp),
             fontWeight = FontWeight.Bold,
             maxLines = 1,
             overflow = TextOverflow.Ellipsis,
             fontSize = 20.sp
         )
         Text(
             text = book.author.toString(),
             modifier = Modifier.padding(4.dp),
             style = MaterialTheme.typography.bodySmall.copy(
                 color = androidx.compose.ui.graphics.Color.Black.copy(
                     alpha = .7f
                 ),
                 fontSize = 18.sp,
                 fontWeight = FontWeight.W500,
             ),
             maxLines = 1
         )

      }
      Row(
          verticalAlignment = Alignment.Bottom,
          horizontalArrangement = Arrangement.End,
          modifier = Modifier.fillMaxWidth()
      ) {
         RoundedButton()
      }
   }
}


