package com.mna.libgdx3dengine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import com.github.kostasdrakonakis.annotation.Intent

@Intent
class MainActivity() : AndroidApplication() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val config = AndroidApplicationConfiguration()
        initialize(Engine(), config)

    }

}
