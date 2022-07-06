package com.gdsdesenvolvimento.organizecontas.utils.helper

import org.junit.Assert.*
import org.junit.Test

class FormValidationTest{
    @Test
    fun `deve retornar true caso o nome de usuario tenha o @ e o pontocom`(){
        val email = "asdfghj@g.com"
        val userNameValid = FormValidation.isUserNameValid(email)
        assertTrue(userNameValid)
    }
    @Test
    fun `deve retornar false caso o nome de usuario nao tenha o @`(){
        val email = "asdfghjg.com"
        val userNameValid = FormValidation.isUserNameValid(email)
        assertFalse(userNameValid)
    }
    @Test
    fun `deve retornar false caso o nome de usuario nao tenha o pontocom`(){
        val email = "asdfghjg@gggg"
        val userNameValid = FormValidation.isUserNameValid(email)
        assertFalse(userNameValid)
    }
    @Test
    fun `deve retornar true caso o password tenha mais de 7 caracteres`(){
        val password = "13246578"
        val userNameValid = FormValidation.isPasswordValid(password)
        assertTrue(userNameValid)
    }
    @Test
    fun `deve retornar true caso o password tenha  7 caracteres`(){
        val password = "1324657"
        val userNameValid = FormValidation.isPasswordValid(password)
        assertTrue(userNameValid)
    }
    @Test
    fun `deve retornar false quando o password tiver 6 caracteres`(){
        val password = "123456"
        val userNameValid = FormValidation.isPasswordValid(password)
        assertFalse(userNameValid)
    }

    @Test
    fun `deve retornar false quando o password tiver menos de 7 caracteres`(){
        val password = "12345"
        val userNameValid = FormValidation.isPasswordValid(password)
        assertFalse(userNameValid)
    }
    @Test
    fun `deve retornar false quando o password nao tiver caracteres`(){
        val password = ""
        val userNameValid = FormValidation.isPasswordValid(password)
        assertFalse(userNameValid)
    }
    @Test
    fun `deve retornar true quando o password tiver 1000 caracteres`(){
        val password = "fghjhakjjdsakjdasdjasjdhajkdajhjskhdjhkahsjhjhssssfghjhakjjdsakjdasdjasjdhajkdajhjskhdjhkahsjhjhssssfghjhakjjdsakjdasdjasjdhajkdajhjskhdjhkahsjhjhssssfghjhakjjdsakjdasdjasjdhajkdajhjskhdjhkahsjhjhssssfghjhakjjdsakjdasdjasjdhajkdajhjskhdjhkahsjhjhssssfghjhakjjdsakjdasdjasjdhajkdajhjskhdjhkahsjhjhssssfghjhakjjdsakjdasdjasjdhajkdajhjskhdjhkahsjhjhssssfghjhakjjdsakjdasdjasjdhajkdajhjskhdjhkahsjhjhssssfghjhakjjdsakjdasdjasjdhajkdajhjskhdjhkahsjhjhssssfghjhakjjdsakjdasdjasjdhajkdajhjskhdjhkahsjhjhssssfghjhakjjdsakjdasdjasjdhajkdajhjskhdjhkahsjhjhssssfghjhakjjdsakjdasdjasjdhajkdajhjskhdjhkahsjhjhssssfghjhakjjdsakjdasdjasjdhajkdajhjskhdjhkahsjhjhssssfghjhakjjdsakjdasdjasjdhajkdajhjskhdjhkahsjhjhssssfghjhakjjdsakjdasdjasjdhajkdajhjskhdjhkahsjhjhssssfghjhakjjdsakjdasdjasjdhajkdajhjskhdjhkahsjhjhssssfghjhakjjdsakjdasdjasjdhajkdajhjskhdjhkahsjhjhssssfghjhakjjdsakjdasdjasjdhajkdajhjskhdjhkahsjhjhssssfghjhakjjdsakjdasdjasjdhajkdajhjskhdjhkahsjhjhssssfghjhakjjdsakjdasdjasjdhajkdajhjskhdjhkahsjhjhssss"
        val userNameValid = FormValidation.isPasswordValid(password)
        assertTrue(userNameValid)
    }
    @Test
    fun `deve retornar true quando o telefone tiver 11 caracteres`(){
        val telefone = "12345678901"
        val phoneValid = FormValidation.isPhoneValid(telefone)
        assertTrue(phoneValid)
    }
    @Test
    fun `deve retornar false quando o telefone tiver menos 11 caracteres`(){
        val telefone = "1234567890"
        val phoneValid = FormValidation.isPhoneValid(telefone)
        assertFalse(phoneValid)
    }
    @Test
    fun `deve retornar false quando o telefone tiver mais de  11 caracteres`(){
        val telefone = "123456789011"
        val phoneValid = FormValidation.isPhoneValid(telefone)
        assertFalse(phoneValid)
    }
    @Test
    fun `deve retornar false quando o nome nao tiver caracteres`(){
        val nome = ""
        val nameValid = FormValidation.isNameValid(nome)
        assertFalse(nameValid)
    }
    @Test
    fun `deve retornar false quando o nome tiver 2 caracteres`(){
        val nome = "ai"
        val nameValid = FormValidation.isNameValid(nome)
        assertFalse(nameValid)
    }
    @Test
    fun `deve retornar true quando o nome tiver 3 ou mais caracteres`(){
        val nome = "aia"
        val nameValid = FormValidation.isNameValid(nome)
        assertTrue(nameValid)
    }
}