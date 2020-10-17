package devenus.rodi.imagesearch.network.service

import devenus.rodi.imagesearch.network.response.SearchImageResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchImageService {

    @GET("/v2/search/image/")
    suspend fun getImageInfo(
        @Header("Authorization") apiKey: String = "",
        @Query("query") keyWord: String = ""
    ): SearchImageResponse
}