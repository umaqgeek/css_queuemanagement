/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import main.RMIConnector;

/**
 *
 * @author umarmukhtar
 */
public class DBConnQueue {
    
    private static String hostname = "10.73.32.200";
    private static int port = 1099;
    
    public boolean setQuery(String query) {
        boolean status = false;
        try {
            RMIConnector rmic = new RMIConnector();
            status = rmic.setQuerySQL(DBConnQueue.getHostname(), DBConnQueue.getPort(), query);
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            status = false;
        }
        return status;
    }
    
    public ArrayList<ArrayList<String>> getQuery(String query) {
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        try {
            RMIConnector rmic = new RMIConnector();
            data = rmic.getQuerySQL(DBConnQueue.getHostname(), DBConnQueue.getPort(), query);
        } catch (Exception e) {
            System.out.println("Error: "+e.getMessage());
            data.removeAll(data);
        }
        return data;
    }
    
    public static void setConfigQueue(String hostname, int port) {
        setHostname(hostname);
        setPort(port);
    }

    public static String getHostname() {
        return hostname;
    }

    public static void setHostname(String aHostname) {
        hostname = aHostname;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int aPort) {
        port = aPort;
    }
}
