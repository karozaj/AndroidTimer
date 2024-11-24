package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment1.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment1 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    val pattern:String="[012345][0123456789][:][012345][0123456789]"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_1, container, false)

    }


    override fun onStart() {
        super.onStart()
        requireView().findViewById<Button>(R.id.start_timer_button)?.isEnabled = false
        val inputString=requireView().findViewById<EditText>(R.id.edit_time).text.toString()
        if(inputString!="") {
            if (inputString.matches(Regex(pattern)) && inputString.matches(Regex(pattern))
                    .toString() != "00:00"
            ) {
                requireView().findViewById<Button>(R.id.start_timer_button).isEnabled = true
            }
        }


        getView()?.findViewById<Button>(R.id.start_timer_button)?.setOnClickListener{
            val timeString=getTimeString()
            val timeInfo=timeString.split(":")
            val minutes:Long=timeInfo[0].toLong()
            val seconds:Long=timeInfo[1].toLong()
            MyTimer.totalTime=seconds*1000+minutes*60*1000
            MyTimer.stop()
            Toast.makeText(context,"Time set",Toast.LENGTH_SHORT).show()
            //(requireActivity() as MainActivity).changeToFragment2(getTimeString())
        }

//        edit_time=getView()?.findViewById<EditText>(R.id.edit_time)
        getView()?.findViewById<EditText>(R.id.edit_time)?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                Toast.makeText(context,"beforeTextChanged",Toast.LENGTH_SHORT).show()
            }

            override fun afterTextChanged(s: Editable?) {
//                Toast.makeText(context,"afterTextChanged",Toast.LENGTH_SHORT).show()
                if(s!=null)
                {
                    if(s.length>5)
                    {
                        getView()?.findViewById<EditText>(R.id.edit_time)?.setText(
                            getView()?.findViewById<EditText>(R.id.edit_time)?.text?.dropLast(1)
                        )
                    }


                    if(s.matches(Regex(pattern)) && s.toString()!="00:00")
                    {
                        getView()?.findViewById<Button>(R.id.start_timer_button)?.isEnabled=true
                    }
                    else
                    {
                        getView()?.findViewById<Button>(R.id.start_timer_button)?.isEnabled=false
                    }
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                Toast.makeText(view?.context,"onTextChanged",Toast.LENGTH_SHORT).show()
            }

        })
    }


    fun getTimeString():String
    {
        return(getView()?.findViewById<EditText>(R.id.edit_time)?.text.toString() )
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment1().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}