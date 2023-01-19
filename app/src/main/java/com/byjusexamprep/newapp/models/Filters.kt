package com.byjusexamprep.newapp.models

data class Filters(
    val brand: List<Brand>,
    val category: List<Category>,
    val discount: List<Discount>,
    val price: Price,
    val star_rating: List<StarRating>
)