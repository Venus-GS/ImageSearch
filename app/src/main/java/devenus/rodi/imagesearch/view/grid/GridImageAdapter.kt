package devenus.rodi.imagesearch.view.grid

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import devenus.rodi.imagesearch.databinding.ItemGirdImageBinding
import devenus.rodi.imagesearch.network.response.ImageInfo

class GridImageAdapter : ListAdapter<ImageInfo, RecyclerView.ViewHolder>(itemDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemGirdImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}

class ItemViewHolder(private val binding: ItemGirdImageBinding) :
    RecyclerView.ViewHolder(binding.root) {

}

val itemDiff = object : DiffUtil.ItemCallback<ImageInfo>() {
    override fun areItemsTheSame(oldItem: ImageInfo, newItem: ImageInfo): Boolean {
        return oldItem.thumbNailUrl == newItem.thumbNailUrl
    }

    override fun areContentsTheSame(oldItem: ImageInfo, newItem: ImageInfo): Boolean {
        return oldItem == newItem
    }
}
