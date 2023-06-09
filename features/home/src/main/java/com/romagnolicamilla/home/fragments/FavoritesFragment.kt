package com.romagnolicamilla.home.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.romagnolicamilla.domain.show.entity.Show
import com.romagnolicamilla.home.R
import com.romagnolicamilla.home.adapters.ShowAdapter
import com.romagnolicamilla.home.databinding.FragmentFavoritesBinding
import com.romagnolicamilla.home.fragments.details.ID
import com.romagnolicamilla.presentation.viewmodels.home.FavoritesViewModel
import com.romagnolicamilla.uicommon.extensions.hide
import com.romagnolicamilla.uicommon.extensions.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding

    private val favoritesViewModel: FavoritesViewModel by viewModels()

    private val favoritesAdapter by lazy { ShowAdapter(::onShowClick) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initView()
        initListeners()
        favoritesViewModel.retrieveFavorites()
    }

    private fun initListeners() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                favoritesViewModel.handleQueryChange(newText)
                return true
            }
        })
    }

    private fun initView() {
        binding.recyclerViewShows.adapter = favoritesAdapter
    }

    private fun initObservers() {
        favoritesViewModel.getFavorites().observe(viewLifecycleOwner) { list ->
            if (list.isNullOrEmpty()) {
                binding.textViewEmptyView.show()
                binding.recyclerViewShows.hide()
            } else {
                binding.textViewEmptyView.hide()
                binding.recyclerViewShows.show()
                favoritesAdapter.submitList(list)
            }
        }
    }

    private fun onShowClick(show: Show) {
        findNavController().navigate(
            R.id.action_favoritesFragment_to_detailsFragment,
            bundleOf(ID to show.id)
        )
    }
}