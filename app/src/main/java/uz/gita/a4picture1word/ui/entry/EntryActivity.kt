package uz.gita.a4picture1word.ui.entry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import uz.gita.a4picture1word.R
import uz.gita.a4picture1word.model.QuestionData
import uz.gita.a4picture1word.ui.game.GameActivity

class EntryActivity : AppCompatActivity(),EntryContract.View {
    private lateinit var btnPlay:AppCompatButton
    private lateinit var presenter: EntryContract.Presenter
    private val images = arrayListOf<ImageView>()
    private lateinit var txtQuestionPos:TextView
    private lateinit var txtCoinsCount:TextView
    private lateinit var btnAbout:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)

        loadViews()

        attachClickListeners()
    }


    private fun loadViews(){
        btnPlay = findViewById(R.id.btn_play)

        images.add(findViewById(R.id.entryImage1))
        images.add(findViewById(R.id.entryImage2))
        images.add(findViewById(R.id.entryImage3))
        images.add(findViewById(R.id.entryImage4))
        txtCoinsCount = findViewById(R.id.txtCountCoinEntry)

        txtQuestionPos = findViewById(R.id.txtPosEntry)
        btnAbout = findViewById(R.id.btn_about)
    }

    private fun attachClickListeners(){
        btnPlay.setOnClickListener {
            presenter.bntPlayClicked()
        }
        btnAbout.setOnClickListener {
            presenter.btnInfoClicked()
        }
    }
    override fun openGameActivity() {
        val intent = Intent(this,GameActivity::class.java)
        startActivity(intent)
    }

    override fun loadSavedQuestion(data: QuestionData, pos: Int) {
        images[0].setImageResource(data.imageId1)
        images[1].setImageResource(data.imageId2)
        images[2].setImageResource(data.imageId3)
        images[3].setImageResource(data.imageId4)

        txtQuestionPos.text = "${pos+1}"
    }

    override fun setCoins(coins: Int) {
        txtCoinsCount.text = "$coins"
    }

    override fun showInfoDialog() {
        AlertDialog.Builder(this)
            .setTitle("About")
            .setMessage(resources.getString(R.string.about))
            .setPositiveButton("Ok"){d,i->
                d.cancel()
            }
            .create()
            .show()
    }

    override fun onResume() {
        super.onResume()
        presenter = EntryPresenter(this)
    }
}