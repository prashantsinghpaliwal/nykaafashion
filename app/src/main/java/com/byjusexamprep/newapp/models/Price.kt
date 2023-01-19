package com.byjusexamprep.newapp.models

data class Price(
    val max_price: Double,
    val min_price: Int,
    val price_range: List<PriceRange>
)