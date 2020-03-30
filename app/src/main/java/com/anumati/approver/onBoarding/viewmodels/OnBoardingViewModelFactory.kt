package com.anumati.approver.onBoarding.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anumati.approver.onBoarding.repos.OnBoardingRepo

class OnBoardingViewModelFactory(private val application:Application, private val onBoardingRepo: OnBoardingRepo) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return OnBoardingViewModel(
            application,
            onBoardingRepo
        ) as T
    }
}