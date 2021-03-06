/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csw.shiftleft.cli.impl;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vishal
 */
public class DependencyCheck extends Scanner {

    public DependencyCheck(ConfigurationIn cofig) {
        super(cofig);
    }
    
    @Override
    protected String imageName() {
        return "vishalceg/owasp";
    }

    @Override
    public List<String> scan() throws Exception {
        System.out.println("Workdir-"+cofig.projectRoot);
        List<String> list = new ArrayList<>();
        System.out.println("Pulling latest image....");
        list.add("Pulling latest image....");
        ShellCmdExecutor.ShellCmdOut out = new ShellCmdExecutor().exe("docker pull " + imageName());
        list.addAll(out.getAndPrint());
        if (!out.success()) {
            return list;
        }
        cofig.projectRoot = cofig.projectRoot == null ? System.getProperty("user.dir") : cofig.projectRoot;
        String finalArgs = "docker run -v " + cofig.projectRoot + ":/src --volume " + cofig.projectRoot + "\\data:/usr/share/dependency-check/data " + imageName() + " --project project_name";
        ShellCmdExecutor.ShellCmdOut out2 = new ShellCmdExecutor().exe(finalArgs);
        list.addAll(out2.getAndPrint());
        return list;
    }

    

}
