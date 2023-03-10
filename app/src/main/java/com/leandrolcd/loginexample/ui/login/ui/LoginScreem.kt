package com.leandrolcd.loginexample.ui.login.ui

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leandrolcd.loginexample.R


@Composable
fun LoginScreem(loginViewModel: LoginViewModel) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        val isLoading:Boolean by loginViewModel.isLoading.observeAsState(initial = false)
        if(isLoading){
            Box(modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center), contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }
        }else{
            Header(
                Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
            )
            Body(Modifier.align(Center), loginViewModel)

            Footer(Modifier.align(Alignment.BottomCenter))

        }
    }


}

//region Header
@Composable
fun Header(modifier: Modifier) {
    val activity = LocalContext.current as Activity
    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = "Close App",
        modifier = modifier.clickable { activity.finish() })
}

//endregion

//region Body
@Composable
fun Body(modifier: Modifier, loginViewModel: LoginViewModel) {

    //region States
    val email:String by loginViewModel.email.observeAsState("")

    val pwd:String by loginViewModel.password.observeAsState("")

    val isLoginEnabled by loginViewModel.isLoginEnabled.observeAsState(true)

    //endregion

    Column(modifier = modifier) {
        ImageLogo(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(16.dp))
        Email(email) {
            loginViewModel.onLoginChange(it, pwd)

        }
        Spacer(modifier = modifier.height(4.dp))
        Password(pwd) {
            loginViewModel.onLoginChange(email, it)

        }
        Spacer(modifier = Modifier.height(8.dp))
        ForgotPassword(Modifier.align(End))
        LoginButton(isLoginEnabled, loginViewModel)
        Spacer(modifier = Modifier.height(16.dp))
        LoginDivider()
        LoginWith()
    }
}

//region Controls Body


@Composable
fun LoginWith() {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_fb),
            contentDescription = "Logo Instagram", modifier = Modifier.size(20.dp)
        )

        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = "Login to Facebook",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4380A1)
        )
    }


}

@Composable
fun LoginDivider() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Divider(
            Modifier
                .weight(1f)
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
        )
        Text(
            "Or",
            modifier = Modifier.padding(20.dp),
            color = Color(0xFFB5B5B5),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
        Divider(
            Modifier
                .weight(1f)
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
        )
    }
}

@Composable
fun LoginButton(enable: Boolean, loginViewModel: LoginViewModel) {
    Button(
        onClick = { loginViewModel.onLoginCliked()}, enabled = enable, modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp), colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF4Ea8E9),
            disabledBackgroundColor= Color(0xFF78C8FA),
            contentColor = Color.White,
             disabledContentColor = Color.White

            )

    ) {
        Text("Log In")
    }
}

@Composable
fun ForgotPassword(modifier: Modifier) {
    Text(
        text = "Forgot Password?",
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF4Ea8E9),
        modifier = modifier
    )
}






@Composable
fun ImageLogo(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.img_instagram),
        contentDescription = "Logo Instagram", modifier = modifier
    )
}

@Composable
fun Email(email: String, function: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = {function(it)},
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Email") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFB2B2B2),
            backgroundColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun Password(pwd: String, function: (String) -> Unit) {
    var passwordVisibility by remember {
        mutableStateOf(false)
    }
    TextField(
        value = pwd,
        onValueChange = { function(it) },
        Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Password") },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFFB2B2B2),
            backgroundColor = Color(0xFFFAFAFA),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            val image = if (passwordVisibility) {
                Icons.Rounded.Visibility
            } else {
                Icons.Rounded.Visibility
            }
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(imageVector = image, contentDescription = "Show Password")
            }
        },
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation('*')
        }
    )
}


//endregion

//endregion

//region Footer
@Composable
fun Footer(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp)
    ) {
        Divider(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
        )

        SignUp()
    }
}

@Composable
fun SignUp() {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text("Don't have an account?", fontSize = 12.sp, color = Color.Gray)
        Text(
            "Sign Up",
            Modifier.padding(start = 8.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4Ea8E9)
        )

    }
}
//endregion