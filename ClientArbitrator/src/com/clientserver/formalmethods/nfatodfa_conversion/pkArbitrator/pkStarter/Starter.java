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
        Configration configration = new Configration();
        Configration.addHost("clientArbitrator", "localhost");
        Configration.addPort("clientArbitrator", 3578);
        Configration.addHost("clientRegistrar", "localhost");
        Configration.addPort("clientRegistrar", 5437);

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
