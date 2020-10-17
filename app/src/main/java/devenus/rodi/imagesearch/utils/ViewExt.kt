package devenus.rodi.imagesearch.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadUrlImage(url: String?) {
    url?.let {
        Glide.with(this)
            .load(url)
            .fitCenter()
            .into(this)
    }
}