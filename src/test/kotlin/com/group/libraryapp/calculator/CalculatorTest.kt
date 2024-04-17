package com.group.libraryapp.calculator

fun main() {
    val calculatorTest = CalculatorTest()
    calculatorTest.addTest()
    calculatorTest.minusTest()
    calculatorTest.multiply()
    calculatorTest.divide()
}

class CalculatorTest {

    fun addTest() {
        //given
        val calculator = Calculator(5)
        //when
        calculator.add(3)
        //then
        if (calculator.number != 8) {
            throw IllegalArgumentException()
        }
    }


    fun minusTest(){
        //given
        val calculator = Calculator(5)
        //when
        calculator.minus(2)
        //then
        if(calculator.number != 3){
            throw IllegalArgumentException()
        }
    }

    fun multiply(){
        //given
        val calculator = Calculator(5)
        //when
        calculator.multiply(2)

        //then
        if(calculator.number != 10){
            throw IllegalArgumentException()
        }
    }


    fun divide(){
        //given
        val calculator = Calculator(5)
        //when
        calculator.divide(2)

        //then
        if (calculator.number != 2){
            throw IllegalArgumentException()
        }
    }



}