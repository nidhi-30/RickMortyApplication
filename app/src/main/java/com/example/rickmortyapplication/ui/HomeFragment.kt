package com.example.rickmortyapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.rickmortyapplication.R
import com.example.rickmortyapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
//    private lateinit var viewModel: RickMortyViewModel
//    private var service = RickMortyService.getInstance()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel =ViewModelProvider(this, RickMortyViewModelFactory(RickMortyRepository(service))).get(RickMortyViewModel::class.java)

        binding.character.setOnClickListener {

            findNavController().navigate(R.id.action_HomeFragment_to_CharacterFragment)
//            viewModel.baseResponse.observe(viewLifecycleOwner, Observer {
//                Toast.makeText(this.context, "${it}", Toast.LENGTH_LONG).show()
//            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}