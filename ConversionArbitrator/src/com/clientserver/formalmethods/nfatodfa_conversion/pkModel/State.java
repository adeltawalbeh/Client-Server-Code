package com.clientserver.formalmethods.nfatodfa_conversion.pkModel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Ahmad Fahmawi
 */
public class State implements Serializable {

    private static final long serialVersionUID = 6529685098267757690L;
    public String stateName;
    public Boolean isFinal;
    public Boolean visited = false;
    public String loopOn;
    public ArrayList<Transition> transitions = new ArrayList<Transition>();

    public Boolean isVisited() {
        return visited;
    }

    public ArrayList<Transition> getTransitions() {
        return transitions;
    }

    public State() {
    }

    public State(String stateName) {
        this.stateName = stateName;
        this.isFinal = false;
    }

    public State(String stateName, Boolean isFinal) {
        this.stateName = stateName;
        this.isFinal = isFinal;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Boolean getIsFinal() {
        return isFinal;
    }

    public void setIsFinal(Boolean isFinal) {
        this.isFinal = isFinal;
    }

    public void addTransitions(ArrayList<Transition> transitions) {
        for (Transition transition : transitions) {

            this.transitions.add(transition);
        }
    }

    public void addTransition(Transition transition) {

        for (int i = 0; i < this.transitions.size(); i++) {
            if (this.transitions.get(i).state.stateName == transition.state.stateName) {
                this.loopOn = transition.on;
                return;
            }
        }
        this.transitions.add(transition);
    }

    public Transition getTransition(int i) {
        return transitions.get(i);
    }

    @Override
    public String toString() {
        return "State{" + "stateName=" + stateName + ", isFinal=" + isFinal + ", visited=" + visited + ", loopOn=" + loopOn + ", transitions=" + transitions + '}';
    }

}
