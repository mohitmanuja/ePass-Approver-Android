package com.anumati.approver.onBoarding.repos

import com.anumati.approver.network.RetrofitService
import com.anumati.approver.onBoarding.models.OTPIdentifier
import com.anumati.approver.qrCodeCheck.models.CreateOTPRequest
import com.anumati.approver.qrCodeCheck.models.VerifyOTPRequest
import com.anumati.approver.qrCodeCheck.models.VerifyOTPResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException


class OnBoardingRepo {

    fun requestOTP(
        otpIdentifier: OTPIdentifier,
        identifierValue: String,
        service: RetrofitService,
        listener: ((Response<Void>) -> Unit)?,
        errorListener: ((String?) -> Unit)?
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val otpTokenRequest =
                    CreateOTPRequest(
                        otpIdentifier.value,
                        identifierValue,
                        "test"
                    )
                val response = service.postRequestOTP(otpTokenRequest)
                listener?.invoke(response)
            } catch (e: HttpException) {
                errorListener?.invoke(null)
            } catch (e: Throwable) {
                errorListener?.invoke(null)
            } catch (e: SocketTimeoutException) {
                errorListener?.invoke(null)
            } catch (e: IOException) {
                errorListener?.invoke(null)
            }catch (e:Exception){
                errorListener?.invoke(null)
            }
        }
    }

    fun verifyOTP(
        number: String,
        otp: String,
        otpIdentifier: OTPIdentifier,
        service: RetrofitService,
        listener: ((Response<VerifyOTPResponse>) -> Unit)?,
        errorListener: ((String?) -> Unit)?
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val verifyOTPRequest = VerifyOTPRequest(otpIdentifier.value,number,otp)
                val response = service.postVerifyOTP(verifyOTPRequest)
                listener?.invoke(response)
            } catch (e: HttpException) {
                errorListener?.invoke(null)
            } catch (e: Throwable) {
                errorListener?.invoke(null)
            } catch (e: SocketTimeoutException) {
                errorListener?.invoke(null)
            } catch (e: IOException) {
                errorListener?.invoke(null)
            }catch (e:Exception){
                errorListener?.invoke(null)
            }
        }
    }
}