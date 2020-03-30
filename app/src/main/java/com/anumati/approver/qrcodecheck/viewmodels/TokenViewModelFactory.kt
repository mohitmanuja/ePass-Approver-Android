package com.anumati.approver.qrcodecheck.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anumati.approver.qrcodecheck.repos.TokenRepo

class TokenViewModelFactory(private val operatorRepo: TokenRepo) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VerifyTokenViewModel(operatorRepo) as T
    }
}