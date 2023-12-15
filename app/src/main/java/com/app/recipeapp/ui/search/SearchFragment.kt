package com.app.recipeapp.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.app.recipeapp.R
import com.app.recipeapp.databinding.FragmentHomeBinding
import com.app.recipeapp.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private var binding : FragmentSearchBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil
            .inflate<FragmentSearchBinding>(inflater, R.layout.fragment_search, container, false)

        return binding!!.root
    }

}