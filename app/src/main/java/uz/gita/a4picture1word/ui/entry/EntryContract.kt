package uz.gita.a4picture1word.ui.entry

import uz.gita.a4picture1word.model.QuestionData

interface EntryContract {
    interface Model {
        fun getQuestionBySavedPos():QuestionData
        fun getSavedPos():Int
        fun getSavedCoinsCount():Int
    }

    interface View {
        fun openGameActivity()
        fun loadSavedQuestion(data: QuestionData,pos:Int)
        fun setCoins(coins:Int)
        fun showInfoDialog()
    }


    interface Presenter {
        fun bntPlayClicked()
        fun btnInfoClicked()
    }
}