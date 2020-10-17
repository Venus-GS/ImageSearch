package devenus.rodi.imagesearch.view.grid

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import devenus.rodi.imagesearch.R
import devenus.rodi.imagesearch.base.BaseFragment
import devenus.rodi.imagesearch.databinding.FragmentGridImageBinding

@AndroidEntryPoint
class GridImageFragment : BaseFragment<FragmentGridImageBinding>(R.layout.fragment_grid_image) {

    private val viewModel by viewModels<GridImageViewModel>()

    private val adapter: GridImageAdapter by lazy { GridImageAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            viewModel = this@GridImageFragment.viewModel
            rvImageList.adapter = adapter
        }

        viewModel.keyWord.observe(viewLifecycleOwner) {
            viewModel.searchImage()
        }

        viewModel.imgUrlList.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
        }
    }
}