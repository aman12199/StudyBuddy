package com.example.studybuddy.Login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel: ViewModel() {

    private val auth : FirebaseAuth = FirebaseAuth.getInstance()

    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    init {
        checkAuthStatus()
    }

    fun checkAuthStatus(){
        if(auth.currentUser==null){
            _authState.value=AuthState.Unauthenticated
        }else{
            _authState.value=AuthState.Authenticated
        }
    }
    fun login(email:String,password: String){

        if(email.isEmpty()||password.isEmpty()){
            _authState.value=AuthState.Error("Email and password can't be empty")
            return
        }

        _authState.value=AuthState.Loading
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener{task ->
                if(task.isSuccessful){
                    _authState.value=AuthState.Authenticated
                }else{
                    _authState.value=AuthState.Error(task.exception?.message?:"Something went wrong")
                }

            }
    }
    fun SinUp(email:String,password: String){

        if(email.isEmpty()||password.isEmpty()){
            _authState.value=AuthState.Error("Email and password can't be empty")
            return
        }

        _authState.value=AuthState.Loading
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{task ->
                if(task.isSuccessful){
                    _authState.value=AuthState.Authenticated
                }else{
                    _authState.value=AuthState.Error(task.exception?.message?:"Something went wrong")
                }

            }
    }
    fun SignOut(){
        auth.signOut()
        _authState.value=AuthState.Unauthenticated
    }

    fun resetPassword(email: String) {
        if (email.isEmpty()) {
            _authState.value = AuthState.Error("Email can't be empty")
            return
        }

        _authState.value = AuthState.Loading
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Success("Password reset email sent") // Email sent successfully
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Error occurred")
                }
            }
    }


}

sealed class AuthState{
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Success(val message: String) : AuthState()
    data class Error(val message : String):AuthState()
}