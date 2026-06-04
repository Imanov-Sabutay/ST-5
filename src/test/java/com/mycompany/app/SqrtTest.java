package com.mycompany.app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SqrtTest {

    private static final double ACC = 1e-7;

    @Test
    @DisplayName("average: 5 и 11")
    void srednee_dlya_pyati_i_odinnadtsati() {
        Sqrt s = new Sqrt(50);
        assertEquals(8.0, s.average(5.0, 11.0), 1e-12);
    }

    @Test
    @DisplayName("average: -11 и 17")
    void srednee_otritsatelnoe_i_polozhitelnoe() {
        Sqrt s = new Sqrt(50);
        assertEquals(3.0, s.average(-11.0, 17.0), 1e-12);
    }

    @Test
    @DisplayName("good: точное значение для 529")
    void tochnaya_ocenka_dlya_pyatisot_dvadtsati_devyati() {
        Sqrt s = new Sqrt(50);
        assertTrue(s.good(23.0, 529.0));
    }

    @Test
    @DisplayName("good: грубая оценка отклоняется")
    void grubaya_ocenka_ne_podkhodit() {
        Sqrt s = new Sqrt(50);
        assertFalse(s.good(22.0, 529.0));
    }

    @Test
    @DisplayName("improve: шаг для 18")
    void shag_uluchsheniya_dlya_vosemnadtsati() {
        Sqrt s = new Sqrt(18);
        assertEquals(4.5, s.improve(3.0, 18.0), 1e-12);
    }

    @Test
    @DisplayName("improve: угадывание уже верное")
    void uluchshenie_bez_izmeneniy() {
        Sqrt s = new Sqrt(2401);
        assertEquals(49.0, s.improve(49.0, 2401.0), 1e-12);
    }

    @Test
    @DisplayName("iter: сразу возвращает угаданное")
    void iter_bez_dopolnitelnykh_shagov() {
        Sqrt s = new Sqrt(72);
        assertEquals(14.0, s.iter(14.0, 196.0), 1e-10);
    }

    @Test
    @DisplayName("iter: сходимость к корню из 27")
    void iter_dlya_dvadtsati_semi() {
        Sqrt s = new Sqrt(27);
        assertEquals(Math.sqrt(27.0), s.iter(3.0, 27.0), ACC);
    }

    @Test
    @DisplayName("calc: корень из 2401")
    void calc_dlya_dvukh_tysyach_chetyresta_odin() {
        assertEquals(49.0, new Sqrt(2401.0).calc(), ACC);
    }

    @Test
    @DisplayName("calc: корень из 18")
    void calc_dlya_vosemnadtsati() {
        assertEquals(Math.sqrt(18.0), new Sqrt(18.0).calc(), ACC);
    }

    @Test
    @DisplayName("calc: ноль")
    void calc_dlya_nulya() {
        assertEquals(0.0, new Sqrt(0.0).calc(), 1e-12);
    }

    @Test
    @DisplayName("calc: дробное число 0.09")
    void calc_dlya_nulya_nulevogo_devyati() {
        assertEquals(0.3, new Sqrt(0.09).calc(), ACC);
    }

    @Test
    @DisplayName("конструктор сохраняет arg")
    void pole_arg_sokhranyaetsya() {
        Sqrt s = new Sqrt(1849.0);
        assertEquals(1849.0, s.arg, 1e-12);
        assertEquals(43.0, s.calc(), ACC);
    }
}
