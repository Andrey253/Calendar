package com.example.calendar.ui.state

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import com.example.calendar.R
import com.example.calendar.base.BaseFragment
import kotlinx.android.synthetic.main.state_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class StateFragment : BaseFragment() {

    companion object {

        const val EDIT_TEXT = "edit_text"

        fun newInstance() = StateFragment()
    }

    private val viewModel: StateViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.state_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        editText.doAfterTextChanged { viewModel.text.value = it.toString() }
        noSaveButton.setOnClickListener { viewModel.state.value = StateViewState.NoSave }
        instantStateButton.setOnClickListener { viewModel.state.value = StateViewState.InstantSave }
        viewModelButton.setOnClickListener { viewModel.state.value = StateViewState.ViewModelSave }
        viewModelWithStateButton.setOnClickListener {
            viewModel.state.value = StateViewState.ViewModelWithStateSave
        }

        restoreState(savedInstanceState)
    }

    private fun restoreState(savedInstanceState: Bundle?) {
        when {
            savedInstanceState?.getString(EDIT_TEXT) != null -> {
                val text = savedInstanceState.getString(EDIT_TEXT)
                Timber.d("State. restore instance state: $text")
                editText.setText(text)
            }

            viewModel.state.value == StateViewState.ViewModelSave -> {
                Timber.d("State. restore from view model : ${viewModel.text.value}")
                editText.setText(viewModel.text.value)
            }

            viewModel.state.value == StateViewState.ViewModelWithStateSave -> {
                Timber.d("State. restore from view model with state: ${viewModel.text.value}")
                editText.setText(viewModel.textWithState.value)
            }

            else -> Timber.d("State. No restore state")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (viewModel.state.value == StateViewState.InstantSave) {
            val text = editText.text.toString()
            Timber.d("State. onSaveInstanceState: $text")
            outState.putString(EDIT_TEXT, text)
        }
    }
}