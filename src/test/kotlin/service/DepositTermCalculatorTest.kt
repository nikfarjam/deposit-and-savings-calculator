package service

import com.mehdi.model.Investment
import com.mehdi.model.PaymentType
import com.mehdi.model.Saving
import com.mehdi.service.DepositTermCalculator
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class DepositTermCalculatorTest {

    private lateinit var calculator: DepositTermCalculator

    @BeforeEach
    fun setUp() {
        calculator = DepositTermCalculator()
    }

    @DisplayName("Calculate saving for valid inputs")
    @ParameterizedTest
    @MethodSource("provideValidInputs")
    fun calculateSavingForValidInputs(input: Investment, expected: Saving) {
        val result = calculator.calculate(input);

        assertEquals(expected.interestRate, result.interestRate, "Interest rate must be correct for $input")
        assertEquals(expected.finalBalance, result.finalBalance, "Final balance must be correct for $input")
    }

    private fun provideValidInputs(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(Investment(10000.0, 0.011, 36, PaymentType.Monthly), Saving(0.011, 10335.0)),
            Arguments.of(Investment(10000.0, 0.011, 36, PaymentType.Quarterly), Saving(0.011, 10335.0)),
            Arguments.of(Investment(10000.0, 0.011, 36, PaymentType.Annually), Saving(0.011, 10334.0)),
            Arguments.of(Investment(10000.0, 0.011, 36, PaymentType.AtMaturity), Saving(0.011, 10330.0)),

            Arguments.of(Investment(25000.0, 0.009, 36, PaymentType.AtMaturity), Saving(0.009, 25675.0)),
            Arguments.of(Investment(25000.0, 0.009, 36, PaymentType.Monthly), Saving(0.009, 25684.0)),
            Arguments.of(Investment(25000.0, 0.009, 36, PaymentType.Annually), Saving(0.009, 25681.0)),
        )
    }
}