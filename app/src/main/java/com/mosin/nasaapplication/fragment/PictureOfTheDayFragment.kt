package com.mosin.nasaapplication.fragment

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.*
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.mosin.nasaapplication.R
import com.mosin.nasaapplication.databinding.PictureOfDayFragmentBinding
import com.mosin.nasaapplication.model.PictureOfTheDayData
import com.mosin.nasaapplication.model.PictureOfTheDayViewModel

class PictureOfTheDayFragment : Fragment() {
    private var show = false

    private var ui: PictureOfDayFragmentBinding? = null
    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = PictureOfDayFragmentBinding.inflate(inflater, container, false).also {
        ui = it
        viewModel.getData()
            .observe(viewLifecycleOwner, Observer<PictureOfTheDayData> { renderData(it) })
        ui?.imageView?.setOnClickListener { showComponents() }
        ui?.description?.setOnClickListener { hideComponents() }
        ui?.descriptionHeader?.setOnClickListener { hideComponents() }

    }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        textListener()
    }

    private fun showComponents() {
        show = true

        val constraintSet = ConstraintSet()
        constraintSet.clone(requireActivity(), R.layout.picture_of_day_fragment_end)

        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(3f)
        transition.duration = 1200

        TransitionManager.beginDelayedTransition(ui?.constraintContainer, transition)
        constraintSet.applyTo(ui?.constraintContainer)
    }

    private fun hideComponents() {
        show = false

        val constraintSet = ConstraintSet()
        constraintSet.clone(requireActivity(), R.layout.picture_of_day_fragment)

        val transition = ChangeBounds()
        transition.interpolator = AnticipateOvershootInterpolator(3f)
        transition.duration = 1200

        TransitionManager.beginDelayedTransition(ui?.constraintContainer, transition)
        constraintSet.applyTo(ui?.constraintContainer)
    }

//    private fun textListener() {
//        ui?.inputLayout?.clearFocus()
//        ui?.inputLayout?.setEndIconOnClickListener {
//            startActivity(Intent(Intent.ACTION_VIEW).apply {
//                data =
//                    Uri.parse("https://en.wikipedia.org/wiki/${ui?.inputEditText?.text.toString()}")
//            })
//        }
//    }

    private fun renderData(data: PictureOfTheDayData) {
        when (data) {
            is PictureOfTheDayData.Success -> {
                val serverResponseData = data.serverResponseData
                val url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                    toast("Ой! Что-то пошло не так! Похоже что ссылка пустая!")
                } else {
                    //showSuccess()
                    ui?.imageView?.load(url) {
                        lifecycle(this@PictureOfTheDayFragment)
                        error(R.drawable.ic_load_error_vector)
                        placeholder(R.drawable.no_foto)
                    }
                    ui?.descriptionHeader?.text = serverResponseData.title
                    ui?.description?.text = serverResponseData.explanation
                    ui?.date?.text = serverResponseData.date
                }
            }
        }
    }

    private fun Fragment.toast(string: String?) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).apply {
            setGravity(Gravity.BOTTOM, 0, 250)
            show()
        }
    }

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
        private var isMain = true
    }
}

object ThemeHolder {
    var theme = R.style.NasaApplication
}
