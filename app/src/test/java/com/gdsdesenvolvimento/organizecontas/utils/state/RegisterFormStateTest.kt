package com.gdsdesenvolvimento.organizecontas.utils.state

import org.junit.Assert.assertEquals
import org.junit.Test

class RegisterFormStateTest {

    @Test
    fun `Quando o estado passado for ErrorQtdCharactersInvalid o o estado deve ser mantido`() {
        val a = RegisterFormState.ErrorQtdCharactersInvalid("msg")
        val s = a is RegisterFormState.ErrorQtdCharactersInvalid
        assertEquals(true, s)
    }

    @Test
    fun `Quando o estado for ErrorQtdCharactersInvalid a mensagem nao pode ser alterada`() {
        val state = RegisterFormState.ErrorQtdCharactersInvalid("ola mundo")
        val msg = state.msg
        assertEquals("ola mundo", msg)
    }

    @Test
    fun `quando o erro for emailNotValid o estado nao pode ser alterado`() {
        val state = RegisterFormState.ErrorEmailNotValid("email invalido")
        val a = state is RegisterFormState.ErrorEmailNotValid
        assertEquals(true, a)
    }


}