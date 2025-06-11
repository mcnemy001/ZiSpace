package com.example.zispace.ui.gallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.zispace.R
import com.example.zispace.data.ZiSpaceDatabase
import com.example.zispace.data.repository.GalleryRepository
import com.example.zispace.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: GalleryViewModel
    private lateinit var galleryAdapter: GalleryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database = ZiSpaceDatabase.getDatabase(requireContext())
        val repository = GalleryRepository(database.galleryDao())
        val factory = GalleryViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[GalleryViewModel::class.java]

        setupRecyclerView()
        observeData()
    }

    private fun setupRecyclerView() {
        galleryAdapter = GalleryAdapter()
        binding.rvGallery.apply {
            // Menggunakan GridLayoutManager dengan 3 kolom
            layoutManager = GridLayoutManager(context, 3)
            adapter = galleryAdapter
        }
    }

    private fun observeData() {
        viewModel.galleryImages.observe(viewLifecycleOwner) { images ->
            galleryAdapter.submitList(images)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}