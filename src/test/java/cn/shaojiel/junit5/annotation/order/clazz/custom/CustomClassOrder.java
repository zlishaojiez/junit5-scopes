package cn.shaojiel.junit5.annotation.order.clazz.custom;

import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.ClassOrdererContext;

import java.util.Comparator;

public class CustomClassOrder implements ClassOrderer {

    @Override
    public void orderClasses(ClassOrdererContext classOrdererContext) {
        classOrdererContext.getClassDescriptors().sort(Comparator.comparing(descriptor -> descriptor.getTestClass().getSimpleName()));
    }
}
