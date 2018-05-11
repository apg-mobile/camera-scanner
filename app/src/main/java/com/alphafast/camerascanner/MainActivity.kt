package com.alphafast.camerascanner

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.alphafast.camerascanner.lib.ScannerFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager?.beginTransaction()
                ?.replace(R.id.mainContainer, ScannerFragment())
                ?.commit()
    }
}
