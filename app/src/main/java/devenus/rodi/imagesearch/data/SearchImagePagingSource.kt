package devenus.rodi.imagesearch.data

import androidx.paging.PagingSource
import devenus.rodi.imagesearch.network.response.ImageInfo
import devenus.rodi.imagesearch.network.service.SearchImageService
import devenus.rodi.imagesearch.utils.PAGING.INIT_PAGE
import retrofit2.HttpException

class SearchImagePagingSource(
    private val searchImageService: SearchImageService,
    private val keyWord: String
) : PagingSource<Int, ImageInfo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImageInfo> {
        return try {
            val currentPage = params.key ?: INIT_PAGE
            val resultData = searchImageService.getImageInfo(
                keyWord = keyWord,
                page = currentPage
            )

            LoadResult.Page(
                data = resultData.imageInfo,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (resultData.imageInfo.isEmpty()) null else currentPage + 1
            )

        } catch (error: Exception) {
            return LoadResult.Error(error)
        } catch (error: HttpException) {
            return LoadResult.Error(error)
        }
    }
}