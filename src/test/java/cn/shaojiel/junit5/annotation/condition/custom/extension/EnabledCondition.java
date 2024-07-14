package cn.shaojiel.junit5.annotation.condition.custom.extension;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.lang.reflect.AnnotatedElement;
import java.util.Optional;

public class EnabledCondition implements ExecutionCondition {

    private static final ConditionEvaluationResult ENABLED = ConditionEvaluationResult.enabled(
            "Enable by default");

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
        AnnotatedElement element = context.getElement().orElse(null);
        /**
         * org.junit.platform.commons.util.AnnotationUtils.findAnnotation does not support spring  @AliasFor
         *
         * return findAnnotation(element, Enabled.class)
         *                 .map(annotation -> toResult(element, annotation))
         *                 .orElse(ENABLED);
         */
        return Optional.ofNullable(AnnotatedElementUtils.findMergedAnnotation(element, Enabled.class))
                .map(annotation -> toResult(element, annotation))
                .orElse(ENABLED);
    }

    private ConditionEvaluationResult toResult(AnnotatedElement element, Enabled annotation) {
        boolean enable = annotation.enable();
        return enable ? ConditionEvaluationResult.enabled(element + " is @Enabled(true)") :
                ConditionEvaluationResult.disabled(element + " is @Enabled(false)");
    }
}
