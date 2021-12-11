package com.leobit.testapplication.fragments


import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import coil.load
import com.leobit.testapplication.R
import com.leobit.testapplication.databinding.FragmentDetailBinding


/**
 * Fragment for displaying image from recycler view
 *
 * */
class DetailsFragment : Fragment() {

    private var characterText: String? = null
    private var characterName: String? = null
    private var characterImage: String? = null
  lateinit  private var binding : FragmentDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            characterText = it.getString("characterText")
            characterName = it.getString("characterName")
            characterImage = it.getString("characterImage")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var detailBinding = FragmentDetailBinding.inflate(inflater)
            binding=  detailBinding

        detailBinding.idCharacter.text = characterText

        detailBinding.sharedImage.let {
            val imgUrl = characterImage?.toUri()?.buildUpon()?.scheme("https")?.build()
            it.load(imgUrl) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }
        }

        return detailBinding.root
    }

    private fun prepareSharedElementTransition(binding: FragmentDetailBinding): Unit {
        val transition =
            TransitionInflater.from(context)
                .inflateTransition(R.transition.image_shared_element_transition)
        sharedElementEnterTransition = transition
        ViewCompat.setTransitionName(binding.sharedImage,characterName)
        characterName?.let { Log.e("Name", it) }

        binding.sharedImage.transitionName = characterName

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareSharedElementTransition(binding)


    }

}