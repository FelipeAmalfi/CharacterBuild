package com.example.characterbuild.presentation.ui.talents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.characterbuild.presentation.ui.talents.composable.TalentsContentView
import com.example.characterbuild.presentation.ui.talents.viewmodel.TalentsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TalentsActivity : ComponentActivity() {

    private val talentsViewModel: TalentsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { TalentsContentView(talentsViewModel) }
    }

}


