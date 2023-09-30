package uz.gita.a4picture1word.ui.game

import uz.gita.a4picture1word.model.QuestionData

interface GameContract {
    interface Model {
        fun getNextQuestion():QuestionData
        fun getAnswer():String
        fun hasQuestion():Boolean
        fun savePosition()
        fun getSavedPosition():Int
        fun getCurrentPos():Int
        fun getSavedCoinsCount():Int
        fun saveCoinsCount(coins:Int)
        fun savePosition(pos:Int)
        fun setPosition(pos:Int)
    }

    interface View {
        fun describeQuestion(data:QuestionData,pos: Int)
        fun setCoinsToView(coins: Int)
        fun resizeAnswerButtons(length:Int)
        fun showValueToAnswer(text: String,pos: Int)
        fun showValueToVariant(text: String,pos: Int,tag: Int)
        fun showWinDialog(answer: String)
        fun showToast(toast:String)
        fun finishActivity()
        fun shakeAnswerButtons()
        fun setWrongBackgroundsToAnswers()
        fun setWrongTxtColorToAnswers()
        fun setSimpleBackgroundToAnswers()
        fun setSimpleTextColorToAnswers()
        fun getFirstEmptyPos():Int
        fun clearExtraButtons(answer:String)
        fun subtractCoins(coins:Int)
        fun setStateToDeleteButton(state:Boolean)
        fun setCorrectLetterToAnswerBtn(answer: String)
        fun setWhiteTextColorToAnswerButtons()
        fun showFinishDialog()
    }

    interface Presenter{
        fun btnVariantClicked(text:String,pos:Int)
        fun btnAnswerClicked(text: String,pos: Int,tag:Int)
        fun checkUserAnswer(answer: String)
        fun btnNextClicked():Boolean
        fun savePositionAndCoinsCount(coins:Int)
        fun btnBackClicked()
        fun clearExtraButtonsClicked(coins:Int)
        fun btnHelpClicked(coins:Int)
        fun btnRestartClicked()
        fun btnHomeClicked()
    }
}