package cn.shaojiel.junit5.annotation.generator;

import org.junit.jupiter.api.DisplayNameGenerator;

import java.lang.reflect.Method;

public class CustomDisplayNameGenerator implements DisplayNameGenerator {

    public CustomDisplayNameGenerator() {

    }

    @Override
    public String generateDisplayNameForClass(Class<?> aClass) {
        return "Class %s test with CustomDisplayNameGenerator".formatted(aClass.getSimpleName());
    }

    @Override
    public String generateDisplayNameForNestedClass(Class<?> aClass) {
        return "NestedClass %s test with CustomDisplayNameGenerator".formatted(aClass.getSimpleName());
    }

    @Override
    public String generateDisplayNameForMethod(Class<?> aClass, Method method) {
        return "Class %s method %s test with CustomDisplayNameGenerator".formatted(aClass.getSimpleName(),
                method.getName());
    }
}
