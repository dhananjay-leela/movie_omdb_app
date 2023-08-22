package com.kermit.social

import android.preference.PreferenceManager
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kermit.social.data.AuthRepository
import com.kermit.social.data.KermitMovieSocialRepository
import com.kermit.social.data.MovieApiService
import com.kermit.social.data.MovieSocialRepository
import com.kermit.social.view.MainViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Dependencies {

    private val appModules = module {
        single { PreferenceManager.getDefaultSharedPreferences(androidContext()) }
    }

    private val networkModule = module {
        factory {
            HttpLoggingInterceptor()
                .setLevel(
                    if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                )
        }

        single {
            OkHttpClient.Builder()
                .addInterceptor(get<HttpLoggingInterceptor>())
                .connectTimeout(10, TimeUnit.SECONDS)
                .build()
        }

        single<Gson> {
            GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        }

        single {
            Retrofit.Builder()
                .client(get())
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(get()))
                .build()
        }

        single { MovieApiService.create(retrofit = get()) }
    }

    private val viewModelModule = module {
        single { Dispatchers.IO as CoroutineDispatcher }

        single { MainViewModel(movieRepo = get(), ioDispatcher = get(), authRepository = get()) }
    }

    private val repositoryModule = module {
        single { KermitMovieSocialRepository(apiService = get()) as  MovieSocialRepository}

        single { AuthRepository(sharedPreferences = get()) }
    }

    internal val modules = listOf(appModules, networkModule, viewModelModule, repositoryModule)
}