package devenus.rodi.imagesearch.view.grid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import devenus.rodi.imagesearch.databinding.ItemLoadStateBinding

class GridImageLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<LoadStateViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding =
            ItemLoadStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadStateViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState, retry)
    }
}

class LoadStateViewHolder(val view: ItemLoadStateBinding) : RecyclerView.ViewHolder(view.root) {

    fun bind(loadState: LoadState, retry: () -> Unit) {
        view.apply {
            btnRetry.setOnClickListener {
                retry.invoke()
                groupError.visibility = View.GONE
            }
            groupError.visibility = if (loadState is LoadState.Error) View.VISIBLE else View.GONE
            clpLoading.visibility = if (loadState is LoadState.Loading) View.VISIBLE else View.GONE
        }
    }
}
