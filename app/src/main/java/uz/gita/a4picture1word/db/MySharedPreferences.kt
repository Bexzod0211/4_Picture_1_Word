package uz.gita.a4picture1word.db

import android.content.Context
import android.content.SharedPreferences

class MySharedPreferences private constructor(context:Context){

    private var pref:SharedPreferences
    private val POSITION = "POSITION"
    private val COINS_COUNT = "COINS_COUNT"
    init {
        pref = context.getSharedPreferences("Pictures",Context.MODE_PRIVATE)
    }

    companion object {
        private lateinit var instance:MySharedPreferences

        fun init(context: Context){
            if (!::instance.isInitialized){
                instance = MySharedPreferences(context)
            }
        }

        fun getInstance():MySharedPreferences {
            return instance
        }

    }

    fun savePosition(pos:Int){
        pref.edit().putInt(POSITION,pos).apply()
    }

    fun getPosition():Int {
        return pref.getInt(POSITION,0)
    }

    fun saveCoinsCount(coins:Int){
        pref.edit().putInt(COINS_COUNT,coins).apply()
    }

    fun getCoinsCount():Int {
        return pref.getInt(COINS_COUNT,400)
    }

}