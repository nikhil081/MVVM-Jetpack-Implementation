package com.example.dogs.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.dogs.databinding.FragmentDetailBinding

class DetailFragment : Fragment(), onClickHandler {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
    }

    private fun initComponents() {
        binding.handler = this
    }

    override fun onFloatingActionClicked() {
        binding.floatingActionButton2.setOnClickListener {
            val action = DetailFragmentDirections.actionListFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity?)?.setActionBarTitle("Details Fragment")

    }
}