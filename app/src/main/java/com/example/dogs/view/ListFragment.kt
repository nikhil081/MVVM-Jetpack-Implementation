package com.example.dogs.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.Navigator
import com.example.dogs.R
import com.example.dogs.databinding.FragmentListBinding


class ListFragment : Fragment(), onClickHandler {
    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
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
        binding.floatingActionButton.setOnClickListener {
            val action = ListFragmentDirections.actionDetailFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

}

interface onClickHandler {
    fun onFloatingActionClicked()
}