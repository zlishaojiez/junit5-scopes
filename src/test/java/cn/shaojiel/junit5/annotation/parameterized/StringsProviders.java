package cn.shaojiel.junit5.annotation.parameterized;

import java.util.stream.Stream;

public class StringsProviders {

    public static Stream<String> tinyStrings() {
        return Stream.of(".", "oo", "OOO");
    }

}
