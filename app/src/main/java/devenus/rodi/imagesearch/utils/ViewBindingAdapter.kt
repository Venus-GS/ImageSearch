package devenus.rodi.imagesearch.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("goneUnless")
fun goneUnless(view: View, visible: Boolean) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("loadUrlImage")
fun loadUrlImage(imageView: ImageView, url: String?) {
    url?.let {
        Glide.with(imageView)
            .load(url)
            .fitCenter()
            .into(imageView)
    }
}
