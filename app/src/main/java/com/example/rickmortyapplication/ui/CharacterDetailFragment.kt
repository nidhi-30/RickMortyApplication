package com.example.rickmortyapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.rickmortyapplication.R
import com.example.rickmortyapplication.databinding.FragmentCharacterDetailBinding
import com.example.rickmortyapplication.models.Character

// Fragment to show detail of selected character
class CharacterDetailFragment : Fragment() {

    private var _binding: FragmentCharacterDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var args : Character? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)

        val bundle = this.arguments
        if (bundle != null){
            args = bundle.getParcelable("character")
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(view.context)
            .load(args?.image)
            .error(R.drawable.ic_launcher_background)
            .into(binding.characterImage)

        binding.characterName.text = args?.name
        binding.characterStatus.text = args?.status
        binding.characterSpecies.text = args?.species
        binding.characterGender.text = args?.gender
        binding.characterOrigin.text = args?.origin?.name
        binding.characterLocation.text = args?.location?.name

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}