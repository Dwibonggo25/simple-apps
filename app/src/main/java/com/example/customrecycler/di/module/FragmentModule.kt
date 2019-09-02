package com.example.simplelogin.di.module

import com.example.simplelogin.ui.login.LoginFragment
import com.example.simplelogin.ui.splashscreen.SplashScreenFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributesLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributesSplashScreenFragment(): SplashScreenFragment
}