package com.epass.curfue.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epass.curfue.models.VerifyTokenResponse
import com.epass.curfue.network.RetrofitFactory
import com.epass.curfue.repos.TokenRepo
import com.epass.curfue.utils.CommonUtils
import retrofit2.HttpException

class VerifyTokenViewModel(val tokenRepo: TokenRepo) : ViewModel() {
    private val tokenResponseLiveData: MutableLiveData<VerifyTokenResponse> = MutableLiveData()
    private val updateScreen = MutableLiveData<String>()
    private val loadingScreen = MutableLiveData<Boolean>()

    fun getLoadingScreen(): LiveData<Boolean> {
        return loadingScreen
    }

    fun getUpdateScreenLiveData(): LiveData<String> {
        return updateScreen
    }

    fun getTokenResponseLiveData(): LiveData<VerifyTokenResponse> {
        return tokenResponseLiveData
    }


    fun fetchTokenResponse(token: String, context: Context) {
        if (!CommonUtils.isInternetAvailable(context)) {
            updateScreen.postValue("No internet Connection found.")
        } else {
            val service = RetrofitFactory.makeRetrofitService(context)
            loadingScreen.postValue(true)
            tokenRepo.fetchTokenResult(token, service) { response ->
                try {
                    if (response.isSuccessful) {
                        loadingScreen.postValue(false)
                        response.body()?.apply {
                            tokenResponseLiveData.postValue(this)
                        }
                    } else {
                        updateScreen.postValue("Error: ${response.message()}")
                        loadingScreen.postValue(false)
                    }
                } catch (e: HttpException) {
                    updateScreen.postValue("Exception ${e.message}")
                    loadingScreen.postValue(false)
                } catch (e: Throwable) {
                    loadingScreen.postValue(false)
                    updateScreen.postValue("Oops: Something else went wrong")
                }


            }
        }

    }


}