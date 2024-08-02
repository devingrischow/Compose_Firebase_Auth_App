package com.example.compose_firebase_auth_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_firebase_auth_app.screens.HomeScreen
import com.example.compose_firebase_auth_app.screens.SignUpScreen
import com.example.compose_firebase_auth_app.ui.theme.Compose_Firebase_Auth_AppTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Compose_Firebase_Auth_AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    ContentHolder(innerPadding)

                }
            }
        }
    }
}

@Composable
fun ContentHolder(innerPaddingValues: PaddingValues) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        //Check whether the user is signed in
        if (isUserSignedIn()){
            //if the user is signed in, show home screen
            HomeScreen()
        }else{
            //if the user is NOT signed in, show Sign Up
            SignUpScreen()
        }


    }

}

@Preview(showBackground = true)
@Composable
fun ContentHolderPreview() {
    Compose_Firebase_Auth_AppTheme {
        ContentHolder(PaddingValues())
    }
}


//Check whether user is Signed In
fun isUserSignedIn():Boolean{

    val auth = Firebase.auth

    //check the current user if one exists
    val currUser = auth.currentUser

    //Check if the user exists or not
    if (currUser != null){
        return true
    }else{
        return false
    }

}