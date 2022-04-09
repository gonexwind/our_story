package com.gonexwind.ourstory.core.data.di

import com.gonexwind.ourstory.ui.auth.signin.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SignInViewModel(get()) }
}