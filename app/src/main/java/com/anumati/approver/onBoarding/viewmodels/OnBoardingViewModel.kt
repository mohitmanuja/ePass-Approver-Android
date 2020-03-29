package com.anumati.approver.onBoarding.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anumati.approver.R
import com.anumati.approver.onBoarding.models.OTPIdentifier
import com.anumati.approver.qrcodecheck.models.VerifyOTPResponse
import com.anumati.approver.network.RetrofitFactory
import com.anumati.approver.onBoarding.repos.OnboardingRepo
import com.anumati.approver.utils.CommonUtils
import com.anumati.approver.utils.SharedPrefHelper

class OnBoardingViewModel(application: Application, val onboardingRepo: OnboardingRepo) :
    AndroidViewModel(application) {
    private val createOTPRequestLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private val verifyOTPRequestLiveData: MutableLiveData<VerifyOTPResponse> = MutableLiveData()
    private val updateScreen = MutableLiveData<String>()
    private val showToast = MutableLiveData<String>()
    private val loadingScreen = MutableLiveData<Boolean>()
    lateinit var phoneNumber: String

    fun getLoadingScreen(): LiveData<Boolean> {
        return loadingScreen
    }

    fun getshowToastLiveData(): LiveData<String> {
        return showToast
    }

    fun getUpdateScreenLiveData(): LiveData<String> {
        return updateScreen
    }

    fun getCreateOTPRequestLiveData(): LiveData<Boolean> {
        return createOTPRequestLiveData
    }

    fun getVerifyOTPRequestLiveData(): LiveData<VerifyOTPResponse> {
        return verifyOTPRequestLiveData
    }


    fun createOTPRequest(number: String) {
        if (!CommonUtils.isInternetAvailable(getApplication())) {
            showToast.postValue(getApplication<Application>().getString(R.string.no_internet_connection_found))
        } else {
            val service = RetrofitFactory.makeRetrofitService(getApplication())
            loadingScreen.postValue(true)
            onboardingRepo.requestOTP(OTPIdentifier.PHONE, "+91$number", service, { response ->
                if (response.isSuccessful) {
                    createOTPRequestLiveData.postValue(true)
                } else {
                    showToast.postValue(getApplication<Application>().getString(R.string.something_went_wrong_try_again))
                }
                loadingScreen.postValue(false)
            }, {
                loadingScreen.postValue(false)
                showToast.postValue(getApplication<Application>().getString(R.string.unable_to_connect_server_try_again))
            })
        }

    }

    private fun verifyOTPRequest(
        number: String,
        otp: String,
        otpIdentifier: OTPIdentifier
    ) {
        if (!CommonUtils.isInternetAvailable(getApplication())) {
            showToast.postValue(getApplication<Application>().getString(R.string.no_internet_connection_found))
        } else {
            val service = RetrofitFactory.makeRetrofitService(getApplication())
            loadingScreen.postValue(true)
            onboardingRepo.verifyOTP("+91$number", otp, otpIdentifier, service, { response ->
                if (response.isSuccessful) {
                    response.body()?.apply {
                        SharedPrefHelper.saveUserLoggedIn(getApplication())
                        SharedPrefHelper.saveAuthToken(getApplication(), authToken)
                        SharedPrefHelper.savePublicKey(getApplication(), publicKey)
                        verifyOTPRequestLiveData.postValue(this)
                    }
                } else {
                    showToast.postValue(getApplication<Application>().getString(R.string.something_went_wrong_try_again))
                }
                loadingScreen.postValue(false)
            }, {
                loadingScreen.postValue(false)
                showToast.postValue(getApplication<Application>().getString(R.string.unable_to_connect_server_try_again))
            })
        }

    }

    fun otpEntered(otpString: String) {
        if (validateOTP(otpString)) {
            verifyOTPRequest(phoneNumber, otpString, OTPIdentifier.PHONE)
        }
    }

    private fun validateOTP(otpString: String): Boolean {
        // Write all your validation here for OTP
        if (otpString.length == 6) {
            return true
        } else {
            showToast.value =
                getApplication<Application>().getString(R.string.please_enter_valid_otp)
            return false
        }
    }

    fun setNumber(number: String) {
        phoneNumber = number
    }

    fun phoneNumberEntered(phoneNumber: String) {
        if (phoneNumber.length == 10) {
            createOTPRequest(phoneNumber)
        } else {
            showToast.value = getApplication<Application>().getString(R.string.please_enter_valid_mobile_number)
        }
    }

}