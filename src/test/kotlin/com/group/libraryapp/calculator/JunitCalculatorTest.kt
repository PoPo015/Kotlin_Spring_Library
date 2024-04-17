package com.group.libraryapp.calculator

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class JunitCalculatorTest {


    @Test
    @DisplayName("숫자 값증가 테스트")
    fun addTest() {
        //given
        val calculator = Calculator(5)
        //when
        calculator.add(3)
        //then
        assertThat(calculator.number).isEqualTo(8)
    }

    @Test
    @DisplayName("숫자 값감소 테스트")
    fun minusTest() {
        //given
        val calculator = Calculator(5)
        //when
        calculator.minus(3)
        //then
        assertThat(calculator.number).isEqualTo(2)
    }

    @Test
    @DisplayName("숫자 곱셈 테스트")
    fun multiplyTest() {
        //given
        val calculator = Calculator(5)
        //when
        calculator.multiply(3)
        //then
        assertThat(calculator.number).isEqualTo(15)
    }


    @Test
    @DisplayName("숫자 나눗셈 테스트")
    fun divideTest() {
        //given
        val calculator = Calculator(5)
        //when
        calculator.divide(2)
        //then
        assertThat(calculator.number).isEqualTo(2)
    }

    @Test
    @DisplayName("숫자 나눗셈 예외테스트")
    fun divideExceptionTest() {
        //given
        val calculator = Calculator(5)
        //when & then
        val message = assertThrows<IllegalArgumentException> {
            calculator.divide(0)
        }.message

        assertThat(message).isEqualTo("0으로 나눌수 없습니다.")
    }

}