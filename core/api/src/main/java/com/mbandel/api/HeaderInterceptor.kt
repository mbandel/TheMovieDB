package com.mbandel.api

import com.mbandel.api.ApiConst.APPLICATION_JSON
import com.mbandel.api.ApiConst.AUTHORIZATION
import com.mbandel.api.ApiConst.BEARER
import com.mbandel.api.ApiConst.BEARER_TOKEN
import com.mbandel.api.ApiConst.CONTENT_TYPE
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Singleton

@Singleton
internal class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = runBlocking {
        try {
            chain.run {
                proceed(
                    request()
                        .newBuilder()
                        .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                        .addHeader(AUTHORIZATION, "$BEARER $BEARER_TOKEN")
                        .build()
                )
            }
        } catch (e: Exception) {
            chain.run {
                proceed(
                    request().newBuilder().build()
                )
            }
        }
    }
}