package com.anumati.approver.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anumati.approver.R
import com.anumati.approver.models.TokenVerifyResponse
import com.anumati.approver.network.RetrofitFactory
import com.anumati.approver.repos.TokenRepo
import com.anumati.approver.utils.CommonUtils

class VerifyTokenViewModel(val tokenRepo: TokenRepo) : ViewModel() {
    private val tokenResponseLiveData: MutableLiveData<TokenVerifyResponse> = MutableLiveData()
    private val updateScreen = MutableLiveData<String>()
    private val showToast = MutableLiveData<String>()
    private val loadingScreen = MutableLiveData<Boolean>()

    fun getLoadingScreen(): LiveData<Boolean> {
        return loadingScreen
    }

    fun getshowToastLiveData(): LiveData<String> {
        return showToast
    }

    fun getUpdateScreenLiveData(): LiveData<String> {
        return updateScreen
    }

    fun getTokenResponseLiveData(): LiveData<TokenVerifyResponse> {
        return tokenResponseLiveData
    }


    fun fetchTokenResponse(token: String, context: Context) {
        if (!CommonUtils.isInternetAvailable(context)) {
            showToast.postValue("No internet Connection found.")
        } else {
            val service = RetrofitFactory.makeRetrofitService(context)
            loadingScreen.postValue(true)
            tokenRepo.fetchTokenResult(token, service, {response->
                if (response.isSuccessful) {
                    response.body()?.apply {
                        tokenResponseLiveData.postValue(this)
                    }
                } else {
                    showToast.postValue("Token is not Valid")
                }
                loadingScreen.postValue(false)
            },{
                loadingScreen.postValue(false)
                showToast.postValue(context.getString(R.string.unable_to_connect_server_try_again))
            })
        }

    }
}