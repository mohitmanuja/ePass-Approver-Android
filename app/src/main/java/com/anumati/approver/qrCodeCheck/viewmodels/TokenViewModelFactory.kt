package com.anumati.approver.qrCodeCheck.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anumati.approver.qrCodeCheck.repos.TokenRepo

class TokenViewModelFactory(private val operatorRepo: TokenRepo) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VerifyTokenViewModel(operatorRepo) as T
    }
}