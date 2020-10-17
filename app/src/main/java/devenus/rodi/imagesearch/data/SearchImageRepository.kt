package devenus.rodi.imagesearch.data

import devenus.rodi.imagesearch.network.response.SearchImageResponse
import devenus.rodi.imagesearch.network.service.SearchImageService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SearchImageRepositoryImpl(private val searchImageService: SearchImageService) :
    SearchImageRepository {
    override fun getImageInfo(keyWord: String): Flow<SearchImageResponse> {
        return flow {
            emit(searchImageService.getImageInfo(keyWord = keyWord))
        }.flowOn(Dispatchers.IO)
    }
}

interface SearchImageRepository {

    fun getImageInfo(keyWord: String): Flow<SearchImageResponse>
}