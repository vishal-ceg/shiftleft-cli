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
public abstract class Scanner {

    protected final ConfigurationIn cofig;

    public Scanner(ConfigurationIn cofig) {
        this.cofig = cofig;
    }

    protected abstract String imageName();

    public abstract List<String> scan() throws Exception ;
      
    
}
