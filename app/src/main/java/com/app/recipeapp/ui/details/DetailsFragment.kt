package com.app.recipeapp.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.app.recipeapp.R
import com.app.recipeapp.databinding.FragmentDetailsBinding
import com.app.recipeapp.databinding.FragmentHomeBinding
import com.app.recipeapp.pojo.network.Recipe
import com.bumptech.glide.Glide


class DetailsFragment : Fragment() {

    private var binding : FragmentDetailsBinding? = null
    var recipe: Recipe? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil
            .inflate<FragmentDetailsBinding>(inflater, R.layout.fragment_details, container, false)

        recipe = arguments?.getParcelable("recipe")

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.imageMain?.let {
            Glide.with(requireContext())
                .load(recipe?.image)
                .into(it)
        }

        binding?.recipeTitle?.text = recipe?.label
        binding?.moreDetail?.text = recipe?.label

        binding?.backButton?.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}