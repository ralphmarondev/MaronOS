package com.ralphmarondev.gallery.di

import com.ralphmarondev.gallery.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val galleryModule = module {
    viewModelOf(::HomeViewModel)
}