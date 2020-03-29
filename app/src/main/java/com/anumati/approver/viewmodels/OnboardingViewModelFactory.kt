package com.anumati.approver.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anumati.approver.repos.OnboardingRepo
import com.anumati.approver.repos.TokenRepo

class OnboardingViewModelFactory(private val application:Application,private val onboardingRepo: OnboardingRepo) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return OnboardingViewModel(application,onboardingRepo) as T
    }
}