package com.leobit.testapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.leobit.testapplication.activities.MainActivity
import com.leobit.testapplication.R
import com.leobit.testapplication.databinding.FrameLayoutBinding

class FragmentLayout : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fragmentManager = (context as AppCompatActivity).supportFragmentManager

        val frameLayout = FrameLayoutBinding.inflate(inflater)

        var fragmentManagerTransaction = fragmentManager.beginTransaction()

        fragmentManagerTransaction.add(R.id.fragment_container, MainActivity.characterFragment)
        fragmentManagerTransaction.commit()
        return frameLayout.root
    }
}