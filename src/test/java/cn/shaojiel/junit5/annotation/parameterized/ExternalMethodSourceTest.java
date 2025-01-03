package cn.shaojiel.junit5.annotation.parameterized;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class ExternalMethodSourceTest {

    @ParameterizedTest
    @MethodSource("cn.shaojiel.junit5.annotation.parameterized.StringsProviders#tinyStrings")
    void testWithExternalMethodSource(String tinyString) {
        // test with tiny string
        Assertions.assertThat(tinyString).isNotNull();
    }

}
