package devenus.rodi.imagesearch.data

import androidx.paging.PagingSource
import devenus.rodi.imagesearch.network.response.ImageInfo
import devenus.rodi.imagesearch.network.service.SearchImageService
import devenus.rodi.imagesearch.utils.PAGING.INIT_PAGE
import devenus.rodi.imagesearch.utils.PAGING.NO_SEARCH_RESULT
import retrofit2.HttpException
import timber.log.Timber

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

            if (resultData.metaData.totalCount == 0) {
                resultData.imageInfoList.add(ImageInfo(thumbNailUrl = NO_SEARCH_RESULT))
            }

            LoadResult.Page(
                data = resultData.imageInfoList,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (resultData.metaData.isEnd) null else currentPage + 1
            )
        } catch (error: Exception) {
            Timber.e(error)
            return LoadResult.Error(error)
        } catch (error: HttpException) {
            Timber.e(error)
            return LoadResult.Error(error)
        }
    }
}
