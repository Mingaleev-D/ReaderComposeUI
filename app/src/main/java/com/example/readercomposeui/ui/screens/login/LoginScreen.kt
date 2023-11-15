package com.example.readercomposeui.ui.screens.login

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.readercomposeui.ui.components.EmailInput
import com.example.readercomposeui.ui.components.PasswordInput
import com.example.readercomposeui.ui.components.ReaderLogoText
import com.example.readercomposeui.ui.navigation.ReaderScreens
import com.example.readercomposeui.ui.theme.md_theme_dark_error

/**
 * @author : Mingaleev D
 * @data : 14.11.2023
 */

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

   val showLoginForm = rememberSaveable() { mutableStateOf(true) }

   Surface(
       modifier = Modifier.fillMaxSize()
   ) {

      Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center
      ) {

         ReaderLogoText(modifier = Modifier.padding(16.dp))

         if (showLoginForm.value) {
            UserForm(
                loading = false,
                isCreateAccount = false,
                onDone = { email, password ->
                   viewModel.signInWithEmailAndPassword(
                       email = email,
                       password = password,
                       home = {
                          navController.navigate(route = ReaderScreens.ReaderHomeScreen.name)
                       }
                   )
                }
            )
         } else {
            UserForm(
                loading = false,
                isCreateAccount = true,
                onDone = { email, password ->
                   viewModel.createUserWithEmailAndPassword(
                       email = email,
                       password = password,
                       home = {
                          navController.navigate(route = ReaderScreens.ReaderHomeScreen.name)
                       }
                   )

                }
            )
         }

         Spacer(modifier = Modifier.size(10.dp))

         Row(
             modifier = Modifier.padding(15.dp),
             horizontalArrangement = Arrangement.Center,
             verticalAlignment = Alignment.CenterVertically
         ) {

            val text = if (showLoginForm.value) "Регистрация" else "Вход"
            Text(text = "Новый пользователь?")
            Text(
                text = text,
                modifier = Modifier
                    .clickable {
                       showLoginForm.value = !showLoginForm.value
                    }
                    .padding(start = 5.dp),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.inversePrimary
            )
         }

      }

   }
}

@Composable
fun UserForm(
    loading: Boolean = false,
    isCreateAccount: Boolean = false,
    onDone: (String, String) -> Unit = { _, _ -> }
) {
   val email = rememberSaveable { mutableStateOf("") }
   val password = rememberSaveable { mutableStateOf("") }
   val passwordVisibility = rememberSaveable { mutableStateOf(false) }
   val passwordFocusRequest = FocusRequester.Default
   val keyboardController = LocalSoftwareKeyboardController.current
   val valid = remember(email.value, password.value) {
      email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
   }
   val modifier: Modifier = Modifier
       .height(250.dp)
       .background(MaterialTheme.colorScheme.background)
       .verticalScroll(
           rememberScrollState()
       )

   Column(
       modifier = modifier,
       horizontalAlignment = Alignment.CenterHorizontally,
   ) {
      if (isCreateAccount) Text(
          text = "Необходим email и пароль состоящий как минимум из 6 символов",
          modifier = Modifier.padding(horizontal = 10.dp),
          fontWeight = FontWeight.Bold,
          color = md_theme_dark_error
      )
      else Text(text = "")

      EmailInput(
          emailsState = email,
          enabled = !loading,
          onAction = KeyboardActions {
             passwordFocusRequest.requestFocus()
          }
      )

      PasswordInput(
          modifier = Modifier.focusRequester(passwordFocusRequest),
          passwordState = password,
          labelId = "Пароль",
          enabled = !loading,
          passwordVisibility = passwordVisibility,
          onAction = KeyboardActions {
             if (!valid) return@KeyboardActions
             onDone(email.value.trim(), password.value.trim())
          }
      )

      SubmitButton(
          textId = if (isCreateAccount) "Создать аккаунт" else "Войти",
          loading = loading,
          validInputs = valid,
          onClick = {
             onDone(email.value.trim(), password.value.trim())
             keyboardController?.hide()
          }
      )

   }

}

@Composable fun SubmitButton(
    textId: String,
    loading: Boolean,
    validInputs: Boolean,
    onClick: () -> Unit = {}
) {
   Button(
       onClick = { onClick() }, modifier = Modifier
       .padding(horizontal = 10.dp, vertical = 10.dp)
       .fillMaxWidth(),
       enabled = !loading && validInputs,
       shape = CircleShape
   ) {
      if (loading) CircularProgressIndicator(modifier = Modifier.size(25.dp))
      else Text(
          text = textId,
          modifier = Modifier.padding(5.dp)
      )
   }

}

@Preview(
    name = "Ночь-Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "День-Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Composable
private fun UserFomtPreview() {
   UserForm()

}