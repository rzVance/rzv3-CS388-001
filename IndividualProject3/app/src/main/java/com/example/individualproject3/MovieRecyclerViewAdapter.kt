package com.example.individualproject3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.individualproject3.R.id
import jp.wasabeef.glide.transformations.BlurTransformation

class MovieRecyclerViewAdapter(
    private val movies: List<Movie>,
    private val mListener: OnListFragmentInteractionListener?
)
    : RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie, parent, false)
        return MovieViewHolder(view)
    }
    inner class MovieViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: Movie? = null
        val mMovieTitle: TextView = mView.findViewById<View>(id.movie_title) as TextView
        val mMovieDescription: TextView = mView.findViewById<View>(id.movie_description) as TextView
        val mMoviePicture: ImageView = mView.findViewById<View>(id.movie_picture) as ImageView
        val mMovieBackdrop: ImageView = mView.findViewById<View>(id.movie_backdrop) as ImageView

        override fun toString(): String {
            return mMovieTitle.toString()
        }
    }
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.mItem = movie
        holder.mMovieTitle.text = movie.title
        holder.mMovieDescription.text = movie.overview

        Glide.with(holder.mView)
            .load("https://image.tmdb.org/t/p/w500/"+movie.poster_path)
            .centerInside()
            .into(holder.mMoviePicture)

        Glide.with(holder.mView)
            .load("https://image.tmdb.org/t/p/w500/" + movie.backdrop_path)
            .centerCrop()
            .transform(BlurTransformation(25, 3))
            .skipMemoryCache(true)
            .into(holder.mMovieBackdrop)

        holder.mView.setOnClickListener {
            holder.mItem?.let { movie ->
                mListener?.onItemClick(movie)
            }
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}