package com.example.readercomposeui.ui.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.readercomposeui.data.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 * @author : Mingaleev D
 * @data : 15.11.2023
 */

class LoginViewModel : ViewModel() {

   //val loadingState = MutableStateFlow(LoadingState.IDLE)
   private val auth: FirebaseAuth = Firebase.auth

   private val _loading = MutableLiveData(false)
   val loading: LiveData<Boolean> = _loading

   fun createUserWithEmailAndPassword(email: String, password: String, home: () -> Unit) {
      viewModelScope.launch {
         if (_loading.value == false) {
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                   if (task.isSuccessful) {
                      val displayName = task.result.user?.email?.split('@')?.get(0)
                      createUser(displayName)
                      home()
                   } else {
                      Log.d("VMTAGFB", "createUserWithEmailAndPassword: ${task.result.toString()}")
                   }
                   _loading.value = false
                }
         }

      }

   }

   private fun createUser(displayName: String?) {
      val userId = auth.currentUser?.uid
      val user = UserModel(
          userId = userId.toString(),
          displayName = displayName.toString(),
          avatarUrl = "",
          quote = "",
          profession = "",
          id = null
      ).toMap()

      FirebaseFirestore.getInstance().collection("users").add(user)
   }

   fun signInWithEmailAndPassword(email: String, password: String, home: () -> Unit) {
      viewModelScope.launch {
         try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                   if (task.isSuccessful) {
                      home()
                      Log.d(
                          "VMTAGFB",
                          "signInWithEmailAndPassword: isSuccessful ${task.result.toString()}"
                      )

                   } else {
                      Log.d("VMTAGFB", "signInWithEmailAndPassword: ${task.result.toString()}")
                   }
                }
         } catch (ex: Exception) {
            Log.d("VMTAGFB", "signInWithEmailAndPassword: ${ex.message}")
         }
      }

   }
}

data class LoadingState(
    val status: Status,
    val message: String? = null
) {

   companion object {

      val IDLE = LoadingState(Status.IDLE)
      val LOADING = LoadingState(Status.LOADING)
      val SUCCESS = LoadingState(Status.SUCCESS)
      val FAILED = LoadingState(Status.FAILED)
   }

   enum class Status {
      LOADING,
      SUCCESS,
      FAILED,
      IDLE
   }
}