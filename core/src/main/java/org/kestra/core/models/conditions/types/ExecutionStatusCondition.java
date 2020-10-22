package org.kestra.core.models.conditions.types;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.kestra.core.models.conditions.Condition;
import org.kestra.core.models.conditions.ConditionContext;
import org.kestra.core.models.flows.State;

import java.util.List;
import javax.validation.Valid;

@SuperBuilder
@ToString
@EqualsAndHashCode
@Getter
@NoArgsConstructor
public class ExecutionStatusCondition extends Condition {
    @Valid
    public List<State.Type> in;

    @Valid
    public List<State.Type> notIn;

    @Override
    public boolean test(ConditionContext conditionContext) {
        if (conditionContext.getExecution() == null) {
            throw new IllegalArgumentException("Invalid condition with execution null");
        }

        boolean result = true;

        if (this.in != null && !this.in.contains(conditionContext.getExecution().getState().getCurrent())) {
            result = false;
        }

        if (this.notIn != null && this.notIn.contains(conditionContext.getExecution().getState().getCurrent())) {
            result = false;
        }

        return result;
    }
}
