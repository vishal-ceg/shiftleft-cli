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
public class DependencyCheck extends Scanner {

    public DependencyCheck(ConfigurationIn cofig) {
        super(cofig);
    }
  
    @Override
    public void scan() throws Exception {
        cofig.projectRoot = cofig.projectRoot == null ? System.getProperty("user.dir") : cofig.projectRoot;
        System.out.println(cofig.projectRoot);
        String finalArgs = "docker run -v " + cofig.projectRoot + ":/src --volume "+cofig.projectRoot+"\\data:/usr/share/dependency-check/data owasp --project project_name";
        new ShellCmdExecutor().exe(finalArgs);
    }

}
