package br.com.ariel.calculoimposto.domain.util;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalUtil {

    public static boolean isLessTo(BigDecimal base, BigDecimal compared) {
        return base.compareTo(compared) < 0;
    }

    public static boolean isLessOrEqualTo(
            BigDecimal base,
            BigDecimal comparado) {

        return base.compareTo(comparado) <= 0;
    }

    public static boolean isLessOrEqualToZero(BigDecimal base) {
        return isLessOrEqualTo(base, BigDecimal.ZERO);
    }

    public static BigDecimal getValueInPercentage(BigDecimal totalValue, BigDecimal valueInPercentage) {
        return valueInPercentage
                .multiply(totalValue)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    }

    public static boolean isNegative(BigDecimal value) {
        return value.compareTo(BigDecimal.ZERO) < 0;

    }
}
