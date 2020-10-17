package devenus.rodi.imagesearch.network.service

import devenus.rodi.imagesearch.network.response.SearchImageResponse
import devenus.rodi.imagesearch.utils.APP.KAKAO_REST_API_KEY
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchImageService {

    @GET("/v2/search/image/")
    suspend fun getImageInfo(
        @Header("Authorization") apiKey: String = "KakaoAK $KAKAO_REST_API_KEY",
        @Query("query") keyWord: String = ""
    ): SearchImageResponse
}