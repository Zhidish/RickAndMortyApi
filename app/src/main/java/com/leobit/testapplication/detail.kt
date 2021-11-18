package com.leobit.testapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.leobit.testapplication.adapter.CallsToApi
import com.leobit.testapplication.databinding.FragmentDetailBinding
import com.leobit.testapplication.network.Character
import kotlinx.coroutines.runBlocking

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [detail.newInstance] factory method to
 * create an instance of this fragment.
 */


class detail : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var id: Int? = null
    private var characterText : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            id = it.getInt("id")
            characterText = it.getString("characterText")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val character = runBlocking {
            id?.let {
                getProperties(it)
            }
        }


        var detailBinding: FragmentDetailBinding = FragmentDetailBinding.inflate(inflater)
        detailBinding.idCharacter.text =
//                character.apply {
//                    if (character != null) {
//                        "Character name : ${character.name}\n Character status : ${character}"
//                    }
//                }.toString()
            with(character) {
                "Character name : ${this?.name}\n Character status : ${this?.status}\n" +
                        "Character origin : ${this?.origin}\n Character gender : ${this?.gender}"

            }.toString()


        return detailBinding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment detail.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            detail().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }


        @JvmStatic
        suspend fun getProperties(id: Int): Character {
            val character = CallsToApi.RickAndMortyService.getCharacter(id)

            return character

        }
    }
}