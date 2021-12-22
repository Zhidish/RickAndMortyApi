package com.leobit.testapplication.fragments


import android.graphics.drawable.Drawable
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
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
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
    lateinit var parentFragment_ : Fragment
    lateinit private var binding: FragmentDetailBinding
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
        binding = detailBinding

        detailBinding.idCharacter.text = characterText

        detailBinding.sharedImage.let {
            val imgUrl = characterImage?.toUri()?.buildUpon()?.scheme("https")?.build()
            it.load(imgUrl) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }
            }
       /* prepareSharedElementTransition(binding)
            Glide.with(this)
                .load(characterImage)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        parentFragment!!.startPostponedEnterTransition()
                      return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                       parentFragment_.startPostponedEnterTransition()
                        return false
                    }


                }

                )
                .into(detailBinding.sharedImage)
*/


        return detailBinding.root
    }

    private fun prepareSharedElementTransition(binding: FragmentDetailBinding): Unit {
        val transition =
            TransitionInflater.from(context)
                .inflateTransition(R.transition.image_shared_element_transition)
        sharedElementEnterTransition = transition
        ViewCompat.setTransitionName(binding.sharedImage, characterName)
        characterName?.let { Log.e("Name", it) }

        binding.sharedImage.transitionName = characterName

        startPostponedEnterTransition()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareSharedElementTransition(binding)


    }

}