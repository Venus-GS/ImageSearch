package devenus.rodi.imagesearch.view.grid

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import devenus.rodi.imagesearch.R
import devenus.rodi.imagesearch.base.BaseFragment
import devenus.rodi.imagesearch.databinding.FragmentGridImageBinding
import devenus.rodi.imagesearch.utils.hideKeyBoard
import devenus.rodi.imagesearch.utils.options

@AndroidEntryPoint
class GridImageFragment : BaseFragment<FragmentGridImageBinding>(R.layout.fragment_grid_image) {

    private val viewModel by viewModels<GridImageViewModel>()

    private val adapter: GridImageAdapter by lazy { GridImageAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter.apply {
            setNoResultListener { this@GridImageFragment.viewModel.setNoResult() }
            setImageClickListener(object : GridImageAdapter.ImageClickListener {
                override fun onClick(imageUrl: String, displaySiteName: String, dateTime: String) {
                    findNavController().navigate(
                        GridImageFragmentDirections.actionToImageDetail(
                            imageUrl = imageUrl,
                            displaySiteName = displaySiteName,
                            dateTime = dateTime
                        ), options
                    )
                }
            })
        }

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

        viewModel.hideKeyBoard.observe(viewLifecycleOwner) {
            requireView().hideKeyBoard()
            binding.etKeyWord.clearFocus()
        }
    }
}