package com.ralphmarondev.auth.di

import com.ralphmarondev.auth.domain.usecase.LoginUseCase
import com.ralphmarondev.auth.presentation.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val authModule = module {

    factoryOf(::LoginUseCase)

    viewModelOf(::LoginViewModel)
}