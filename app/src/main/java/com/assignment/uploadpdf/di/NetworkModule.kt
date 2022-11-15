package com.assignment.uploadpdf.di

import com.assignment.uploadpdf.data.network.PdfUploadApi
import com.assignment.uploadpdf.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePdfUploadApi(retrofit: Retrofit): PdfUploadApi {
        return retrofit.create(PdfUploadApi::class.java)
    }
}