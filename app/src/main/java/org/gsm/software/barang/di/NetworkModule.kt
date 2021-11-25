package org.gsm.software.barang.di

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.gsm.software.barang.BuildConfig
import org.gsm.software.barang.model.users.PostApi
import org.gsm.software.barang.model.users.UserApi
import org.gsm.software.barang.viewmodel.LoginViewModel
import org.gsm.software.barang.viewmodel.MainViewModel
import org.gsm.software.barang.viewmodel.SinupViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit


val networkModule = module {
    single {
        okHttp()
    }
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PostApi::class.java)
    }
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL2)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApi::class.java)
    }



}

val viewModelPart = module {
    viewModel { MainViewModel(get(),get()) }
    viewModel { LoginViewModel(get(),get()) }
    viewModel { SinupViewModel(get()) }
}

private val requestBodyLoggerInterceptor: Interceptor
    get() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
    }

private val requestHeaderLoggerInterceptor: Interceptor
    get() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.HEADERS
        else HttpLoggingInterceptor.Level.NONE
    }


private fun okHttp() = OkHttpClient.Builder()
    //서버로부터의 응답까지의 시간이 읽기 시간 초과보다 크면 요청 실패로 판단한다.
    .readTimeout(10, TimeUnit.SECONDS)
    //서버로 요청을 시작한 후 15초가 지날 때 까지 데이터가 안오면 에러로 판단한다.
    .connectTimeout(10,TimeUnit.SECONDS)
    .addInterceptor(requestBodyLoggerInterceptor)
    .addInterceptor(requestHeaderLoggerInterceptor)
    .build()


val myModule = listOf(networkModule, viewModelPart)