package com.example.zispace.ui.dailyactivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zispace.data.ZiSpaceDatabase
import com.example.zispace.data.repository.DailyActivityRepository
import com.example.zispace.databinding.FragmentDailyActivityBinding
import com.example.zispace.model.ActivityItem
import com.example.zispace.model.FriendItem

class DailyActivityFragment : Fragment() {
    // NIM : 10122384
    // Nama : Aziyusman Maulana
    // Kelas : ANDRO-4
    // Tanggal Pengerjaan : 11/06/2025

    private var _binding: FragmentDailyActivityBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: DailyActivityViewModel
    private lateinit var activityAdapter: ActivityAdapter
    private lateinit var friendsAdapter: FriendsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDailyActivityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup dependency injection untuk ViewModel
        val database = ZiSpaceDatabase.getDatabase(requireContext())
        val repository = DailyActivityRepository(database.dailyActivityDao(), database.friendDao())
        val factory = DailyActivityViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[DailyActivityViewModel::class.java]

        setupRecyclerViews()
        observeData()

    }

    private fun setupRecyclerViews() {
        activityAdapter = ActivityAdapter()
        binding.rvDailyActivities.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = activityAdapter
        }

        friendsAdapter = FriendsAdapter()
        binding.rvFriends.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = friendsAdapter
        }
    }

    private fun observeData() {
        viewModel.dailyActivities.observe(viewLifecycleOwner) { activities ->
            activityAdapter.submitList(activities)
        }

        viewModel.friends.observe(viewLifecycleOwner) { friends ->
            friendsAdapter.submitList(friends)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}