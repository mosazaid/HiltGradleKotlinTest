package com.example.hiltgradlekotlintest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import javax.inject.Inject

// At field injection.
@AndroidEntryPoint
class ExampleActivity : AppCompatActivity() {

    @AuthInterceptorOkHttpClient
    @Inject
    lateinit var okHttpClient: OkHttpClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
    }
}