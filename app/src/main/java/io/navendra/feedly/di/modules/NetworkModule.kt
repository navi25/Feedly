package io.navendra.feedly.di.modules

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import io.navendra.feedly.data.source.remote.FeedlyApiInterface
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule (private val baseUrl: String){

    @Provides @Singleton
    fun provideOkhttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides @Singleton
    fun provideMoshi() : Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi) : Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    @Provides @Singleton
    fun provideApiInterface(retrofit: Retrofit): FeedlyApiInterface = retrofit.create(
        FeedlyApiInterface::class.java)
}