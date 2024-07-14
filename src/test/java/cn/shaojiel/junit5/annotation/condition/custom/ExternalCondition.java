package cn.shaojiel.junit5.annotation.condition.custom;

/**
 * There are several cases where a condition method would need to be static: • when @EnabledIf or @DisabledIf is used at class level
 * • when @EnabledIf or @DisabledIf is used on a @ParameterizedTest or a @TestTemplate method
 * • when the condition method is located in an external class
 * In any other case, you can use either static methods or instance methods as condition methods.
 * It is often the case that you can use an existing static method in a utility class as a custom condition.
 */
public class ExternalCondition {

    static boolean customCondition() {
        return true;
    }
}
