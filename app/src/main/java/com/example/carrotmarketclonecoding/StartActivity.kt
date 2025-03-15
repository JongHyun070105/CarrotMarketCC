package com.example.carrotmarketclonecoding

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.StyleSpan
import android.text.TextPaint
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        // 버튼 정의 및 클릭 이벤트
        val goMainBtn = findViewById<Button>(R.id.StartButton)
        goMainBtn.setOnClickListener {
            startActivity(Intent(this, GPSActivity::class.java))
        }

        // TextView에서 "로그인" 텍스트만 클릭 가능하도록 설정 및 전체 Bold 적용
        val textView = findViewById<TextView>(R.id.textView4)
        val fullText = textView.text.toString()
        val spannableString = SpannableString(fullText)

        // 전체 텍스트 Bold 처리
        spannableString.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            spannableString.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val loginText = "로그인"
        val startIndex = fullText.indexOf(loginText)
        if (startIndex != -1) {
            val endIndex = startIndex + loginText.length

            // ClickableSpan 정의: 클릭 시 LoginActivity로 전환, 밑줄 제거, 텍스트 색상 지정 및 배경 제거
            val clickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    startActivity(Intent(this@StartActivity, LoginActivity::class.java))
                }
                override fun updateDrawState(ds: TextPaint) {
                    ds.isUnderlineText = false          // 밑줄 제거
                    ds.color = Color.parseColor("#FF7F00") // 텍스트 색상 지정
                    ds.bgColor = Color.TRANSPARENT         // 클릭 시 배경 제거
                }
            }
            spannableString.setSpan(
                clickableSpan,
                startIndex,
                endIndex,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance()
    }
}