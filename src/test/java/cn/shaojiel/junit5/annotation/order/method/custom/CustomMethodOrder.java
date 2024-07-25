package cn.shaojiel.junit5.annotation.order.method.custom;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.MethodOrdererContext;

import java.util.Comparator;

public class CustomMethodOrder implements MethodOrderer {

    @Override
    public void orderMethods(MethodOrdererContext methodOrdererContext) {
        methodOrdererContext.getMethodDescriptors().sort(Comparator.comparing(descriptor -> descriptor.getMethod().getName()));
    }

}
