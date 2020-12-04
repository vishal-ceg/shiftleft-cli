/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.csw.shiftleft.cli.impl;

/**
 *
 * @author Vishal
 */
public class FindSecBug implements Scanner{

    @Override
    public void scan() throws Exception {
        String path = System.getProperty("user.dir");
        String finalArgs = "docker run -v " + path + ":/workdir/scan findsecbugs";
        new ShellCmdExecutor().exe(finalArgs);
    }
    
}
