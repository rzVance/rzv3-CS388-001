package com.example.individualproject2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var wishList: MutableList<Wishlist>
    lateinit var adapter: WishAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wishRv = findViewById<RecyclerView>(R.id.wishRv)
        wishList = Fetcher.getWishList().toMutableList()

        // Set up the adapter with a long click listener for deletion
        adapter = WishAdapter(wishList) { position ->
            deleteWish(position) // Handle deletion
        }
        wishRv.adapter = adapter
        wishRv.layoutManager = LinearLayoutManager(this)

        val nameInput = findViewById<EditText>(R.id.nameInput)
        val priceInput = findViewById<EditText>(R.id.priceInput)
        val urlInput = findViewById<EditText>(R.id.urlInput)

        findViewById<Button>(R.id.addButton).setOnClickListener {
            val name = nameInput.text.toString()
            val price = priceInput.text.toString()
            val url = urlInput.text.toString()
            // Update wishlist
            Fetcher.postWish(name, price, url)
            wishList.add(Wishlist(name, price, url))
            adapter.notifyItemInserted(wishList.size - 1)
            // Clear after input
            nameInput.text.clear()
            priceInput.text.clear()
            urlInput.text.clear()

            wishRv.scrollToPosition(wishList.size - 1)
        }
    }

    // Delete a wish
    private fun deleteWish(position: Int) {
        wishList.removeAt(position)
        adapter.notifyItemRemoved(position) // Notify
    }
}
