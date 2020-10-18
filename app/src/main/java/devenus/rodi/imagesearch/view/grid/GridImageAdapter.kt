package devenus.rodi.imagesearch.view.grid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import devenus.rodi.imagesearch.databinding.ItemGirdImageBinding
import devenus.rodi.imagesearch.network.response.ImageInfo
import devenus.rodi.imagesearch.utils.PAGING.NO_SEARCH_RESULT
import devenus.rodi.imagesearch.utils.loadUrlImage

class GridImageAdapter : PagingDataAdapter<ImageInfo, GridImageViewHolder>(itemDiff) {

    private var noResultListener: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridImageViewHolder {
        val binding =
            ItemGirdImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GridImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GridImageViewHolder, position: Int) {
        if (getItem(0)?.thumbNailUrl == NO_SEARCH_RESULT) {
            noResultListener?.invoke()
        } else {
            holder.bind(getItem(position))
        }
    }

    fun setListener(listener: () -> Unit) {
        this.noResultListener = listener
    }
}

class GridImageViewHolder(val view: ItemGirdImageBinding) : RecyclerView.ViewHolder(view.root) {

    fun bind(item: ImageInfo?) {
        view.ivGirdImage.loadUrlImage(item?.thumbNailUrl)
    }
}

val itemDiff = object : DiffUtil.ItemCallback<ImageInfo>() {
    override fun areItemsTheSame(oldItem: ImageInfo, newItem: ImageInfo): Boolean {
        return oldItem.thumbNailUrl == newItem.thumbNailUrl
    }

    override fun areContentsTheSame(oldItem: ImageInfo, newItem: ImageInfo): Boolean {
        return oldItem == newItem
    }
}
