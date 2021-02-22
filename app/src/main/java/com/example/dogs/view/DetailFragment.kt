package com.example.dogs.view

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.dogs.databinding.FragmentDetailBinding
import com.example.dogs.getProgressDrawable
import com.example.dogs.loadImage
import com.example.dogs.model.DogPalette
import com.example.dogs.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewModel: DetailViewModel
    private var dogUuid = 0

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
        arguments?.let {
            dogUuid = DetailFragmentArgs.fromBundle(it).dogUuid
        }
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.fetch(dogUuid)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.dogLiveData.observe(viewLifecycleOwner, Observer { dog ->
            dog?.let {
                dogName.text = dog.dogBreed
                dogLifeSpan.text = dog.lifeSpan
                dogTempermant.text = dog.tempermant
                context?.let {
                    dogImage.loadImage(dog.imageUrl, getProgressDrawable(it))
                }
                it.imageUrl?.let {
                    setUpBackGround(it)
                }
            }
        })
    }

    private fun setUpBackGround(url: String) {
        Glide.with(this).asBitmap().load(url).into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                Palette.from(resource).generate { palette ->
                    val intColor = palette?.vibrantSwatch?.rgb ?: 0
                    val myPalette = DogPalette(intColor)
                    binding.palette = myPalette
                }
            }

            override fun onLoadCleared(placeholder: Drawable?) {

            }

        })

    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity?)?.setActionBarTitle("Details Fragment")
    }
}