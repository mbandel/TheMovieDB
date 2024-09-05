//package com.mbandel.config
//
//import android.app.Application
//import dagger.hilt.android.qualifiers.ApplicationContext
//import java.io.IOException
//import java.io.InputStream
//import java.util.Properties
//import javax.inject.Inject
//import javax.inject.Singleton
//
//@Singleton
//class ConfigLoader @Inject constructor(
//    @ApplicationContext private val context: Application
//){
//    private val properties = Properties()
//    init {
//        try {
//            val input: InputStream = context.assets.open("apikeys.properties")
//            properties.load(input)
//        }catch (e: IOException) {
//            e.printStackTrace()
//        }
//    }
//
//    fun getBaseUrl(): String = properties.getProperty("BASE_URL")
//
//    fun getBearerToken(): String = properties.getProperty("BEARER_TOKEN")
//
//    fun getBackdropURL(): String = properties.getProperty("BACKDROP_URL")
//
//}