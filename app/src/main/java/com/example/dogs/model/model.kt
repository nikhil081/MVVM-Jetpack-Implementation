package com.example.dogs.model

import com.google.gson.annotations.SerializedName

data class DogBreed(
    @SerializedName("id")
    val breedId: String?,

    @SerializedName("name")
    val dogBreed: String?,

    @SerializedName("life_span")
    val lifeSpan: String?,

    @SerializedName("bred_for")
    val bredFor: String?,

    @SerializedName("breed_group")
    val breedGroup: String?,

    @SerializedName("url")
    val imageUrl: String?,

    @SerializedName("temperament")
    val tempermant: String?,
    )


