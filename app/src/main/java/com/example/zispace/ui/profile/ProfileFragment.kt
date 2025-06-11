package com.example.zispace.ui.profile

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zispace.R
import com.example.zispace.data.ZiSpaceDatabase
import com.example.zispace.data.entity.ProfileEntity
import com.example.zispace.data.repository.ProfileRepository
import com.example.zispace.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    // NIM : 10122384
    // Nama : Aziyusman Maulana
    // Kelas : ANDRO-4
    // Tanggal Pengerjaan : 11/06/2025



    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup ViewModel menggunakan Factory
        val database = ZiSpaceDatabase.getDatabase(requireContext())
        val repository = ProfileRepository(database.profileDao(), database.interestDao())
        val factory = ProfileViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]

        observeViewModel()
    }

    private fun observeViewModel() {
        // Amati data profil. Saat data datang, update seluruh UI.
        viewModel.profileData.observe(viewLifecycleOwner) { profile ->
            profile?.let {
                populateUi(it)
                setupClickListeners(it)
            }
        }
    }

    // Fungsi untuk mengisi semua data ke UI
    private fun populateUi(profile: ProfileEntity) {
        binding.profileName.text = profile.name
        binding.profileStatus.text = profile.description // Menggunakan 'description' untuk status
        binding.aboutDescription.text = profile.introduction // Menggunakan 'introduction' untuk 'About Me'
        binding.phoneNumber.text = profile.phone
        binding.emailAddress.text = profile.email
        binding.instagramUsername.text = profile.instagram
        binding.locationAddress.text = profile.location

        val context = requireContext()
        val imageResId = context.resources.getIdentifier(profile.profileImageUrl, "drawable", context.packageName)
        if (imageResId != 0) {
            binding.profileImage.setImageResource(imageResId)
        }
    }

    // Fungsi untuk mengatur semua click listener dengan data dinamis
    private fun setupClickListeners(profile: ProfileEntity) {
        binding.callCard.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${profile.phone}"))
            startActivity(intent)
        }

        binding.emailCard.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:${profile.email}"))
            startActivity(intent)
        }

        binding.instagramCard.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/${profile.instagram}"))
            startActivity(intent)
        }

        binding.mapCard.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(profile.mapsUrl))
            startActivity(intent)
        }

        binding.aboutAppCard.setOnClickListener {
            showAboutDialog()
        }
    }

    private fun showAboutDialog() {
        Dialog(requireContext()).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.dialog_about)
            window?.setBackgroundDrawableResource(android.R.color.transparent)
            window?.setLayout(
                (resources.displayMetrics.widthPixels * 0.9).toInt(),
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            findViewById<View>(R.id.close_button).setOnClickListener { dismiss() }
            show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
