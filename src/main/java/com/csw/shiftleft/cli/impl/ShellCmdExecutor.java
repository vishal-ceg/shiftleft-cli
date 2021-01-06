/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csw.shiftleft.cli.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vishal
 */
public class ShellCmdExecutor {
    
    public ShellCmdOut exe(String finalArgs) {
        ShellCmdOut out = new ShellCmdOut();
        try {
            Process process;
            if (isWindows()) {
                process = Runtime.getRuntime().exec("cmd.exe  /c " + finalArgs);
            } else {
                process = Runtime.getRuntime().exec(new String[]{"/bin/sh", "-c", finalArgs});
            }
            
            BufferedReader reader1 = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()));
            String line1;
            while ((line1 = reader1.readLine()) != null) {
                out.addErrorMsgList(line1);
            }
            // result
            int exitVal = process.waitFor();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            
            String line;
            while ((line = reader.readLine()) != null) {
                out.addSuccessMsg(line);
            }
            if (exitVal == 0) {
                out.success(true);
            }
            
        } catch (IOException | InterruptedException e) {
            out.success(false);
            e.printStackTrace();
        }
        return out;
    }
    
    private static boolean isWindows() {
        return System.getProperty("os.name")
                .toLowerCase().startsWith("windows");
    }
    
    public static class ShellCmdOut {
        
        private boolean success = true;
        private List<String> errorMsgList = new ArrayList();
        private List<String> successMsgList = new ArrayList();
        
        public List<String> getErrorMsgList() {
            return errorMsgList;
        }
        
        public void addErrorMsgList(String msg) {
            this.success = false;
            this.errorMsgList.add(msg);
        }
        
        public List<String> getSuccessMsgList() {
            return successMsgList;
        }
        
        public void addSuccessMsg(String msg) {
            this.successMsgList.add(msg);
        }
        
        public boolean success() {
            return success;
        }
        
        public void success(boolean success) {
            this.success = success;
        }
        
        public List<String> getAndPrint() {
            if (success) {
                successMsgList.stream().forEach((s) -> {
                    System.out.println(s);
                });
                return successMsgList;
            }
            errorMsgList.stream().forEach((s) -> {
                System.out.println(s);
            });
            return errorMsgList;
        }
        
    }
    
}
