package com.example.compose_firebase_auth_app.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


@Composable
fun SignUpScreen(){

    //Email Password Entry String States
    var emailEntry by remember { mutableStateOf<String>("") }

    var passwordEntry by remember { mutableStateOf<String>("") }



    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Text(text = "Sign Up with Email and Password",
            color = Color.Black,
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 25.dp, top = 50.dp)
                .width(350.dp)

        )



        //Text Area For Review
        TextField(
            value = emailEntry,
            onValueChange = { emailEntry = it  },
            label = {
                Text(text = "Email")
            },
            modifier = Modifier
                .padding(bottom = 30.dp)
        )



        //Text Area For Review
        TextField(
            value = passwordEntry,
            onValueChange = { passwordEntry = it  },
            label = {
                Text(text = "Password")
            },
            modifier = Modifier
                .padding(bottom = 30.dp)
        )

        Button(onClick = {
            Log.d("Pressed Button", "Pressed Sign Up Button")

            //Sign Up the User
            val signUpResult = signUpUserToFirebaseWithEmailPassword(emailEntry, passwordEntry)
        }) {
            Text("Sign Up")
            //Bottom Of Button
        }





    }



}


@Preview(showBackground = true)
@Composable
fun PreviewSignUp(){
    SignUpScreen()
}



//check if the email and password are blank
//if blank, dont let through
//(in the real world scenario, use more advanced password conditions and email regex checks)
fun checkEmailAndPasswordThrough(emailEntry:String, passwordEntry:String):Boolean {

    if (!emailEntry.isBlank() && !passwordEntry.isBlank()) {
        //Neither password or email is blank, sign up through firebase through email


        return true
    }else{
        //Email entry or password is blank, return FALSE

        return false
    }



}




//Sign Up User Through Email password
//Sign up the user to firebase through email password
fun signUpUserToFirebaseWithEmailPassword(email:String, password:String):Boolean {



    //Instantiate Firebase Auth module for the function
    val auth = Firebase.auth

    var signInUserResult = false

    //https://www.youtube.com/watch?v=AlSjt_2GU5A
    //Sign Up the User Throguh Firebase, and return whether the operation was a success
    auth.createUserWithEmailAndPassword(
        email,
        password,
    )
    //On complete, alert system return true
        .addOnCompleteListener { task ->
            //if the task is successfull, output whether it was and log it
            if (task.isSuccessful){
                Log.d("Sign Up Task", "Signed Up new user")
                signInUserResult = true
            }else{
                Log.e("Sign Up Task", "Failed To Sign Up user")
                signInUserResult = false
            }
        }




    return signInUserResult
}