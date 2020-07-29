/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clientserver.formalmethods.nfatodfa_conversion.pkInterface;

import com.clientserver.formalmethods.nfatodfa_conversion.pkModel.NFA;
import com.clientserver.formalmethods.nfatodfa_conversion.pkModel.State;
import com.clientserver.formalmethods.nfatodfa_conversion.pkModel.Transition;
import java.util.ArrayList;

/**
 *
 * @author Ahmad Fahmawi
 */
public abstract class DB {

    public static ArrayList<NFA> getAutomata() {
        ArrayList<NFA> al = new ArrayList<>();

        State q0 = new State("q0");
        State q1 = new State("q1");
        State q2 = new State("q2", true);
        State q3 = new State("q3");
        State q4 = new State("q4", true);

        q3.addTransition(new Transition(q0, "$"));
        q2.addTransition(new Transition(q3, "$"));

        q1.addTransition(new Transition(q2, "b"));
        q1.addTransition(new Transition(q4, "a"));

        q0.addTransition(new Transition(q1, "a"));

        NFA nfa = new NFA(q0, "aa||ab*");
        al.add(nfa);

        State q10 = new State("q0");
        State q11 = new State("q1");
        State q12 = new State("q2");
        State q13 = new State("q3", true);

        q13.addTransition(new Transition(q13, "a"));
        q13.addTransition(new Transition(q12, "b"));
        q12.addTransition(new Transition(q13, "a"));
        q12.addTransition(new Transition(q12, "b"));

        q11.addTransition(new Transition(q11, "a"));
        q11.addTransition(new Transition(q10, "b"));
        q10.addTransition(new Transition(q11, "b"));
        q10.addTransition(new Transition(q12, "a"));

        NFA nfa1 = new NFA(q10, "{a,b}*");
        al.add(nfa1);

        State q20 = new State("q0");
        State q21 = new State("q1");
        State q22 = new State("q2");
        State q23 = new State("q3", true);

        q22.addTransition(new Transition(q23, "1"));
        q22.addTransition(new Transition(q22, "0"));
        q23.addTransition(new Transition(q23, "0"));
        q23.addTransition(new Transition(q23, "1"));
        q21.addTransition(new Transition(q22, "0"));
        q21.addTransition(new Transition(q20, "1"));
        q20.addTransition(new Transition(q21, "0"));
        q20.addTransition(new Transition(q21, "1"));

        NFA nfa2 = new NFA(q20, "0+1*0*");

        al.add(nfa2);
        State q30 = new State("q0", true);
        State q31 = new State("q1");
        State q32 = new State("q2");

        q31.addTransition(new Transition(q30, "0"));

        q31.addTransition(new Transition(q32, "1"));

        q30.addTransition(new Transition(q31, "1"));
        q30.addTransition(new Transition(q32, "$"));

        NFA nfa3 = new NFA(q30, "{0,1}*");
        al.add(nfa3);
        State q40 = new State("q0", true);
        State q41 = new State("q1");
        State q42 = new State("q2");

        q42.addTransition(new Transition(q40, "0"));
        q41.addTransition(new Transition(q40, "0"));

        q41.addTransition(new Transition(q42, "1"));
        q40.addTransition(new Transition(q41, "1"));

        NFA nfa4 = new NFA(q40, "{110}*");
        al.add(nfa4);
        State q50 = new State("q0");
        State q51 = new State("q1", true);

        q50.addTransition(new Transition(q51, "a"));

        NFA nfa5 = new NFA(q50, "a");
        al.add(nfa5);
        State q60 = new State("q0");
        State q61 = new State("q1");
        State q62 = new State("q2", true);
        State q63 = new State("q3");

        q61.addTransition(new Transition(q62, "a"));
        q60.addTransition(new Transition(q61, "a"));
        q60.addTransition(new Transition(q63, "a"));
        NFA nfa6 = new NFA(q60, "aa");
        al.add(nfa6);

        State q70 = new State("q0");
        State q71 = new State("q1");
        State q72 = new State("q2", true);
        State q73 = new State("q3");

        q73.addTransition(new Transition(q70, "$"));
        q72.addTransition(new Transition(q73, "$"));

        q71.addTransition(new Transition(q72, "b"));

        q70.addTransition(new Transition(q71, "a"));

        NFA nfa7 = new NFA(q70, "ab$${ab}+");
        al.add(nfa7);

        State q80 = new State("q0");
        State q81 = new State("q1");
        State q82 = new State("q2", true);
        State q83 = new State("q3");
        State q84 = new State("q4", true);

        q83.addTransition(new Transition(q80, "$"));
        q82.addTransition(new Transition(q83, "$"));

        q81.addTransition(new Transition(q82, "b"));
        q81.addTransition(new Transition(q84, "a"));

        q0.addTransition(new Transition(q81, "a"));

        NFA nfa8 = new NFA(q80, "{ab$$}*aa");
        al.add(nfa8);

        State q90 = new State("q0");
        State q91 = new State("q1");
        State q92 = new State("q2", true);
        State q93 = new State("q3");

        q92.addTransition(new Transition(q92, "b"));
        q92.addTransition(new Transition(q92, "a"));
        q93.addTransition(new Transition(q93, "b"));
        q93.addTransition(new Transition(q93, "a"));
        q91.addTransition(new Transition(q92, "b"));
        q91.addTransition(new Transition(q93, "a"));
        q90.addTransition(new Transition(q91, "a"));
        q90.addTransition(new Transition(q93, "b"));

        NFA nfa9 = new NFA(q90, "ab{a}*{b}*");
        al.add(nfa9);

        State q100 = new State("q0");
        State q101 = new State("q1");
        State q102 = new State("q2");
        State q103 = new State("q3");
        State q104 = new State("q4", true);

        q103.addTransition(new Transition(q104, "a"));
        q102.addTransition(new Transition(q103, "b"));
        q101.addTransition(new Transition(q102, "b"));
        q100.addTransition(new Transition(q101, "a"));

        NFA nfa10 = new NFA(q100, "abba");
        al.add(nfa10);

        State q110 = new State("q0");
        State q111 = new State("q1", true);
        State q112 = new State("q2");

        q112.addTransition(new Transition(q110, "b"));
        q111.addTransition(new Transition(q112, "$"));
        q111.addTransition(new Transition(q111, "a"));
        q110.addTransition(new Transition(q111, "a"));

        NFA nfa11 = new NFA(q110, "{a}*$ba");
        al.add(nfa11);

        State q120 = new State("q0", true);
        q120.addTransition(new Transition(q120, "a"));

        NFA nfa12 = new NFA(q120, "{a}*");
        al.add(nfa12);

        State q130 = new State("q0");
        State q131 = new State("q1", true);
        State q132 = new State("q2");

        q131.addTransition(new Transition(q132, "b"));
        q131.addTransition(new Transition(q132, "a"));
        q130.addTransition(new Transition(q131, "b"));
        q130.addTransition(new Transition(q130, "a"));

        NFA nfa13 = new NFA(q130, "{a}*b");
        al.add(nfa13);

        return al;
    }

}
