package com.example.mycalculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

private const val TAG = "CalculatorFragment"
class CalculatorFragment : Fragment(), View.OnClickListener {

    private lateinit var resultTextView: TextView
    private lateinit var deleteImageButton: ImageButton
    private lateinit var equalsButton: Button
    private lateinit var plusButton: Button
    private lateinit var subButton: Button
    private lateinit var multiButton: Button
    private lateinit var divButton: Button
    private lateinit var zeroButton: Button
    private lateinit var oneButton: Button
    private lateinit var twoButton: Button
    private lateinit var threeButton: Button
    private lateinit var fourButton: Button
    private lateinit var fiveButton: Button
    private lateinit var sixButton: Button
    private lateinit var sevenButton: Button
    private lateinit var eightButton: Button
    private lateinit var nineButton: Button

    private val calculatorViewModel by lazy {
        ViewModelProvider(this).get(CalculatorViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.calculator_fragment, container, false)

        if (view != null) {
            initializeViews(view)
            setClickListeners()
            setResultIfAny()
        }
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
            calculatorViewModel.result = Integer.parseInt(resultTextView.text.toString())
    }

    private fun initializeViews(view: View){

        resultTextView = view.findViewById(R.id.resultTextView)
        deleteImageButton = view.findViewById(R.id.deleteImageButton)
        equalsButton = view.findViewById(R.id.equalsButton)
        plusButton = view.findViewById(R.id.plusButton)
        subButton = view.findViewById(R.id.subButton)
        multiButton = view.findViewById(R.id.multiButton)
        divButton = view.findViewById(R.id.divButton)
        zeroButton = view.findViewById(R.id.zeroButton)
        oneButton = view.findViewById(R.id.oneButton)
        twoButton = view.findViewById(R.id.twoButton)
        threeButton = view.findViewById(R.id.threeButton)
        fourButton = view.findViewById(R.id.fourButton)
        fiveButton = view.findViewById(R.id.fiveButton)
        sixButton = view.findViewById(R.id.sixButton)
        sevenButton = view.findViewById(R.id.sevenButton)
        eightButton = view.findViewById(R.id.eightButton)
        nineButton = view.findViewById(R.id.nineButton)
    }

    private fun setClickListeners(){
        resultTextView.setOnClickListener(this)
        deleteImageButton.setOnClickListener(this)
        equalsButton.setOnClickListener(this)
        plusButton.setOnClickListener(this)
        subButton.setOnClickListener(this)
        multiButton.setOnClickListener(this)
        divButton.setOnClickListener(this)
        zeroButton.setOnClickListener(this)
        oneButton.setOnClickListener(this)
        twoButton.setOnClickListener(this)
        threeButton.setOnClickListener(this)
        fourButton.setOnClickListener(this)
        fiveButton.setOnClickListener(this)
        sixButton.setOnClickListener(this)
        sevenButton.setOnClickListener(this)
        eightButton.setOnClickListener(this)
        nineButton.setOnClickListener(this)
    }

    private fun setResultIfAny(){
        if (calculatorViewModel.result != null){
            resultTextView.text = calculatorViewModel.result.toString()
        }
    }

    override fun onClick(v: View?) {
        when(v){
            deleteImageButton -> clear()
            equalsButton -> getResult()
            plusButton -> operations('+')
            subButton -> operations('-')
            multiButton -> operations('*')
            divButton -> operations('/')
            zeroButton -> number(0)
            oneButton -> number(1)
            twoButton -> number(2)
            threeButton -> number(3)
            fourButton -> number(4)
            fiveButton -> number(5)
            sixButton -> number(6)
            sevenButton -> number(7)
            eightButton -> number(8)
            nineButton -> number(9)
        }
    }

    private fun clear(){
        resultTextView.setText(R.string.result)
        calculatorViewModel.num1 = null
        calculatorViewModel.num2 = null
        calculatorViewModel.result = null
        calculatorViewModel.operation = null
    }

    private fun getResult(){
        if (calculatorViewModel.num1 != null && calculatorViewModel.num2 != null && calculatorViewModel.operation != null){
            var result = when(calculatorViewModel.operation){
                '+' -> calculatorViewModel.num1!! + calculatorViewModel.num2!!
                '-' -> calculatorViewModel.num1!! - calculatorViewModel.num2!!
                '*' -> calculatorViewModel.num1!! * calculatorViewModel.num2!!
                '/' -> {
                    if (calculatorViewModel.num2 == 0){
                        0
                    }else{
                        calculatorViewModel.num1!! / calculatorViewModel.num2!!
                    }
                }
                else -> {return}
            }
            if (result == 0){
                resultTextView.text = "Error."
            }else {
                calculatorViewModel.result = result
                resultTextView.text = result.toString()
            }
        }
    }

    private fun operations(oprator: Char){
        if (calculatorViewModel.num1 != null){
            calculatorViewModel.operation = oprator
        }
    }

    private fun number(num: Int){
        if (calculatorViewModel.num1 == null && calculatorViewModel.operation == null){
            calculatorViewModel.num1 = num
            resultTextView.text = calculatorViewModel.num1.toString()
        }else if (calculatorViewModel.num1 != null && calculatorViewModel.operation == null){
            calculatorViewModel.num1 = "${calculatorViewModel.num1!!}$num".toInt()
            resultTextView.text = calculatorViewModel.num1.toString()
        }else if (calculatorViewModel.num1 != null && calculatorViewModel.operation != null){
            if (calculatorViewModel.num2 == null) {
                calculatorViewModel.num2 = num
            }else {
                calculatorViewModel.num2 = "${calculatorViewModel.num2!!}$num".toInt()
            }
            resultTextView.text = calculatorViewModel.num2.toString()
        }else{
            return
        }
    }

}