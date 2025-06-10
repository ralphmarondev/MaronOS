package com.ralphmarondev.auth.di

import com.ralphmarondev.auth.data.repository.AuthRepositoryImpl
import com.ralphmarondev.auth.domain.repository.AuthRepository
import com.ralphmarondev.auth.domain.usecase.LoginUseCase
import com.ralphmarondev.auth.presentation.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authModule = module {

    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()

    factoryOf(::LoginUseCase)

    viewModelOf(::LoginViewModel)
}