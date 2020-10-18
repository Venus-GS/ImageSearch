package devenus.rodi.imagesearch.view.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import devenus.rodi.imagesearch.R
import devenus.rodi.imagesearch.base.BaseFragment
import devenus.rodi.imagesearch.databinding.FragmentImageDetailBinding

@AndroidEntryPoint
class ImageDetailFragment :
    BaseFragment<FragmentImageDetailBinding>(R.layout.fragment_image_detail) {

    private val viewModel by viewModels<ImageDetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = viewModel
    }
}
