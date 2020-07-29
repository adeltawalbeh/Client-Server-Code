package com.clientserver.formalmethods.nfatodfa_conversion.pkModel;

import java.io.Serializable;

/**
 *
 * @author Ahmad Fahmawi
 */
public class Transition implements Serializable {

    private static final long serialVersionUID = 6529685098267757690L;
    State state;
    String on;

    public Transition() {
    }

    public Transition(State state, String on) {
        this.state = state;
        this.on = on;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "\nTransition{" + "state=" + state.getStateName() + ", on=" + on + '}';
    }

    public String getOn() {
        return on;
    }

    public void setOn(String on) {
        this.on = on;
    }

}
