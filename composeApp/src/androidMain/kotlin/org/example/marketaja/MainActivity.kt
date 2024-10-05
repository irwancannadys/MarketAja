package org.example.marketaja

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.slack.circuit.foundation.internal.BackHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val backPressConsumer = Channel<Unit>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppContextProvider.setActivity(this)
        ModuleInstaller.install()

        setContent {
            BackHandler(true) {
                lifecycleScope.launch {
                    backPressConsumer.send(Unit)
                }
            }

            App(backPressConsumer)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        backPressConsumer.close()
    }
}