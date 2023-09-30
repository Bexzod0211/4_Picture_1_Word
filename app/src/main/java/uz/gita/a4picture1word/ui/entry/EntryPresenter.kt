package uz.gita.a4picture1word.ui.entry

class EntryPresenter(private val view:EntryContract.View) : EntryContract.Presenter {
    private val model:EntryContract.Model=  EntryModel()

    init {
        view.loadSavedQuestion(model.getQuestionBySavedPos(),model.getSavedPos())
        view.setCoins(model.getSavedCoinsCount())
    }

    override fun bntPlayClicked() {
        view.openGameActivity()
    }

    override fun btnInfoClicked() {
        view.showInfoDialog()
    }
}