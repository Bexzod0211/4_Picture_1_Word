package uz.gita.a4picture1word.ui.game

import uz.gita.a4picture1word.model.QuestionData
import uz.gita.a4picture1word.repository.AppRepository

class GameModel : GameContract.Model {

    private val repository = AppRepository.getInstance()
    private var list = ArrayList<QuestionData>()
    private var currentPos = getSavedPosition()
    init {
        loadListOfQuestions()
    }

    private fun loadListOfQuestions(){
        list.addAll(repository.getListOfData())
    }

    override fun getNextQuestion(): QuestionData {
        return list[currentPos++]
    }

    override fun getAnswer(): String {
        return list[currentPos-1].answer
    }

    override fun hasQuestion(): Boolean {
        return currentPos < list.size
    }

    override fun savePosition() {
        repository.savePosition(currentPos-1)
    }

    override fun savePosition(pos: Int) {
        repository.savePosition(0)
    }

    override fun getSavedPosition(): Int {
        return repository.getSavedPosition()
    }

    override fun getCurrentPos(): Int {
        return currentPos
    }

    override fun getSavedCoinsCount(): Int {
        return repository.getSavedCoinsCount()
    }

    override fun saveCoinsCount(coins: Int) {
        repository.saveCoinsCount(coins)
    }

    override fun setPosition(pos: Int) {
        currentPos = pos
    }


}