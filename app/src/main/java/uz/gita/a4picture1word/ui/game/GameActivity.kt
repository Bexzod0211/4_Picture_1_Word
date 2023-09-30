package uz.gita.a4picture1word.ui.game

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import uz.gita.a4picture1word.R
import uz.gita.a4picture1word.model.QuestionData
import uz.gita.a4picture1word.ui.dialog.DialogFinish
import uz.gita.a4picture1word.ui.dialog.DialogWin

class GameActivity : AppCompatActivity(), GameContract.View {
    private lateinit var image1: ImageView
    private lateinit var image2: ImageView
    private lateinit var image3: ImageView
    private lateinit var image4: ImageView
    private var variants = ArrayList<TextView>()
    private var answers = ArrayList<TextView>()
    private lateinit var presenter: GameContract.Presenter
    private lateinit var txtQuestionPos: TextView
    private lateinit var imgBack: ImageView
    private lateinit var txtCountCoin: TextView
    private lateinit var musicWin: MediaPlayer
    private lateinit var answerLine: LinearLayout
    private lateinit var animation: Animation
    private lateinit var btnClear: LinearLayout
    private lateinit var btnHelp: LinearLayout
    private var pos = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        loadViews()
        presenter = GamePresenter(this)

        attachClickListeners()

    }

    private fun loadViews() {
        image1 = findViewById(R.id.entryImage1)
        image2 = findViewById(R.id.entryImage2)
        image3 = findViewById(R.id.entryImage3)
        image4 = findViewById(R.id.entryImage4)
        txtQuestionPos = findViewById(R.id.txtPos)
        imgBack = findViewById(R.id.img_back)
        txtCountCoin = findViewById(R.id.txtCountCoin)
        btnClear = findViewById(R.id.btn_clear_extra_letters)
        btnHelp = findViewById(R.id.btn_help)

        val variantLine1: LinearLayout = findViewById(R.id.variant_line1)
        val variantLine2: LinearLayout = findViewById(R.id.variant_line2)

        for (i in 0 until variantLine1.childCount) {
            if (i != 6)
                variants.add(variantLine1.getChildAt(i) as TextView)
        }

        for (i in 0 until variantLine2.childCount) {
            if (i != 6)
                variants.add(variantLine2.getChildAt(i) as TextView)
        }

        answerLine = findViewById(R.id.answers_line)

        for (i in 0 until answerLine.childCount) {
            answers.add(answerLine.getChildAt(i) as TextView)
        }

    }

    override fun describeQuestion(data: QuestionData, pos: Int) {
        image1.setImageResource(data.imageId1)
        image2.setImageResource(data.imageId2)
        image3.setImageResource(data.imageId3)
        image4.setImageResource(data.imageId4)

        for (i in variants.indices) {
            variants[i].text = data.variants[i].toString()
            variants[i].isEnabled = true
        }

        for (i in answers.indices) {
            answers[i].text = ""
            answers[i].isEnabled = false
        }

        txtQuestionPos.text = "$pos"
        this.pos = pos

    }

    override fun setCoinsToView(coins: Int) {
        txtCountCoin.text = "$coins"
    }

    override fun resizeAnswerButtons(length: Int) {
        for (i in 0 until length) {
            answers[i].visibility = View.VISIBLE
        }

        for (i in length until answers.size) {
            answers[i].visibility = View.GONE
        }
    }

    override fun showValueToAnswer(text: String, pos: Int) {
        val index = getFirstEmptyPos()
        if (index != -1) {
            answers[index].text = text
            answers[index].tag = pos
            variants[pos].text = ""
            variants[pos].isEnabled = false
            answers[index].isEnabled = true
        }
    }

    override fun showValueToVariant(text: String, pos: Int, tag: Int) {
        variants[tag].text = text
        variants[tag].isEnabled = true
        answers[pos].text = ""
        answers[pos].isEnabled = false
    }

    override fun showWinDialog(answer: String) {
        /*AlertDialog.Builder(this)
            .setTitle("Correct")
            .setMessage("Answer is $answer")
            .setPositiveButton("Next") { dialogInterface, p1 ->
                if (presenter.btnNextClicked()){
                    dialogInterface.cancel()
                } else {
                    dialogInterface.cancel()
                    finish()
                }
            }
            .setNegativeButton("Home") { dialogInterface, p1 ->
                dialogInterface.cancel()
                finish()
            }
            .setCancelable(false)
            .create()
            .show()*/
        musicWin = MediaPlayer.create(this, R.raw.music_win)
        musicWin.start()
        val dialog = DialogWin(this, pos)
        dialog.setOnNextButtonClickListener {
            if (presenter.btnNextClicked()) {
                txtCountCoin.text = it.toString()
                dialog.cancel()
                musicWin.stop()
            } else {
                dialog.cancel()
                musicWin.stop()
            }
        }
        dialog.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        dialog.setCoinsCount(txtCountCoin.text.toString().toInt())
        dialog.setAnswersToTextViews(answer)
        dialog.show()
    }

    override fun showToast(toast: String) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show()
    }

    override fun finishActivity() {
        finish()
    }

    override fun shakeAnswerButtons() {
        animation = AnimationUtils.loadAnimation(this, R.anim.animaton_shake)
        answerLine.startAnimation(animation)
        val musicWrong = MediaPlayer.create(this, R.raw.music_error)
        musicWrong.start()
    }

    override fun setWrongBackgroundsToAnswers() {
        for (i in answers.indices) {
            answers[i].setBackgroundResource(R.drawable.bg_shape_wrong_asnwer)
        }
    }

    override fun setWrongTxtColorToAnswers() {
        for (i in answers.indices) {
            answers[i].setTextColor(resources.getColor(R.color.color_wrong))
        }
    }

    override fun setSimpleBackgroundToAnswers() {
        for (i in answers.indices) {
            answers[i].setBackgroundResource(R.drawable.bg_shape_answers)
        }
    }

    override fun setSimpleTextColorToAnswers() {
        for (i in answers.indices) {
            answers[i].setTextColor(resources.getColor(R.color.white))
        }
    }

    private fun getUserFullAnswer(): String {
        val sb = StringBuilder()

        for (i in answers.indices) {
            sb.append(answers[i].text.toString())
        }


        return sb.toString()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun attachClickListeners() {
        for (i in variants.indices) {
            variants[i].setOnClickListener {
                if (getFirstEmptyPos() != -1) {
                    presenter.btnVariantClicked(variants[i].text.toString(), i)
                    presenter.checkUserAnswer(getUserFullAnswer())
                }
            }
        }

        for (i in answers.indices) {
            answers[i].setOnClickListener {
                val tagPos = answers[i].tag as Int
                presenter.btnAnswerClicked(answers[i].text.toString(), i, tagPos)
            }
        }

        imgBack.setOnClickListener {
            presenter.btnBackClicked()
        }

        btnClear.setOnClickListener {
            presenter.clearExtraButtonsClicked(txtCountCoin.text.toString().toInt())
        }

        btnHelp.setOnClickListener {
            presenter.btnHelpClicked(txtCountCoin.text.toString().toInt())
        }
    }


    override fun getFirstEmptyPos(): Int {
        for (i in answers.indices) {
            if (answers[i].text.toString().isEmpty() && answers[i].visibility == View.VISIBLE) {
                return i
            }
        }

        return -1
    }

    override fun clearExtraButtons(answer: String) {
        val list = getSuitableAnswerButtonsPos(answer)
        var index = 0

        for (i in variants.indices) {
            if (i == list[index] && index < list.size - 1) {
                index++
            } else if (i != list[index]) {
                variants[i].text = ""
                variants[i].isEnabled = false
            }
        }
    }

    override fun subtractCoins(coins: Int) {
        var currentCoins = txtCountCoin.text.toString().toInt()

        currentCoins -= coins
        txtCountCoin.text = "$currentCoins"
    }

    override fun setStateToDeleteButton(state: Boolean) {
        btnClear.isEnabled = state
    }

    override fun setCorrectLetterToAnswerBtn(answer: String) {
        var answerIndex = getFirstEmptyOrWrongLetterPos(answer)

        var variantIndex: Int
        if (answerIndex != -1) {
            var letter = answers[answerIndex].text.toString()
            variantIndex = getSuitableVariantLetterPos(answer[answerIndex].toString())
            if (!letter.equals("")) {
                presenter.btnAnswerClicked(letter, answerIndex, answers[answerIndex].tag as Int)
                presenter.btnVariantClicked(variants[variantIndex].text.toString(), variantIndex)

            } else if (letter.isEmpty()) {
                presenter.btnVariantClicked(variants[variantIndex].text.toString(), variantIndex)
            }
            answers[answerIndex].setTextColor(resources.getColor(R.color.color_correct))
            answers[answerIndex].isEnabled = false
            presenter.checkUserAnswer(getUserFullAnswer())
        }
    }

    override fun setWhiteTextColorToAnswerButtons() {
        for (i in answers.indices) {
            answers[i].setTextColor(resources.getColor(R.color.white))
        }
    }

    override fun showFinishDialog() {
        val dialog = DialogFinish(this, txtCountCoin.text.toString().toInt())
        dialog.create()
        dialog.setOnRestartButtonClickListener {
            dialog.cancel()
            presenter.btnRestartClicked()

        }
        dialog.setOnHomeButtonListener {
            dialog.cancel()
            presenter.btnHomeClicked()
        }
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    private fun getSuitableVariantLetterPos(letter: String): Int {
        for (i in variants.indices) {
            if (variants[i].text.toString().equals(letter)) {
                return i
            }
        }
        return -1
    }

    private fun getFirstEmptyOrWrongLetterPos(answer: String): Int {
        var letter: String
        for (i in answers.indices) {
            letter = answers[i].text.toString()
            if (i < answer.length) {
                if ((!letter.equals(answer[i].toString()) || letter.equals("")) && answers[i].visibility == View.VISIBLE) {
                    return i
                }
            }
        }

        return -1
    }

    private fun getSuitableAnswerButtonsPos(answer: String): ArrayList<Int> {
        val arr = arrayListOf<Int>()
        var index: Int
        var st: StringBuilder = StringBuilder(answer)

        for (i in variants.indices) {
            var letter = variants[i].text.toString()
            if (st.toString().contains(letter)) {
                arr.add(i)
                index = st.indexOf(letter)
                st.deleteCharAt(index)
            }
        }

        return arr
    }

    override fun onPause() {
        super.onPause()
        presenter.savePositionAndCoinsCount(txtCountCoin.text.toString().toInt())
    }
}