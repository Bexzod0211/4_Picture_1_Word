package uz.gita.a4picture1word.ui.entry

import uz.gita.a4picture1word.model.QuestionData
import uz.gita.a4picture1word.repository.AppRepository

class EntryModel : EntryContract.Model {
    private val repository = AppRepository.getInstance()

    override fun getQuestionBySavedPos(): QuestionData {
        return repository.getQuestionBySavedPos()
    }

    override fun getSavedPos(): Int {
        return repository.getSavedPosition()
    }

    override fun getSavedCoinsCount(): Int {
        return repository.getSavedCoinsCount()
    }


}