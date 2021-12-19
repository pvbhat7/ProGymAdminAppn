package com.progym.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;

public class Identity {

    public static String getMacAddress(){

        String macAddress = "";
        if(!ProjectContext.MAC.isEmpty())
            macAddress = ProjectContext.MAC;
        else
        {
            InetAddress ip;
            try {

                ip = InetAddress.getLocalHost();

                NetworkInterface network = NetworkInterface.getByInetAddress(ip);

                byte[] mac = network.getHardwareAddress();

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < mac.length; i++) {
                    sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                }
                macAddress = sb.toString();
                ProjectContext.MAC = macAddress;

            } catch (Exception e){
                e.printStackTrace();
            }
        }

        return macAddress;
    }

    public static String getIpAddress(){

        String ipAddress =  "";

        InetAddress ip;
        try {

            ip = InetAddress.getLocalHost();
            ipAddress = ip.getHostAddress();

        } catch (UnknownHostException e) {

            e.printStackTrace();

        }
        return ipAddress;
    }

    public static String getSerialNumber(){

        String serialNumber = "";
        try
        {
            String result = null;
            Process p = Runtime.getRuntime().exec("wmic baseboard get serialnumber");
            BufferedReader input
                    = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null)
            {
                result += line;
            }
            if (result.equalsIgnoreCase(" ")) {
                System.out.println("Result is empty");
            } else
            {
                serialNumber = result;
            }
            input.close();
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

        // returning the serial number
        return serialNumber;
    }

}
