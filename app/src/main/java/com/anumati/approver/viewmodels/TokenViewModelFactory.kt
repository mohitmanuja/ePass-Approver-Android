package com.anumati.approver.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.anumati.approver.repos.TokenRepo

class TokenViewModelFactory(private val operatorRepo: TokenRepo) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VerifyTokenViewModel(operatorRepo) as T
    }
}