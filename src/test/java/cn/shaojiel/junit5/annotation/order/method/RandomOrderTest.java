package cn.shaojiel.junit5.annotation.order.method;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.Random.class)
class RandomOrderTest {

    @Test
    void nullValues() {
        // perform assertions against null values
    }

    @Test
    void emptyValues() {
        // perform assertions against empty values
    }

    @Test
    void validValues() {
        // perform assertions against valid values
    }
}
