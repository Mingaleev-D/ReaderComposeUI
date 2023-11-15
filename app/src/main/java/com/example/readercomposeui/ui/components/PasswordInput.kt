package com.example.readercomposeui.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @author : Mingaleev D
 * @data : 15.11.2023
 */

@Composable fun PasswordInput(
    modifier: Modifier = Modifier,
    passwordState: MutableState<String>,
    enabled: Boolean,
    passwordVisibility: MutableState<Boolean>,
    labelId: String,
    imeAction: ImeAction = ImeAction.Done,
    onAction: KeyboardActions = KeyboardActions.Default
) {

   val visualTransformation = if (passwordVisibility.value) VisualTransformation.None else
      PasswordVisualTransformation()

   OutlinedTextField(
       value = passwordState.value,
       onValueChange = { passwordState.value = it },
       label = {
          Text(text = labelId)
       },
       singleLine = true,
       textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colorScheme.onBackground),
       modifier = modifier
           .padding(horizontal = 10.dp, vertical = 5.dp)
           .fillMaxWidth(),
       enabled = enabled,
       keyboardOptions = KeyboardOptions(
           keyboardType = KeyboardType.Password,
           imeAction = imeAction
       ),
       visualTransformation = visualTransformation,
       trailingIcon = {
          PasswordVisibility(passwordVisibility = passwordVisibility)
       },
       keyboardActions = onAction
   )

}

@Composable fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {
   val visible = passwordVisibility.value
   IconButton(onClick = { passwordVisibility.value = !visible }) {
      Icons.Default.Close
   }

}