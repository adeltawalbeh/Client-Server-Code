/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clientserver.formalmethods.nfatodfa_conversion.pkConverter;

import com.clientserver.formalmethods.nfatodfa_conversion.pkModel.DFA;
import com.clientserver.formalmethods.nfatodfa_conversion.pkModel.NFA;
import com.clientserver.formalmethods.nfatodfa_conversion.pkModel.State;
import com.clientserver.formalmethods.nfatodfa_conversion.pkModel.Transition;

/**
 *
 * @author Ahmad Fahmawi
 */
public class NFAtoDFA_Converter {

    public DFA convert(NFA nfa) {

        DFA dfa = new DFA(extractStates(nfa.state));
        return dfa;

    }

    private State extractStates(State nFA_State) {

        if (nFA_State.isVisited()) {
//            System.out.println("extractStates line 33:: " + nFA_State.stateName + " is visited  ");
            State state = new State(nFA_State.getStateName(), nFA_State.getIsFinal());
            state.addTransitions(nFA_State.getTransitions());
            return null;
        }

        State startState = new State(nFA_State.getStateName(), nFA_State.getIsFinal());

        for (int i = 0; i < nFA_State.getTransitions().size(); i++) {
            nFA_State.visited = true;
            //getTransition(i).changeStateVisited(i, true);
            if (nFA_State.getTransition(i).getState().stateName.equals(nFA_State.stateName)) {
//                System.out.println("*******" + nFA_State.getTransition(i).getState().stateName + "************" + nFA_State.stateName + "*******");

                startState.loopOn = nFA_State.loopOn;
            } else {
                //    nFA_State.getTransition(i).changeStateVisited(i, true);
                if (nFA_State.getTransition(i).getState() != null) {

                    State state = extractStates(nFA_State.getTransition(i).getState());
                    if (state != null) {
                        state.addTransitions(nFA_State.getTransition(i).getState().transitions);
//                        System.out.println("com.ahmad.clientservor.formalmethods.nfatodfa_converter.NFAtoDFA_Converter.extractStates() ADDED");
                        startState.addTransition(new Transition(state, nFA_State.getTransition(i).getOn()));
                    }
                }
            }
        }

        return startState;

    }

}
