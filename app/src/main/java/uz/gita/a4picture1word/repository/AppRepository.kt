package uz.gita.a4picture1word.repository

import uz.gita.a4picture1word.R
import uz.gita.a4picture1word.db.MySharedPreferences
import uz.gita.a4picture1word.model.QuestionData

class AppRepository {
    private var list = ArrayList<QuestionData>()
    private val pref = MySharedPreferences.getInstance()

    init {
        loadQuestions()
    }

    companion object {
        private lateinit var instance:AppRepository
        fun init(){
            if (!::instance.isInitialized){
                instance = AppRepository()
            }
        }
        fun getInstance():AppRepository{
            return instance
        }
    }


    private fun loadQuestions(){
        list.add(QuestionData(R.drawable.mouse1,R.drawable.mouse2,R.drawable.mouse3,R.drawable.mouse4,"DEKRMSHPSUIO","MOUSE"))
        list.add(QuestionData(R.drawable.classroom_1_1_pencil,R.drawable.classroom_1_2_pencil,R.drawable.classroom_1_3_pencil,R.drawable.classroom_1_4_pencil,"NLKPQSCDINEM","PENCIL"))
        list.add(QuestionData(R.drawable.classroom_3_1_chair,R.drawable.classroom_3_2_chair,R.drawable.classroom_3_3_chair,R.drawable.classroom_3_4_chair,"PADIFDGCHRHZ","CHAIR"))
        list.add(QuestionData(R.drawable.color_4_1_blue,R.drawable.color_4_2_blue,R.drawable.color_4_3_blue,R.drawable.color_4_4_blue,"ALFCEHBEAUTPE","BLUE"))
        list.add(QuestionData(R.drawable.classroom_5_1_desk,R.drawable.classroom_5_2_desk,R.drawable.classroom_5_3_desk,R.drawable.classroom_5_4_desk,"QKTSFNDDUEPO","DESK"))
        list.add(QuestionData(R.drawable.classroom_4_1_board,R.drawable.classroom_4_2_board,R.drawable.classroom_4_3_board,R.drawable.classroom_4_4_board,"FQDOVRTNBAYK","BOARD"))
        list.add(QuestionData(R.drawable.color_2_1_white,R.drawable.color_2_2_white,R.drawable.color_2_3_white,R.drawable.color_2_4_white,"ALICGHEKAUWT","WHITE"))
        list.add(QuestionData(R.drawable.classroom_2_1_bag,R.drawable.classroom_2_2_bag,R.drawable.classroom_2_3_bag,R.drawable.classroom_2_4_bag,"EAGTNFEALHBT","BAG"))
        list.add(QuestionData(R.drawable.classroom_10_1_notebook,R.drawable.classroom_10_2_notebook,R.drawable.classroom_10_3_notebook,R.drawable.classroom_10_4_notebook,"GOBHOELNOTSK","NOTEBOOK"))
        list.add(QuestionData(R.drawable.sleep1,R.drawable.sleep2,R.drawable.sleep3,R.drawable.sleep4,"DELSREPWPOPD","SLEEP"))
        list.add(QuestionData(R.drawable.classroom_6_1_book,R.drawable.classroom_6_2_book,R.drawable.classroom_6_3_book,R.drawable.classroom_6_4_book,"QWONKTYOBIOP","BOOK"))
        list.add(QuestionData(R.drawable.color_1_1_black,R.drawable.color_1_2_black,R.drawable.color_1_3_black,R.drawable.color_1_4_black,"ALFCGHBKAUTP","BLACK"))
        list.add(QuestionData(R.drawable.classroom_7_1_eraser,R.drawable.classroom_7_2_eraser,R.drawable.classroom_7_3_eraser,R.drawable.classroom_7_4_eraser,"NRTALRAPEWES","ERASER"))
        list.add(QuestionData(R.drawable.cold1,R.drawable.cold2,R.drawable.cold3,R.drawable.cold4,"WODQCVPHDELP","COLD"))
        list.add(QuestionData(R.drawable.classroom_9_1_scissor,R.drawable.classroom_9_2_scissor,R.drawable.classroom_9_3_scissor,R.drawable.classroom_9_4_scissor,"BRCOSIUSJLSP","SCISSOR"))
        list.add(QuestionData(R.drawable.color_3_1_green,R.drawable.color_3_2_green,R.drawable.color_3_3_green,R.drawable.color_3_4_green,"NLRCGEBKAUEP","GREEN"))
        list.add(QuestionData(R.drawable.classroom_8_1_glue,R.drawable.classroom_8_2_glue,R.drawable.classroom_8_3_glue,R.drawable.classroom_8_4_glue,"MIEMOUPGXLAG","GLUE"))
    }


    fun getListOfData():List<QuestionData> {
        return list
    }

    fun savePosition(pos:Int){
        pref.savePosition(pos)
    }

    fun getSavedPosition():Int {
        return pref.getPosition()
    }

    fun getQuestionBySavedPos():QuestionData {
        return list[getSavedPosition()]
    }

    fun getSavedCoinsCount():Int {
        return pref.getCoinsCount()
    }

    fun saveCoinsCount(coins:Int){
        pref.saveCoinsCount(coins)
    }

}