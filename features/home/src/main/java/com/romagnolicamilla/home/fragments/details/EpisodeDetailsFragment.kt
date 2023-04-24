package com.romagnolicamilla.home.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.romagnolicamilla.domain.show.entity.Episode
import com.romagnolicamilla.home.R
import com.romagnolicamilla.home.databinding.FragmentEpisodeDetailsBinding
import com.romagnolicamilla.uicommon.extensions.fromHtml
import com.romagnolicamilla.uicommon.extensions.loadImage
import dagger.hilt.android.AndroidEntryPoint

const val EPISODE = "com.romagnolicamilla.EPISODE"

@AndroidEntryPoint
class EpisodeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentEpisodeDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleArguments()
        initListeners()
    }

    private fun initView(episode: Episode) {
        with(binding) {
            textViewName.text = episode.name
            textViewNumber.text = getString(R.string.episode, episode.number)
            textViewSeason.text = getString(R.string.season, episode.season)
            textViewSummary.text = episode.summary?.fromHtml()
            imageViewPoster.loadImage(binding.root, episode.image?.getImageUrl())
        }
    }

    private fun handleArguments() {
        arguments?.getParcelable<Episode>(EPISODE)?.run {
            initView(this)
        }
    }

    private fun initListeners() {
        binding.imageButtonReturn.setOnClickListener { findNavController().navigateUp() }
    }

}

