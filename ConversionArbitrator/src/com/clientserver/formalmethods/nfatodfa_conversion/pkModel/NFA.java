package com.clientserver.formalmethods.nfatodfa_conversion.pkModel;

import java.io.Serializable;

/**
 *
 * @author Ahmad Fahmawi
 */
public class NFA implements Serializable {

    private static final long serialVersionUID = 6529685098267757690L;

    public State state;
    static int transitionCounter;
    String signature;

    public NFA(State state, String signature) {
        this.state = state;
        this.signature = signature;
    }

    public NFA() {
    }

    private boolean checkNFA(State state, String input) {

        if (!state.isFinal && (input.isEmpty() || input.equals("$")) && state.visited) {

            return false;
        } else if (state.isFinal && input.isEmpty()) {

            return true;
        }

        if (!input.isEmpty()) {

            State next = null;
            for (int i = 0; i < state.transitions.size(); i++) {
                if ((input.charAt(0) + "").equals(state.loopOn)) {

                    return checkNFA(state, input.substring(1));
                } else {
                    if (state.transitions.get(i).on.equals(input.charAt(0) + "")
                            || "$".equals(input.charAt(0) + "")
                            && !state.transitions.get(i).state.visited) {
                        next = state.transitions.get(i).state;
                        state.transitions.get(i).state.visited = true;
                        transitionCounter++;
                        break;
                    }

                }
            }

            if (next == null) {

                return false;
            } else if (next != null) {

                return checkNFA(next, input.substring(1));
            }
        }

        return true;

    }

    public Response start(String input) {
        transitionCounter = 1;
        boolean isaccepted = checkNFA(this.state, input);
        return new Response(isaccepted, transitionCounter, "NFA", signature, input);

    }

    public void addTransition(Transition transition) {

        this.state.addTransition(transition);

    }

}
