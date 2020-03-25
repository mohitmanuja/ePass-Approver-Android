package com.livemobilelocationtracker.livelocation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.epass.curfue.viewmodels.VerifyTokenViewModel
import com.epass.curfue.repos.TokenRepo

class TokenViewModelFactory(private val operatorRepo: TokenRepo) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VerifyTokenViewModel(operatorRepo) as T
    }
}