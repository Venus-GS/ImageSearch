package devenus.rodi.imagesearch.network.response

import com.google.gson.annotations.SerializedName

data class SearchImageResponse (
    @SerializedName("meta")
    val metaData: MetaData,
    @SerializedName("documents")
    var imageInfoList: MutableList<ImageInfo>
)

data class MetaData(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("pageable_count")
    val pageableCount: Int,
    @SerializedName("is_end")
    val isEnd: Boolean
)

data class ImageInfo(
    @SerializedName("thumbnail_url")
    val thumbNailUrl: String,
    @SerializedName("image_url")
    val imageUrl: String = ""
)

