/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clientserver.formalmethods.nfatodfa_conversion.pkApplicationInterface.pkStarter;

import com.clientserver.formalmethods.nfatodfa_conversion.pkApplicationInterface.Configration;
import com.clientserver.formalmethods.nfatodfa_conversion.pkApplicationInterface.ConversionServer;

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
        Configration.addHost("conversionArbitrator", "localhost");
        Configration.addPort("conversionArbitrator", 3587);
        Configration.addHost("conversionRegistrar", "localhost");
        Configration.addPort("conversionRegistrar", 5473);

        ConversionServer conversionServer = new ConversionServer(10, 2223, "localhost", "Conversion Server1");
        //  ConversionServer conversionServer = new ConversionServer(10, 2354, "localhost", "ConversionServer1");

    }

}
