package uz.gita.a4picture1word.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import uz.gita.a4picture1word.R

class DialogWin(context:Context,private val pos:Int) : AlertDialog(context) {
    private lateinit var txtCoinsCount:TextView
    private var answers = ArrayList<TextView>()
    private lateinit var btnNext:AppCompatButton
    private lateinit var listener:(Int)->Unit
    private lateinit var txtComment:TextView
    private val comments = arrayListOf("Correct","Very Good","Great","Awesome","Fantastic","Perfect")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_win)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        loadViews()
        attachClickListeners()
        txtComment.text = comments[pos%6]
    }

    private fun loadViews(){
        txtCoinsCount = findViewById(R.id.txtCountCoinDialog)
        val answerLine:LinearLayout = findViewById(R.id.dialog_answers_line)
        txtComment = findViewById(R.id.txt_comment)

        for (i in 0 until answerLine.childCount){
            answers.add(answerLine.getChildAt(i) as TextView)
        }
        btnNext = findViewById(R.id.btn_next)
    }

    private fun attachClickListeners(){
        btnNext.setOnClickListener {
            txtCoinsCount.text = (txtCoinsCount.text.toString().toInt()+4).toString()
            listener.invoke(txtCoinsCount.text.toString().toInt())
        }
    }


    fun setOnNextButtonClickListener(block:(Int)->Unit){
        listener = block
    }

    fun setCoinsCount(coins:Int){
        txtCoinsCount.text = "$coins"
    }

    fun setAnswersToTextViews(answer:String){
        resize(answer.length)

        for (i in answer.indices){
            answers[i].text = answer[i].toString()
        }
    }

    private fun resize(length:Int){
        for (i in 0 until length){
            answers[i].visibility = View.VISIBLE
        }

        for (i in length until answers.size){
            answers[i].visibility = View.GONE
        }
    }
}