package devenus.rodi.imagesearch.view.grid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import devenus.rodi.imagesearch.databinding.ItemGirdImageBinding
import devenus.rodi.imagesearch.network.response.ImageInfo
import devenus.rodi.imagesearch.utils.PAGING.GRID_SPAN_SIZE
import devenus.rodi.imagesearch.utils.PAGING.LOAD_SPAN_SIZE
import devenus.rodi.imagesearch.utils.PAGING.NO_SEARCH_RESULT
import devenus.rodi.imagesearch.utils.loadUrlImage
import java.text.SimpleDateFormat
import java.util.Locale

class GridImageAdapter : PagingDataAdapter<ImageInfo, GridImageViewHolder>(itemDiff) {

    private lateinit var noResultListener: (() -> Unit)
    private lateinit var imageClickListener: ImageClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridImageViewHolder {
        val binding =
            ItemGirdImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GridImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GridImageViewHolder, position: Int) {
        if (getItem(0)?.thumbNailUrl == NO_SEARCH_RESULT) {
            noResultListener.invoke()
        } else {
            holder.bind(getItem(position), imageClickListener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount) {
            GRID_SPAN_SIZE
        } else {
            LOAD_SPAN_SIZE
        }
    }

    fun setNoResultListener(listener: () -> Unit) {
        this.noResultListener = listener
    }

    fun setImageClickListener(listener: ImageClickListener) {
        this.imageClickListener = listener
    }

    interface ImageClickListener {
        fun onClick(imageUrl: String, displaySiteName: String, dateTime: String)
    }
}

class GridImageViewHolder(val view: ItemGirdImageBinding) : RecyclerView.ViewHolder(view.root) {

    fun bind(item: ImageInfo?, imageClickListener: GridImageAdapter.ImageClickListener) {
        val dateTime = item?.dateTime?.let {
            SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(it)
        } ?: run {
            ""
        }

        view.ivGirdImage.apply {
            loadUrlImage(this, item?.thumbNailUrl)
            setOnClickListener {
                imageClickListener.onClick(item?.imageUrl!!, item.displaySiteName, dateTime)
            }
        }
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
