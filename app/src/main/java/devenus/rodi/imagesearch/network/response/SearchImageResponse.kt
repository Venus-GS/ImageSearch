package devenus.rodi.imagesearch.network.response

import com.google.gson.annotations.SerializedName

data class SearchImageResponse (
    @SerializedName("documents")
    val imageInfo: List<ImageInfo>
)

data class ImageInfo(
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("thumbnail_url")
    val thumbNailUrl: String
)

