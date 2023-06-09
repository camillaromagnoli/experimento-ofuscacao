package com.romagnolicamilla.home.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.romagnolicamilla.domain.cast.entity.CastDetails
import com.romagnolicamilla.domain.cast.entity.Person
import com.romagnolicamilla.domain.show.entity.Show
import com.romagnolicamilla.home.R
import com.romagnolicamilla.home.adapters.ShowAdapter
import com.romagnolicamilla.home.databinding.FragmentCastDetailsBinding
import com.romagnolicamilla.presentation.viewmodels.home.details.CastDetailsViewModel
import com.romagnolicamilla.uicommon.extensions.loadImage
import com.romagnolicamilla.uicommon.extensions.showErrorModal
import com.romagnolicamilla.uicommon.progress.ProgressDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

const val PERSON = "com.romagnolicamilla.PERSON"

@AndroidEntryPoint
class CastDetailsFragment : Fragment(), CoroutineScope by MainScope() {

    private lateinit var binding: FragmentCastDetailsBinding

    private val progress by lazy { ProgressDialog(requireContext()) }
    private val showAdapter by lazy { ShowAdapter(::onShowClick) }

    private val castDetailsViewModel: CastDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCastDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleArguments()
        initListeners()
        initObservers()
    }

    private fun initView(person: Person) {
        with(binding) {
            recyclerViewShows.adapter = showAdapter
            textViewName.text = person.name
            imageViewPoster.loadImage(root, person.image?.getImageUrl())
        }
    }

    private fun handleArguments() {
        arguments?.getParcelable<Person>(PERSON)?.run {
            initView(this)
            castDetailsViewModel.id = id
            loadDetails()
        }
    }

    private fun initListeners() {
        binding.imageButtonReturn.setOnClickListener { findNavController().navigateUp() }
    }

    private fun initObservers() {
        castDetailsViewModel.getCastDetails().observe(viewLifecycleOwner) { state ->
            state.handleIt(
                loading = { progress.showProgress() },
                stopLoading = { progress.hideProgress() },
                success = { onSuccessState(it) },
                error = { onErrorState(it) }
            )
        }
    }

    private fun onErrorState(throwable: Throwable?) {
        showErrorModal(throwable = throwable, action = { _, _ -> loadDetails() })
    }

    private fun loadDetails() {
        launch {
            castDetailsViewModel.loadDetails()
        }
    }

    private fun onSuccessState(data: List<CastDetails>?) {
        data?.map { it.embedded?.show }?.takeUnless { it.isNullOrEmpty() }?.run {
            showAdapter.submitList(this)
        }
    }

    private fun onShowClick(show: Show) {
        findNavController().navigate(
            R.id.action_castDetailsFragment_to_detailsFragment,
            bundleOf(ID to show.id)
        )
    }

    override fun onDestroy() {
        cancel()
        super.onDestroy()
    }
}

