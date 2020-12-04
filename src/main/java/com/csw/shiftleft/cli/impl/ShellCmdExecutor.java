/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csw.shiftleft.cli.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Vishal
 */
public class ShellCmdExecutor {

    public void exe(String finalArgs) {
        try {
            Process process;
            if (isWindows()) {
                process = Runtime.getRuntime().exec("cmd.exe  /c " + finalArgs);
            } else {
                process = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", finalArgs});
            }

            // error
            StringBuilder outputErr = new StringBuilder();
            BufferedReader reader1 = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()));
            String line1;
            while ((line1 = reader1.readLine()) != null) {
                outputErr.append(line1 + "\n");
            }
            System.out.println(outputErr);
            // result
            StringBuilder output = new StringBuilder();
            int exitVal = process.waitFor();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

            if (exitVal == 0) {
                System.out.println("Success!");
                System.out.println(output);
            } else {
                System.out.println("Error");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    private static boolean isWindows(){
         return System.getProperty("os.name")
                    .toLowerCase().startsWith("windows");
    }

}
