package com.epass.curfue.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.epass.curfue.R
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
                        response.body()?.apply {
                            tokenResponseLiveData.postValue(this)
                        }
                    } else {
                        showToast.postValue("Token is not Valid")
                    }
                } catch (e: HttpException) {
                    showToast.postValue(context.getString(R.string.unable_to_connect_server_try_again))
                } catch (e: Throwable) {
                    showToast.postValue(context.getString(R.string.unable_to_connect_server_try_again))
                } catch (e: SocketTimeoutException) {
                    showToast.postValue(context.getString(R.string.unable_to_connect_server_try_again))
                } catch (e: IOException) {
                    showToast.postValue(context.getString(R.string.unable_to_connect_server_try_again))
                }finally {
                    loadingScreen.postValue(false)
                }
            }
        }

    }
}