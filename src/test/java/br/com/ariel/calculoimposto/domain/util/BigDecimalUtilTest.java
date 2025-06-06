package br.com.ariel.calculoimposto.domain.util;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class BigDecimalUtilTest {


    @Test
    void isLessOrEqualTo() {
        assertThat(BigDecimalUtil.isLessOrEqualTo(BigDecimal.ONE, BigDecimal.ONE)).isTrue();
        assertThat(BigDecimalUtil.isLessOrEqualTo(BigDecimal.ONE, BigDecimal.TEN)).isTrue();
        assertThat(BigDecimalUtil.isLessOrEqualTo(BigDecimal.ONE, BigDecimal.ZERO)).isFalse();
    }

    @Test
    void isLessOrEqualToZero() {
        assertThat(BigDecimalUtil.isLessOrEqualToZero(BigDecimal.ZERO)).isTrue();
        assertThat(BigDecimalUtil.isLessOrEqualToZero(BigDecimal.valueOf(-1))).isTrue();
        assertThat(BigDecimalUtil.isLessOrEqualToZero(BigDecimal.ONE)).isFalse();
    }

    @Test
    void obtemValorEmPorcentagem() {
        assertThat(BigDecimalUtil.getValueInPercentage(BigDecimal.valueOf(100), BigDecimal.valueOf(20))).isEqualByComparingTo(BigDecimal.valueOf(20));
    }

}
