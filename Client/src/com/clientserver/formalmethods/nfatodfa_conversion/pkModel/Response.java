package com.clientserver.formalmethods.nfatodfa_conversion.pkModel;



import java.io.Serializable;

/**
 *
 * @author Ahmad Fahmawi
 */
public class Response implements Serializable {
private static final long serialVersionUID = 6529685098267757690L;
    boolean isAccepted;
    int numberOfTransitions;
    String automata;
    String signature;
    String input;

    public Response() {
    }

    public Response(boolean isAccepted, int numberOfTransitions, String automata, String signature, String input) {
        this.isAccepted = isAccepted;
        this.numberOfTransitions = numberOfTransitions;
        this.automata = automata;
        this.signature = signature;
        this.input = input;
    }

    public boolean isIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    public int getNumberOfTransitions() {
        return numberOfTransitions;
    }

    public void setNumberOfTransitions(int numberOfTransitions) {
        this.numberOfTransitions = numberOfTransitions;
    }

//    @Override
//    public String toString() {
//        return automata + " {" + "input: " + input + (isAccepted ? ", Accepted " : ", Rejected ") + "through " + numberOfTransitions + " transitions }";
//    }
    @Override
    public String toString() {
        return "Response{   automata=" + automata + ", signature=" + signature + ", input=" + input + "isAccepted=" + isAccepted + ", numberOfTransitions=" + numberOfTransitions + '}';

    }

}
