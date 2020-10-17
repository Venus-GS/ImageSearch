package devenus.rodi.imagesearch.data

import devenus.rodi.imagesearch.network.response.ImageInfo
import devenus.rodi.imagesearch.network.service.SearchImageService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SearchImageRepositoryImpl(private val searchImageService: SearchImageService) :
    SearchImageRepository {
    override fun getImageInfo(keyWord: String): Flow<List<ImageInfo>> {
        return flow {
            emit(searchImageService.getImageInfo(keyWord = keyWord).documents)
        }.flowOn(Dispatchers.IO)
    }
}

interface SearchImageRepository {

    fun getImageInfo(keyWord: String): Flow<List<ImageInfo>>
}