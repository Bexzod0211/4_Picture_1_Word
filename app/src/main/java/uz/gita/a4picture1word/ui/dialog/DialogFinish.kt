package uz.gita.a4picture1word.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import uz.gita.a4picture1word.R

class DialogFinish(context:Context,private val coins:Int) : AlertDialog(context) {
    private lateinit var txtTotalCoins:TextView
    private lateinit var btnRestart:TextView
    private lateinit var btnHome:TextView
    private lateinit var restartButtonListener:()->Unit
    private lateinit var homeButtonListener:()->Unit
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_finish)
        loadViews()
        txtTotalCoins.text = "You have earned $coins coins"
        attachClickListeners()
    }

    private fun loadViews(){
        txtTotalCoins = findViewById(R.id.txt_total_coins_count)
        btnRestart = findViewById(R.id.btn_restart)
        btnHome = findViewById(R.id.btn_home)

    }

    private fun attachClickListeners(){
        btnRestart.setOnClickListener {
            restartButtonListener.invoke()
        }

        btnHome.setOnClickListener {
            homeButtonListener.invoke()
        }
    }

    fun setOnRestartButtonClickListener(block:()->Unit){
        restartButtonListener = block
    }


    fun setOnHomeButtonListener(block: () -> Unit){
        homeButtonListener = block
    }

}