package com.mazaira.searchtv.provider.api

import com.mazaira.searchtv.model.domain.MostPopularData
import com.mazaira.searchtv.model.domain.NewData
import com.mazaira.searchtv.model.domain.SearchData
import com.mazaira.searchtv.model.domain.TopData
import com.mazaira.searchtv.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

object ImdbProvider {

    //Private
    private fun getRetofit(method: ImdbServices) = Retrofit.Builder().baseUrl(method.baseUrl())
        .addConverterFactory(GsonConverterFactory.create()).build()

    // Properties
    private enum class ImdbServices {

        SEARCH,
        TOP250_MOVIES,
        TOP250_TV_SHOWS,
        MOST_POPULAR_MOVIES,
        MOST_POPULAR_TV_SHOWS,
        COMING_SOON,
        AUTHORIZE;

        fun baseUrl(): String {

            return when (this) {
                AUTHORIZE -> "${Constants.IMDB_API_URI}authorizeuser:read:email"
                SEARCH, TOP250_MOVIES, TOP250_TV_SHOWS, MOST_POPULAR_MOVIES, MOST_POPULAR_TV_SHOWS, COMING_SOON  -> Constants.IMDB_API_URI
            }
        }
    }


    // MARK: Services

    private interface ImdbAPIService {

        @GET("SearchMovie/${Constants.API_KEY}/{query}")
        suspend fun search(@Path("query") query: String): SearchData

        @GET("Top250Movies/${Constants.API_KEY}")
        suspend fun top250Movies(): TopData

        @GET("Top250TVs/${Constants.API_KEY}")
        suspend fun top250TVs(): TopData

        @GET("MostPopularMovies/${Constants.API_KEY}")
        suspend fun mostPopularMovies(): MostPopularData

        @GET("MostPopularTVs/${Constants.API_KEY}")
        suspend fun mostPopularTVs(): MostPopularData

        @GET("ComingSoon/${Constants.API_KEY}")
        suspend fun comingSoon(): NewData
    }

    suspend fun search(query: String): SearchData {
        val apiService = getRetofit(ImdbServices.SEARCH).create(ImdbAPIService::class.java)
        return apiService.search(query)
    }

    suspend fun top250Movies():TopData{
        val apiService = getRetofit(ImdbServices.TOP250_MOVIES).create(ImdbAPIService::class.java)
        return apiService.top250Movies()
    }

    suspend fun top250tvs():TopData{
        val apiService = getRetofit(ImdbServices.TOP250_TV_SHOWS).create(ImdbAPIService::class.java)
        return apiService.top250TVs()
    }

    suspend fun mostPopularMovies():MostPopularData{
        val apiService = getRetofit(ImdbServices.MOST_POPULAR_MOVIES).create(ImdbAPIService::class.java)
        return apiService.mostPopularMovies()
    }

    suspend fun mostPopularTvs():MostPopularData{
        val apiService = getRetofit(ImdbServices.MOST_POPULAR_TV_SHOWS).create(ImdbAPIService::class.java)
        return apiService.mostPopularTVs()
    }

    suspend fun comingSoon():NewData{
        val apiService = getRetofit(ImdbServices.COMING_SOON).create(ImdbAPIService::class.java)
        return apiService.comingSoon()
    }
}