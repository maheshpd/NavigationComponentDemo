package com.createsapp.navigationcomponentdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_first.view.*


class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        view.textView.setOnClickListener {
            val action = FirstFragmentDirections.navigateToSecondFragment(22)
            Navigation.findNavController(view).navigate(action)
        }

        return view
    }
}