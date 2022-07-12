package com.gdsdesenvolvimento.organizecontas.ui.view

import android.os.Bundle
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import com.gdsdesenvolvimento.organizecontas.R
import com.gdsdesenvolvimento.organizecontas.data.dataSource.instances.FBInstance
import com.gdsdesenvolvimento.organizecontas.data.di.DI
import com.gdsdesenvolvimento.organizecontas.ui.adapter.ConfigCreditCardAdapter
import com.gdsdesenvolvimento.organizecontas.utils.extensions.nextScreen
import com.heinrichreimersoftware.materialintro.app.IntroActivity
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide

class IntroductionActivity : IntroActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sliderIntro()
    }

    override fun onStart() {
        super.onStart()
        verifyUserConnected()
    }

    private fun verifyUserConnected() {
        if (DI.isUserLogged()){
            nextScreen(ConfigCreditCardActivity())
        }
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

    fun login(view: View) = nextScreen(LoginActivity())
    fun register(view: View) = nextScreen(RegisterActivity())
}