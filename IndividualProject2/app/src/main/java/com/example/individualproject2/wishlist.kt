package com.example.individualproject2

class Wishlist(
    val name: String,
    val price: String,
    val url: String
)

class Fetcher {
    companion object {
        private val wishList: MutableList<Wishlist> = mutableListOf()
        //wish object constructor
        fun postWish(name: String, price: String, url: String): MutableList<Wishlist> {
            val newWish = Wishlist(name, price, url)
            wishList.add(newWish)
            return wishList
        }

        fun getWishList(): List<Wishlist> {
            return wishList
        }

    }
}
