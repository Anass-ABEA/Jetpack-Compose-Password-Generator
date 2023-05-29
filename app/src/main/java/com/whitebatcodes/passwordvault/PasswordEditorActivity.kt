package com.whitebatcodes.passwordvault

import android.app.Activity
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    var savedPassword by remember {
        mutableStateOf(SavedPassword())
    }

    val context = LocalContext.current

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
            TxtField(
                value = savedPassword.title,
                onChange = {
                    savedPassword = savedPassword.copy(title = it)
                },
                modifier = Modifier
                    .padding(horizontal = 5.dp),
                label = stringResource(id = R.string.name)
            )
            TxtField(
                value = savedPassword.login,
                onChange = {
                    savedPassword = savedPassword.copy(login = it)
                },
                modifier = Modifier
                    .padding(horizontal = 5.dp),
                label = stringResource(id = R.string.login)
            )
            PwdField(
                value = savedPassword.password,
                onChange = {
                    savedPassword = savedPassword.copy(password = it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp)
            )
            TxtField(
                value = savedPassword.siteUrl,
                onChange = {
                    savedPassword = savedPassword.copy(siteUrl = it)
                },
                modifier = Modifier
                    .padding(horizontal = 5.dp),
                label = stringResource(id = R.string.site_link)
            )
            TxtField(
                value = savedPassword.description,
                onChange = {
                    savedPassword = savedPassword.copy(description = it)
                },
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .heightIn(min = 80.dp),
                label = stringResource(id = R.string.description)
            )
            Spacer(Modifier.height(10.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    // send the data
                    (context as Activity).finish()
                },
                enabled = savedPassword.mandatoryFieldsExist()
            ) {
                Text(stringResource(id = R.string.save))
            }

        }
    }
}


@Preview(device = "id:Nexus One", showSystemUi = true)
@Composable
fun PasswordEditorPreview() {
    PasswordVaultTheme {
        PasswordEditor()
    }
}