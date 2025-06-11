package com.example.zispace.ui.walkthrough

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.zispace.R
import com.example.zispace.databinding.FragmentWalkthroughBinding

class WalkthroughFragment : Fragment() {
    // NIM : 10122384
    // Nama : Aziyusman Maulana
    // Kelas : ANDRO-4
    // Tanggal Pengerjaan : 11/06/2025


    private var _binding: FragmentWalkthroughBinding? = null
    private val binding get() = _binding!!

    private var position: Int = 0

    companion object {
        private const val ARG_POSITION = "position"

        fun newInstance(position: Int): WalkthroughFragment {
            val fragment = WalkthroughFragment()
            val args = Bundle()
            args.putInt(ARG_POSITION, position)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(ARG_POSITION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWalkthroughBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupContent()
    }

    private fun setupContent() {
        when (position) {
            0 -> {
                binding.imageWalkthrough.setImageResource(R.drawable.walkthrough_1)
                binding.titleWalkthrough.text = "Hey, It's Me!"
                binding.descriptionWalkthrough.text = "Get to know a little more about who I am — my story, my passions, and what makes me... well, me."
                binding.root.setBackgroundColor(resources.getColor(R.color.background_2, null))
            }
            1 -> {
                binding.imageWalkthrough.setImageResource(R.drawable.walkthrough_2)
                binding.titleWalkthrough.text = "Welcome\nto My World"
                binding.descriptionWalkthrough.text = "From my daily routines to things I love doing, and the awesome people I call friends — it's all here."
                binding.root.setBackgroundColor(resources.getColor(R.color.background_1, null))
                binding.titleWalkthrough.setTextColor(resources.getColor(R.color.text_primary_dark, null))
                binding.descriptionWalkthrough.setTextColor(resources.getColor(R.color.text_primary_dark, null))
            }
            2 -> {
                binding.imageWalkthrough.setImageResource(R.drawable.walkthrough_3)
                binding.titleWalkthrough.text = "Let's Connect!"
                binding.descriptionWalkthrough.text = "Want to reach out? You can contact me, check out where I am, or find me on social media. Let's stay in touch."
                binding.root.setBackgroundColor(resources.getColor(R.color.background_2, null))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}