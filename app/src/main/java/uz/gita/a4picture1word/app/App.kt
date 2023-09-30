package uz.gita.a4picture1word.app

import android.app.Application
import uz.gita.a4picture1word.db.MySharedPreferences
import uz.gita.a4picture1word.repository.AppRepository

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        MySharedPreferences.init(this)
        AppRepository.init()
    }

}