package org.example.marketaja

import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController(
    configure = {
        ModuleInstaller.install()
    }
) {

    App()
}
