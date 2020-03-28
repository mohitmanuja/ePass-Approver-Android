package com.epass.curfue.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epass.curfue.models.TokenVerifyResponse
import com.epass.curfue.network.RetrofitFactory
import com.epass.curfue.repos.TokenRepo
import com.epass.curfue.utils.CommonUtils
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

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
            tokenRepo.fetchTokenResult(token, service) { response ->
                try {
                    if (response.isSuccessful) {
                        loadingScreen.postValue(false)
                        response.body()?.apply {
                            tokenResponseLiveData.postValue(this)
                        }
                    } else {
                        showToast.postValue("Token is not Valid")
                        loadingScreen.postValue(false)
                    }
                } catch (e: HttpException) {
                    showToast.postValue("Exception ${e.message}")
                    loadingScreen.postValue(false)
                } catch (e: Throwable) {
                    loadingScreen.postValue(false)
                    showToast.postValue("Oops: Something else went wrong")
                } catch (e: SocketTimeoutException) {
                    loadingScreen.postValue(false)
                    showToast.postValue("Oops: Something else went wrong")
                } catch (e: IOException) {
                    loadingScreen.postValue(false)
                    showToast.postValue("Oops: Something else went wrong")
                }
            }
        }

    }
}