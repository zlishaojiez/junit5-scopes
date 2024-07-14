package cn.shaojiel.junit5.annotation.condition.custom.extension;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith({EnabledCondition.class})
public @interface Enabled {

    @AliasFor("enable")
    boolean value() default true;

    @AliasFor("value")
    boolean enable() default true;
}
