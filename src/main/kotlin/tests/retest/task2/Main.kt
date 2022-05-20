package tests.retest.task2

// Copyright 2000-2021 JetBrains s.r.o. and contributors.
// Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication, title = "RETEST2",
        state = rememberWindowState(width = 800.dp, height = 800.dp)
    ) {
        val viewModel = remember { ViewModel() }
        view(viewModel)
    }
}
