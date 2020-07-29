/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clientserver.formalmethods.nfatodfa_conversion.pkStater;

import com.clientserver.formalmethods.nfatodfa_conversion.pkInterface.CheckerServer;
import com.clientserver.formalmethods.nfatodfa_conversion.pkInterface.Configration;

/**
 *
 * @author HP
 */
public class Starter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Configration configration = new Configration();

        Configration.addHost("clientArbitrator", "localhost");
        Configration.addPort("clientArbitrator", 3578);
        Configration.addHost("clientRegistrar", "localhost");
        Configration.addPort("clientRegistrar", 5437);
        Configration.addHost("conversionArbitrator", "localhost");
        Configration.addPort("conversionArbitrator", 3587);//3587
        //CheckerServer server = new CheckerServer(10, 4451, "localhost", "coreserver");
        CheckerServer server = new CheckerServer(10, 4458, "localhost", "coreserver");
        //CheckerServer server = new CheckerServer(10, 4523, "localhost", "coreserver");
    }

}
