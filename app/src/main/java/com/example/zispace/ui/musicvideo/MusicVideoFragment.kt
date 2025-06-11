package com.example.zispace.ui.musicvideo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zispace.R
import androidx.lifecycle.ViewModelProvider
import com.example.zispace.data.ZiSpaceDatabase
import com.example.zispace.data.repository.MusicVideoRepository
import com.example.zispace.databinding.FragmentMusicVideoBinding

class MusicVideoFragment : Fragment() {
    // NIM : 10122384
    // Nama : Aziyusman Maulana
    // Kelas : ANDRO-4
    // Tanggal Pengerjaan : 11/06/2025

    private var _binding: FragmentMusicVideoBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MusicVideoViewModel
    private lateinit var musicAdapter: MusicAdapter
    private lateinit var videoAdapter: VideoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMusicVideoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Setup ViewModel menggunakan Factory untuk mengambil data dari Room
        val database = ZiSpaceDatabase.getDatabase(requireContext())
        val repository = MusicVideoRepository(database.musicDao(), database.videoDao())
        val factory = MusicVideoViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[MusicVideoViewModel::class.java]

        // 2. Menyiapkan RecyclerView
        setupRecyclerViews()

        // 3. Mengamati perubahan data dari ViewModel
        observeData()
    }

    private fun setupRecyclerViews() {
        // Setup untuk daftar musik
        musicAdapter = MusicAdapter()
        binding.rvMusic.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = musicAdapter
            // Mencegah RecyclerView di dalam NestedScrollView agar tidak lag
            isNestedScrollingEnabled = false
        }

        // Setup untuk daftar video
        videoAdapter = VideoAdapter()
        binding.rvVideo.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = videoAdapter
            isNestedScrollingEnabled = false
        }
    }

    private fun observeData() {
        // Tampilkan daftar musik saat datanya tersedia
        viewModel.favoriteMusic.observe(viewLifecycleOwner) { musicList ->
            musicAdapter.submitList(musicList)
        }

        // Tampilkan daftar video saat datanya tersedia
        viewModel.favoriteVideos.observe(viewLifecycleOwner) { videoList ->
            videoAdapter.submitList(videoList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
