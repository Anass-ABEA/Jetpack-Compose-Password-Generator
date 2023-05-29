package com.whitebatcodes.passwordvault

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.whitebatcodes.passwordvault.PasswordGeneratorActivity.Companion.AFTER_CREATE_EDIT_PASSWORD
import com.whitebatcodes.passwordvault.PasswordGeneratorActivity.Companion.BEFORE_CREATE_EDIT_PASSWORD
import com.whitebatcodes.passwordvault.models.password.SavedPassword
import com.whitebatcodes.passwordvault.ui.helpers.PwdField
import com.whitebatcodes.passwordvault.ui.helpers.TxtField
import com.whitebatcodes.passwordvault.ui.theme.PasswordVaultTheme

class PasswordEditorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PasswordEditor()
        }
    }
}

@Composable
fun PasswordEditor() {

    val context = LocalContext.current

    var receivedPassword = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU ->
            (context as Activity).intent.getParcelableExtra(
                BEFORE_CREATE_EDIT_PASSWORD,
                SavedPassword::class.java
            )

        else -> @Suppress("DEPRECATION") (context as Activity).intent.getSerializableExtra(
            BEFORE_CREATE_EDIT_PASSWORD
        ) as SavedPassword
    }

    if (receivedPassword == null) {
        receivedPassword = SavedPassword()
    }

    var savedPassword by remember {
        mutableStateOf(receivedPassword)
    }

    val focusManger = LocalFocusManager.current

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                stringResource(id = R.string.create_new_password),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(Modifier.height(10.dp))
            TxtField(
                value = savedPassword.title,
                onChange = {
                    savedPassword = savedPassword.copy(title = it)
                },
                modifier = Modifier
                    .padding(vertical = 5.dp),
                label = stringResource(id = R.string.name),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManger.moveFocus(FocusDirection.Down)
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )
            TxtField(
                value = savedPassword.login,
                onChange = {
                    savedPassword = savedPassword.copy(login = it)
                },
                modifier = Modifier
                    .padding(vertical = 5.dp),
                label = stringResource(id = R.string.login),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManger.moveFocus(FocusDirection.Down)
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )
            PwdField(
                value = savedPassword.password,
                onChange = {
                    savedPassword = savedPassword.copy(password = it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManger.moveFocus(FocusDirection.Down)
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )
            TxtField(
                value = savedPassword.siteUrl,
                onChange = {
                    savedPassword = savedPassword.copy(siteUrl = it)
                },
                modifier = Modifier
                    .padding(vertical = 5.dp),
                label = stringResource(id = R.string.site_link),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManger.moveFocus(FocusDirection.Down)
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )
            TxtField(
                value = savedPassword.description,
                onChange = {
                    savedPassword = savedPassword.copy(description = it)
                },
                modifier = Modifier
                    .padding(vertical = 5.dp)
                    .heightIn(min = 80.dp),
                label = stringResource(id = R.string.description),
                keyboardActions = KeyboardActions(
                    onDone  = {
                        if(savedPassword.mandatoryFieldsExist()){
                            sendResultAndFinish(savedPassword, context as Activity)
                        }
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            )
            Spacer(Modifier.height(10.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    sendResultAndFinish(savedPassword, context as Activity)
                },
                enabled = savedPassword.mandatoryFieldsExist()
            ) {
                Text(stringResource(id = R.string.save))
            }

        }
    }
}

fun sendResultAndFinish(savedPassword: SavedPassword, context: Activity) {
    context.setResult(RESULT_OK, Intent().putExtra(AFTER_CREATE_EDIT_PASSWORD,savedPassword))
    context.finish()
}


@Preview(device = "id:Nexus One", showSystemUi = true)
@Composable
fun PasswordEditorPreview() {
    PasswordVaultTheme {
        PasswordEditor()
    }
}