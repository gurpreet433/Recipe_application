package com.app.recipeapp.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.app.recipeapp.R
import com.app.recipeapp.databinding.FragmentDetailsBinding
import com.app.recipeapp.databinding.FragmentHomeBinding


class DetailsFragment : Fragment() {

    private var binding : FragmentDetailsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil
            .inflate<FragmentDetailsBinding>(inflater, R.layout.fragment_details, container, false)

        return binding?.root
    }
}