package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlin.math.floor

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment2 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        return inflater.inflate(R.layout.fragment_2, container, false)
    }

    override fun onStart() {
        super.onStart()

        MyTimer.onTickFun={millisUntilFinished->updateTimeDisplay(millisUntilFinished)}
        MyTimer.onFinishFun={Toast.makeText(context,"Time has elapsed",Toast.LENGTH_SHORT).show() }


        requireActivity().findViewById<Button>(R.id.start_button).setOnClickListener{
            if(MyTimer.isRunning==false)
            {
                MyTimer.start()
            }
        }

        requireActivity().findViewById<Button>(R.id.pause_button).setOnClickListener{
            MyTimer.pause()
        }

        requireActivity().findViewById<Button>(R.id.stop_button).setOnClickListener{
            MyTimer.stop()
            updateTimeDisplay(MyTimer.currentTime)
        }

        requireActivity().findViewById<Button>(R.id.plus_10_minute_button).setOnClickListener{
            MyTimer.updateCurrentTime(10*60*1000)
            updateTimeDisplay(MyTimer.currentTime)
        }

        requireActivity().findViewById<Button>(R.id.plus_1_minute_button).setOnClickListener{
            MyTimer.updateCurrentTime(1*60*1000)
            updateTimeDisplay(MyTimer.currentTime)
        }

        requireActivity().findViewById<Button>(R.id.plus_10_second_button).setOnClickListener{
            MyTimer.updateCurrentTime(10*1000)
            updateTimeDisplay(MyTimer.currentTime)
        }

        requireActivity().findViewById<Button>(R.id.plus_1_second_button).setOnClickListener{
            MyTimer.updateCurrentTime(1*1000)
            updateTimeDisplay(MyTimer.currentTime)
        }

        requireActivity().findViewById<Button>(R.id.minus_10_minute_button).setOnClickListener{
            MyTimer.updateCurrentTime(-10*60*1000)
            updateTimeDisplay(MyTimer.currentTime)
        }

        requireActivity().findViewById<Button>(R.id.minus_1_minute_button).setOnClickListener{
            MyTimer.updateCurrentTime(-1*60*1000)
            updateTimeDisplay(MyTimer.currentTime)
        }

        requireActivity().findViewById<Button>(R.id.minus_10_second_button).setOnClickListener{
            MyTimer.updateCurrentTime(-10*1000)
            updateTimeDisplay(MyTimer.currentTime)
        }

        requireActivity().findViewById<Button>(R.id.minus_1_second_button).setOnClickListener{
            MyTimer.updateCurrentTime(-1*1000)
            updateTimeDisplay(MyTimer.currentTime)
        }

        updateTimeDisplay(MyTimer.currentTime)
    }

    fun updateTimeDisplay(millisUntilFinished:Long) {
        val minutes= floor(((millisUntilFinished.toDouble())/1000)/60)
        val seconds= floor((millisUntilFinished-minutes*60*1000)/1000)
        var minutesString=minutes.toInt().toString()
        if(minutesString.length<2)
        {
            minutesString="0"+minutesString
        }
        var secondsString=seconds.toInt().toString()
        if(secondsString.length<2)
        {
            secondsString="0"+secondsString
        }
        requireActivity().findViewById<TextView>(R.id.time_counter).text=
            (minutesString+":"+secondsString)
    }

    override fun onDestroy() {
        super.onDestroy()
        MyTimer.onTickFun=null
        MyTimer.onFinishFun=null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}