package com.example.calendar.ui.navigation

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.calendar.R
import kotlinx.android.synthetic.main.navigation_fragment.*

class NavigationFragment : Fragment() {

    companion object {
        const val COUNT = "count"

        fun newInstance(count: Int) = NavigationFragment().apply {
            arguments = Bundle().apply {
                putInt(COUNT, count)
            }
        }

        val colors = listOf(Color.BLUE, Color.RED, Color.CYAN, Color.GREEN, Color.YELLOW)
    }

    var count: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.navigation_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        count = arguments?.getInt(COUNT) ?: 0
        main.setBackgroundColor(getColor())
        textView.text = "Current count = $count"
        oneButton.setOnClickListener { openOneFragment() }
        twoButton.setOnClickListener { openTwoFragments() }
        closeButton.setOnClickListener { clearBackStack() }
    }

    fun onBackPressed() {
        val fragment = childFragmentManager.findFragmentById(R.id.contentFrame)

        if (fragment != null && fragment is NavigationFragment) {
            fragment.onBackPressed()
        } else {
            parentFragmentManager.popBackStack()
        }
    }

    private fun getColor(): Int {
        val index = if (colors.size > count) {
            count
        } else {
            count % colors.size
        }

        return colors[index]
    }

    private fun openOneFragment() {
        val fragment = newInstance(++count)
        childFragmentManager.beginTransaction()
            .replace(R.id.contentFrame, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun openTwoFragments() {
        val firstFragment = newInstance(++count)
        val secondFragment = newInstance(++count)
        childFragmentManager.beginTransaction()
            .add(R.id.contentFrame, firstFragment)
            .addToBackStack(null)
            .commit()

        childFragmentManager.beginTransaction()
            .add(R.id.contentFrame, secondFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun clearBackStack() {
        if (parentFragmentManager.backStackEntryCount > 0) {
            parentFragmentManager.popBackStack(
                parentFragmentManager.getBackStackEntryAt(0).id,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }
    }
}