package uz.gita.a4picture1word.ui.game

class GamePresenter(private val view:GameContract.View) : GameContract.Presenter {

    private val model:GameContract.Model = GameModel()

    init {
        val data = model.getNextQuestion()
        view.describeQuestion(data,model.getCurrentPos())
        view.resizeAnswerButtons(data.answer.length)
        view.setCoinsToView(model.getSavedCoinsCount())
    }

    override fun btnVariantClicked(text: String, pos: Int) {
        view.showValueToAnswer(text, pos)
    }

    override fun btnAnswerClicked(text: String, pos: Int, tag: Int) {
        view.showValueToVariant(text, pos, tag)
        view.setSimpleTextColorToAnswers()
        view.setSimpleBackgroundToAnswers()
    }

    override fun checkUserAnswer(answer: String) {
        if (model.getAnswer().length == answer.length) {
            if (model.getAnswer().equals(answer, false)) {
                view.showWinDialog(answer)
            }
            else {
                view.setWrongBackgroundsToAnswers()
                view.setWrongTxtColorToAnswers()
                view.shakeAnswerButtons()
            }
        }

    }

    override fun btnNextClicked(): Boolean {
        if (model.hasQuestion()) {
            val data = model.getNextQuestion()
            view.resizeAnswerButtons(data.answer.length)
            view.describeQuestion(data,model.getCurrentPos())
            view.setStateToDeleteButton(true)
            view.setWhiteTextColorToAnswerButtons()
            return true
        }
        view.showFinishDialog()
        return false
    }

    override fun savePositionAndCoinsCount(coins: Int) {
        model.savePosition()
        model.saveCoinsCount(coins)
    }

    override fun btnBackClicked() {
        view.finishActivity()
    }

    override fun clearExtraButtonsClicked(coins: Int) {
        if (coins>=80){
            view.clearExtraButtons(model.getAnswer())
            view.subtractCoins(80)
            view.setStateToDeleteButton(false)
        }
        else{
            view.showToast("Oops! You don't have enough money")
        }
    }

    override fun btnHelpClicked(coins: Int) {
        if (coins>=30){
            view.setCorrectLetterToAnswerBtn(model.getAnswer())
            view.subtractCoins(30)
        }
        else {
            view.showToast("Oops! You don't have enough money")
        }
    }

    override fun btnRestartClicked() {
        model.setPosition(0)
        model.savePosition(0)
        view.describeQuestion(model.getNextQuestion(),model.getCurrentPos())
    }

    override fun btnHomeClicked() {
        model.setPosition(1)
        view.finishActivity()
    }


}