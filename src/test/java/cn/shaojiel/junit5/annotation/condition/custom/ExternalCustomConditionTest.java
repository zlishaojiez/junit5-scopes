package cn.shaojiel.junit5.annotation.condition.custom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;

class ExternalCustomConditionTest {

    @Test
    @EnabledIf("cn.shaojiel.junit5.annotation.condition.custom.ExternalCondition#customCondition")
    void enabled() {
        // ...
    }
}
