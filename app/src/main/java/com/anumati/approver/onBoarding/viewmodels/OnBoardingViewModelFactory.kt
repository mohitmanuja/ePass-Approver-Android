package com.anumati.approver.onBoarding.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anumati.approver.onBoarding.repos.OnboardingRepo

class OnBoardingViewModelFactory(private val application:Application, private val onboardingRepo: OnboardingRepo) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return OnBoardingViewModel(
            application,
            onboardingRepo
        ) as T
    }
}