package org.mycompany.health;

import java.util.Map;
import java.util.UUID;

import org.apache.camel.health.HealthCheckResultBuilder;
import org.apache.camel.impl.health.AbstractHealthCheck;

/**
 * An health checker
 */
public class ApplicationHealthCheck extends AbstractHealthCheck {
    
	private State state;

    public ApplicationHealthCheck(String group, String id) {
        super(group, id);

        this.state = State.UP;

        getConfiguration().setEnabled(true);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    protected void doCall(HealthCheckResultBuilder builder, Map<String, Object> options) {
        builder.state(state).detail("random.value", UUID.randomUUID().toString());
    }
    
}