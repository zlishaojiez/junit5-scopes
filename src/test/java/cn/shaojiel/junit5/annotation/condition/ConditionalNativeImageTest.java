package cn.shaojiel.junit5.annotation.condition;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledInNativeImage;
import org.junit.jupiter.api.condition.EnabledInNativeImage;

class ConditionalNativeImageTest {

    @Test
    @EnabledInNativeImage
    void onlyWithinNativeImage() {
        // ...
    }

    @Test
    @DisabledInNativeImage
    void neverWithinNativeImage() {
        // ...
    }
}
