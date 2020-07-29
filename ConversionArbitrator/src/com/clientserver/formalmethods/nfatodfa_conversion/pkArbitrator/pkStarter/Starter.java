/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.clientserver.formalmethods.nfatodfa_conversion.pkArbitrator.pkStarter;

import com.clientserver.formalmethods.nfatodfa_conversion.pkArbitrator.Arbitrator;
import com.clientserver.formalmethods.nfatodfa_conversion.pkRegistration.Configration;
import com.clientserver.formalmethods.nfatodfa_conversion.pkRegistration.Registrar;

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
        Configration.addHost("conversionLoader", "localhost");
        Configration.addPort("conversionLoader", 2587);
        Thread arbitrator = new Thread(new Runnable() {
            @Override
            public void run() {
                Arbitrator arbitrator = Arbitrator.getInstance();

            }
        });
        Thread registrar = new Thread(new Runnable() {
            @Override
            public void run() {
                Registrar registrar = Registrar.getInstance();
            }
        });

        arbitrator.start();
        registrar.start();
    }

}
