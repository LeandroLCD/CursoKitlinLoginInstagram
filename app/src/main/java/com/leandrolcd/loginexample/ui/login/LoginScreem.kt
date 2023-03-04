package com.leandrolcd.loginexample.ui.login

import android.app.Activity
import android.text.BoringLayout
import android.util.Log
import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leandrolcd.loginexample.R
import java.util.regex.Pattern

@Preview(showBackground = true)
@Composable
fun LoginScreem() {
    Box(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Header(
            Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
        )
        Body(Modifier.align(Center))

        Footer(Modifier.align(Alignment.BottomCenter))
    }


}

//region Header
@Composable
fun Header(modifier: Modifier) {
    var activity = LocalContext.current as Activity
    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = "Close App",
        modifier = modifier.clickable { activity.finish() })
}

//endregion

//region Body
@Composable
fun Body(modifier: Modifier) {

    //region States
    var email by rememberSaveable {
        mutableStateOf("")
    }

    var pwd by rememberSaveable {
        mutableStateOf("")
    }
    var isLoginEnabled by rememberSaveable {
        mutableStateOf(false)
    }

    //endregion

    Column(modifier = modifier) {
        ImageLogo(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.height(16.dp))
        Email(email) { email = it
            isLoginEnabled = enabledLogin(email, pwd)

        }
        Spacer(modifier = modifier.height(4.dp))
        Password(pwd) {
            pwd = it
            isLoginEnabled = enabledLogin(email, pwd)

        }
        Spacer(modifier = Modifier.height(8.dp))
        ForgotPassword(Modifier.align(End))
        LoginButton(isLoginEnabled)
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
fun LoginButton(enable: Boolean) {
    Button(
        onClick = { }, enabled = enable, modifier = Modifier
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

fun enabledLogin(email:String, password:String) =
    Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 5




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
            var image = if (passwordVisibility) {
                Icons.Filled.VisibilityOff
            } else {
                Icons.Filled.Visibility
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