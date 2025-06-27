package com.ralphmarondev.data.di

import com.ralphmarondev.data.local.database.AppDatabase
import com.ralphmarondev.data.local.preferences.AppPreferences
import com.ralphmarondev.data.repository.UserRepositoryImpl
import com.ralphmarondev.domain.repository.UserRepository
import com.ralphmarondev.domain.usecase.user.LoginUseCase
import com.ralphmarondev.domain.usecase.user.RegisterUseUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    singleOf(::AppPreferences)
    single { AppDatabase.createDatabase(androidContext()) }

    // user
    single { get<AppDatabase>().userDao }
    single<UserRepository> { UserRepositoryImpl(get()) }
    factoryOf(::LoginUseCase)
    factoryOf(::RegisterUseUseCase)

    // note
    single { get<AppDatabase>().noteDao }

    // bot
    single { get<AppDatabase>().botMessageDao }
}