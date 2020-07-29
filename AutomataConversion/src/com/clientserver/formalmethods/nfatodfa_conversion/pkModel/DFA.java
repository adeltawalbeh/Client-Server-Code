package com.clientserver.formalmethods.nfatodfa_conversion.pkModel;

import java.io.Serializable;

/**
 *
 * @author HP
 */
public class DFA implements Serializable {

    private static final long serialVersionUID = 6529685098267757690L;
    State StartState;
    static int transitionCounter;
    String signature;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public DFA(State StartState) {

        this.StartState = StartState;
    }

    public DFA() {
    }

    private boolean checkDFA(State state, String input) {

        if (!state.isFinal && input.isEmpty()) {

            return false;
        }
        if (!input.isEmpty()) {
            if ((input.charAt(0) + "").equals(state.loopOn)) {

                return checkDFA(state, input.substring(1));
            } else {
                transitionCounter++;
                State next = null;

                for (int i = 0; i < state.transitions.size(); i++) {

                    if (state.transitions.get(i).on.equals(input.charAt(0) + "")) {
                        next = state.transitions.get(i).state;

                    }

                }

                if (next == null) {

                    return false;
                } else if (next != null) {

                    return checkDFA(next, input.substring(1));
                }
            }
        }

        return true;

    }

    State getState(String input) {
        return null;
    }

    public Response start(String input) {
        transitionCounter = 1;
        boolean isaccepted = checkDFA(StartState, input);
        return new Response(isaccepted, transitionCounter, "DFA", signature, input);

    }

    @Override
    public String toString() {
        return "DFA{" + "StartState=" + StartState + '}';
    }

}
