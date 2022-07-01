package com.gdsdesenvolvimento.organizecontas.ui.view

import android.os.Bundle
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import com.gdsdesenvolvimento.organizecontas.R
import com.heinrichreimersoftware.materialintro.app.IntroActivity
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide

class IntroductionActivity : IntroActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sliderIntro()
    }

    private fun sliderIntro() {
        configButtonNavigationSlideIntro()
        addLayoutSlideIntro(R.layout.intro_slide, isBackSlide = false)
        addLayoutSlideIntro(R.layout.intro2_slide)
        addLayoutSlideIntro(R.layout.intro3_slide)
        addLayoutSlideIntro(R.layout.intro4_slide)
        addLayoutSlideIntro(R.layout.intro_cadastro, isNextSlide = false)
    }

    private fun configButtonNavigationSlideIntro(btnBack : Boolean = false , btnNext : Boolean = false) {
        isButtonBackVisible = btnBack
        isButtonNextVisible = btnNext
    }

    private fun addLayoutSlideIntro(
        @LayoutRes layoutSlide: Int,
        @ColorRes backgroundColor: Int = R.color.backgroundIntro,
        @NonNull isBackSlide : Boolean = true,
        @NonNull isNextSlide : Boolean = true,
    ) {
        addSlide(
            FragmentSlide.Builder()
                .background(backgroundColor)
                .fragment(layoutSlide)
                .canGoBackward(isBackSlide)
                .canGoForward(isNextSlide)
                .build()
        )
    }
}