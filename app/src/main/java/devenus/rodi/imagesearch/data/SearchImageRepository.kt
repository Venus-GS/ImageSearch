package devenus.rodi.imagesearch.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import devenus.rodi.imagesearch.network.response.ImageInfo
import devenus.rodi.imagesearch.network.service.SearchImageService
import devenus.rodi.imagesearch.utils.PAGING.PAGE_SIZE
import kotlinx.coroutines.flow.Flow

class SearchImageRepositoryImpl(private val searchImageService: SearchImageService) :
    SearchImageRepository {
    override fun getImageInfo(keyWord: String): Flow<PagingData<ImageInfo>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE)
        ) {
            SearchImagePagingSource(searchImageService, keyWord)
        }.flow
    }
}

interface SearchImageRepository {

    fun getImageInfo(keyWord: String): Flow<PagingData<ImageInfo>>
}
