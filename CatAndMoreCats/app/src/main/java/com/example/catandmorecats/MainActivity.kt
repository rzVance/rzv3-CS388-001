package com.example.catandmorecats

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

            val client = AsyncHttpClient()
            val params = RequestParams()
            params["limit"] = "5"
            params["page"] = "0"
            client["https://api.thecatapi.com/v1/images/search", params, object :
                TextHttpResponseHandler() {
                override fun onSuccess(statusCode: Int, headers: Headers, response: String) {
                    // called when response HTTP status is "200 OK"
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    errorResponse: String,
                    t: Throwable?
                ) {
                    // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                }
            }]
            val params = RequestParams()
            params["limit"] = "5"
            params["page"] = "0"

            client["https://api.thecatapi.com/v1/images/search", params, object :
                JsonHttpResponseHandler() {
                override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                    // Access a JSON array response with `json.jsonArray`
                    Log.d("DEBUG ARRAY", json.jsonArray.toString())
                    // Access a JSON object response with `json.jsonObject`
                    Log.d("DEBUG OBJECT", json.jsonObject.toString())
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    response: String,
                    throwable: Throwable?
                ) {
                }
            }]
        }
    }
}