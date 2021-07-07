package com.example.rickmortyapplication.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickmortyapplication.R
import com.example.rickmortyapplication.adapters.CharactersAdapter
import com.example.rickmortyapplication.databinding.FragmentCharacterBinding
import com.example.rickmortyapplication.models.Character
import com.example.rickmortyapplication.network.RickMortyService
import com.example.rickmortyapplication.repositories.RickMortyRepository
import com.example.rickmortyapplication.viewmodel.RickMortyViewModel
import com.example.rickmortyapplication.viewmodel.RickMortyViewModelFactory
import java.util.*
import kotlin.collections.ArrayList

// Fragment which shows a list of characters
class CharacterFragment : Fragment(), CharactersAdapter.OnItemClickListener{

    private var _binding: FragmentCharacterBinding? = null

    private lateinit var viewModel: RickMortyViewModel
    private var service = RickMortyService.getInstance()

    private var characterList : ArrayList<Character>? = null
    private var characterAdapter = CharactersAdapter(this)

    private lateinit var newList : ArrayList<Character>

    // This property is only valid between onCreateView ande
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listCharacter.layoutManager = GridLayoutManager(this.context,2)

        viewModel = ViewModelProvider(this, RickMortyViewModelFactory(RickMortyRepository(service))).get(RickMortyViewModel::class.java)
        viewModel.getCharacters()

        viewModel.baseResponse.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                characterList = it.results
                newList = characterList as ArrayList<Character>
                characterAdapter.setCharacters(newList)
                binding.listCharacter.adapter = characterAdapter
//                Toast.makeText(this.context, "$characterList", Toast.LENGTH_LONG).show()
            }
        })

        //search character by name
        binding.characterSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if (newText != null) {
                    newList = characterList?.filter { it -> it.name.lowercase(Locale.getDefault()).contains(newText.lowercase()) } as ArrayList<Character>
                    characterAdapter.setCharacters(newList)
                    binding.listCharacter.adapter = characterAdapter

                    Log.d("LIST UPDATED", "${newList.size}")
                }

                return false
            }

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //open another fragment with more detail of selected character
    override fun onItemClick(position: Int) {
        val clickedCharacter = newList[position]

        Toast.makeText(this.context, "$clickedCharacter", Toast.LENGTH_LONG).show()
        val bundle = Bundle()
        bundle.putParcelable("character", clickedCharacter)

        Log.d("SELECTED POSITION", "$position")
        Log.d("SELECTED ITEM", "$clickedCharacter")

        findNavController().navigate(R.id.action_CharacterFragment_to_CharacterDetailFragment, bundle)
    }

}