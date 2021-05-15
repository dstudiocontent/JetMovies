package com.extack.jetmovies.api.commons

import com.extack.jetmovies.api.repository.PopularMoviesApi
import com.extack.jetmovies.api.repository.RegionalMoviesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Singleton
    @Provides
    fun retrofit(client: OkHttpClient, jsonFactory: MoshiConverterFactory): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(jsonFactory)
            .client(client)
            .build()

    @Singleton
    @Provides
    fun client(
        interceptor: HttpLoggingInterceptor,
        tokenInterceptor: TokenInterceptor
    ): OkHttpClient {
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .addNetworkInterceptor(tokenInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .followRedirects(true)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun interceptor() = HttpLoggingInterceptor()

    @Singleton
    @Provides
    fun tokenInterceptor() = TokenInterceptor()

    @Singleton
    @Provides
    fun moshiFactory(): MoshiConverterFactory = MoshiConverterFactory.create()

    @Provides
    @Singleton
    fun popularMoviesApi(retrofit: Retrofit): PopularMoviesApi =
        retrofit.create(PopularMoviesApi::class.java)

    @Provides
    @Singleton
    fun regionalMoviesApi(retrofit: Retrofit): RegionalMoviesApi =
        retrofit.create(RegionalMoviesApi::class.java)
}