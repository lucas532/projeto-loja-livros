package com.lucas.projetolojalivros.helper;

import static io.github.benas.randombeans.EnhancedRandomBuilder.aNewEnhancedRandomBuilder;
import static java.lang.Integer.MAX_VALUE;
import static lombok.AccessLevel.PRIVATE;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.Randomizer;
import io.github.benas.randombeans.randomizers.range.BigDecimalRangeRandomizer;
import io.github.benas.randombeans.randomizers.range.IntegerRangeRandomizer;
import io.github.benas.randombeans.randomizers.range.LocalDateRangeRandomizer;
import io.github.benas.randombeans.randomizers.range.LongRangeRandomizer;
import io.github.benas.randombeans.randomizers.text.StringRandomizer;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.NoArgsConstructor;
import wiremock.org.apache.commons.lang3.RandomStringUtils;

@NoArgsConstructor(access = PRIVATE)
public class RandomHelper {

    private static final EnhancedRandomBuilder random = aNewEnhancedRandomBuilder();
    private static final Double MIN = 0.0;
    private static final Integer MIN_INTEGER = 0;
    private static final Long MIN_LONG = 0L;
    private static final Double MAX = 9999.9;
    private static final Double NEGATIVE = -9999.9;

    public static String gerarString() {
        return random(String.class, new StringRandomizer());
    }

    public static String gerarString(int tamanho) {
        return random(String.class, new StringRandomizer(tamanho, tamanho, gerarLong()));
    }

    public static String gerarStringNumerico(int tamanho) {
        return RandomStringUtils.random(tamanho, false, true);
    }

    public static String gerarStringEmail() {
        return random(String.class, new StringRandomizer()).concat("@email");
    }

    public static Integer gerarInteger() {
        return random(Integer.class, new IntegerRangeRandomizer(MIN_INTEGER, MAX_VALUE));
    }

    public static Integer gerarInteger(int min, int max) {
        return random(Integer.class, new IntegerRangeRandomizer(min, max));
    }

    public static Long gerarLong() {
        return random(Long.class, new LongRangeRandomizer(MIN_LONG, Long.MAX_VALUE));
    }

    public static BigDecimal gerarBigDecimal() {
        return random(BigDecimal.class, new BigDecimalRangeRandomizer(MIN, MAX));
    }

    public static BigDecimal gerarBigDecimal(double min, double max) {
        return random(BigDecimal.class, new BigDecimalRangeRandomizer(min, max));
    }

    public static <T> T gerarObjeto(Class<T> type) {
        return aNewEnhancedRandomBuilder().build().nextObject(type);
    }

    public static LocalDate gerarData(LocalDate inicio, LocalDate fim) {
        return random(LocalDate.class, new LocalDateRangeRandomizer(inicio, fim));
    }

    private static <T> T random(Class<T> clazz, Randomizer<T> randomizer) {
        return random.randomize(clazz, randomizer).build().nextObject(clazz);
    }
}
