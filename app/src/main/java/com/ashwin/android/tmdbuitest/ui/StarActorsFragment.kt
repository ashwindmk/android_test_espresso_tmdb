package com.ashwin.android.tmdbuitest.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.ashwin.android.tmdbuitest.R
import com.ashwin.android.tmdbuitest.databinding.FragmentStarActorsBinding

class StarActorsFragment : Fragment(R.layout.fragment_star_actors) {
    companion object {
        fun stringBuilderForStarActors(actors: ArrayList<String>): String{
            val sb = StringBuilder()
            for (actor in actors) {
                sb.append(actor + "\n")
            }
            return sb.toString()
        }
    }

    private lateinit var binding: FragmentStarActorsBinding
    private val starActors: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { args ->
            starActors.addAll(args.get("args_actors") as List<String>)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStarActorsBinding.bind(view)
        setActors()
    }

    private fun setActors(){
        binding.starActorsText.text = stringBuilderForStarActors(starActors)
    }
}
