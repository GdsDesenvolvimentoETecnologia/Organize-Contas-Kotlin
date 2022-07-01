package com.gdsdesenvolvimento.organizecontas.ui.view

import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import com.gdsdesenvolvimento.organizecontas.R
import com.heinrichreimersoftware.materialintro.app.IntroActivity
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide

class IntroductionActivity : IntroActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configButtonNavigationSlideIntro()
        addLayoutSlideIntro(R.layout.activity_intro)
        addLayoutSlideIntro(R.layout.activity_intro2)
        addLayoutSlideIntro(R.layout.activity_intro3)
        addLayoutSlideIntro(R.layout.activity_intro4)
    }

    private fun configButtonNavigationSlideIntro(btnBack : Boolean = false , btnNext : Boolean = false) {
        isButtonBackVisible = btnBack
        isButtonNextVisible = btnNext
    }

    private fun addLayoutSlideIntro(
        @LayoutRes layoutSlide: Int,
        @ColorRes backgroundColor: Int = R.color.backgroundIntro
    ) {
        addSlide(
            FragmentSlide.Builder()
                .background(backgroundColor)
                .fragment(layoutSlide)
                .build()
        )
    }
}