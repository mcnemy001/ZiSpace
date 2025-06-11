package com.example.zispace.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.zispace.R
import com.example.zispace.data.ZiSpaceDatabase
import com.example.zispace.data.entity.InterestEntity
import com.example.zispace.data.repository.ProfileRepository
import com.example.zispace.databinding.FragmentHomeBinding
import com.google.android.flexbox.FlexboxLayout

class HomeFragment : Fragment() {
    // NIM : 10122384
    // Nama : Aziyusman Maulana
    // Kelas : ANDRO-4
    // Tanggal Pengerjaan : 11/06/2025

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        // 1. Inisialisasi semua komponen yang dibutuhkan untuk ViewModel
        val database = ZiSpaceDatabase.getDatabase(requireContext())
        val repository = ProfileRepository(database.profileDao(), database.interestDao())
        val factory = HomeViewModelFactory(repository)

        // 2. Buat ViewModel menggunakan Factory yang sudah dibuat
        homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        // Mengamati data profil dari LiveData
        homeViewModel.userProfile.observe(viewLifecycleOwner) { profile ->
            profile?.let {
                binding.profileName.text = it.name
                binding.profileDescription.text = it.description
                binding.introTitle.text = getString(R.string.intro_title_format, it.name.split(" ").firstOrNull() ?: "")
                binding.introDescription.text = it.introduction
                // TODO: Load profile image here using Glide or Picasso
            }
        }

        // Mengamati setiap daftar minat
        homeViewModel.hobbies.observe(viewLifecycleOwner) { hobbies ->
            updateInterestChips(binding.hobbyFlexbox, hobbies)
        }
        homeViewModel.favoriteFood.observe(viewLifecycleOwner) { foods ->
            updateInterestChips(binding.foodFlexbox, foods)
        }
        homeViewModel.favoriteDrinks.observe(viewLifecycleOwner) { drinks ->
            updateInterestChips(binding.drinkFlexbox, drinks)
        }
        homeViewModel.interests.observe(viewLifecycleOwner) { interests ->
            updateInterestChips(binding.interestFlexbox, interests)
        }
        homeViewModel.aspirations.observe(viewLifecycleOwner) { aspirations ->
            updateInterestChips(binding.aspirationFlexbox, aspirations)
        }
    }

    /**
     * Helper function untuk mengisi FlexboxLayout dengan chip.
     * Perhatikan tipe data sekarang adalah List<InterestEntity>
     */
    private fun updateInterestChips(flexbox: FlexboxLayout, interests: List<InterestEntity>) {
        flexbox.removeAllViews() // Hapus chip lama
        for (interest in interests) {
            val chip = LayoutInflater.from(context).inflate(R.layout.item_interest_chip, flexbox, false) as TextView
            chip.text = interest.name
            flexbox.addView(chip)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}