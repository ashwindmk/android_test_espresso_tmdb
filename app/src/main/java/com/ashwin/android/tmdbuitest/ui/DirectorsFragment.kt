package com.ashwin.android.tmdbuitest.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.ashwin.android.tmdbuitest.R
import com.ashwin.android.tmdbuitest.databinding.FragmentDirectorsBinding

class DirectorsFragment : Fragment(R.layout.fragment_directors) {
    companion object{
        fun stringBuilderForDirectors(directors: ArrayList<String>): String{
            val sb = StringBuilder()
            for(director in directors){
                sb.append(director + "\n")
            }
            return sb.toString()
        }
    }

    private lateinit var binding: FragmentDirectorsBinding
    private val directors: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { args ->
            directors.addAll(args.get("args_directors") as List<String>)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDirectorsBinding.bind(view)
        setDirectors()
    }

    private fun setDirectors(){
        binding.directorsText.text = stringBuilderForDirectors(directors)
    }
}
