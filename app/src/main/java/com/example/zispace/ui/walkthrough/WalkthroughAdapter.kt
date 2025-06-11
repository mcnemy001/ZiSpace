package com.example.zispace.ui.walkthrough

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class WalkthroughAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    // NIM : 10122384
    // Nama : Aziyusman Maulana
    // Kelas : ANDRO-4
    // Tanggal Pengerjaan : 11/06/2025


    override fun getItemCount(): Int = 3 // Jumlah halaman walkthrough

    override fun createFragment(position: Int): Fragment {
        return WalkthroughFragment.newInstance(position)
    }
}