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

    public int findMultiTransition(String input) {
        int indexCounter = 0;
        for (int i = 0; i < this.transitions.size(); i++) {
            if (this.transitions.get(i).on.equals(input) && indexCounter > 1) {
                return indexCounter;
            }
            indexCounter++;
        }

        return -1;
    }

    public State merg(int i, int repeatedTransition) {
        String FStateName = this.transitions.get(i).state.stateName;
        String SStateName = this.transitions.get(repeatedTransition).state.stateName;
        String newStateName = FStateName + "," + SStateName;

        String input = this.transitions.get(i).on;

        boolean newIsFinal = this.transitions.get(i).state.isFinal || this.transitions.get(repeatedTransition).state.isFinal;
        State fA_State = new State(newStateName, newIsFinal);

        for (Transition transition : transitions.get(i).state.getTransitions()) {
            fA_State.addTransition(transition);
        }
        for (Transition transition : transitions.get(repeatedTransition).state.getTransitions()) {
            fA_State.addTransition(transition);
        }
        this.transitions.remove(repeatedTransition);
        this.transitions.remove(i);
        //this.transitions.add(i, new Transition( fA_State,fA_State.));
        return fA_State;
    }

    @Override
    public String toString() {
        return "State{" + "stateName=" + stateName + ", isFinal=" + isFinal + ", visited=" + visited + ", loopOn=" + loopOn + ", transitions=" + transitions + '}';
    }

}
