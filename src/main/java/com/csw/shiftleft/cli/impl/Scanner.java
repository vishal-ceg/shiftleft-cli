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
public abstract class Scanner {
     protected final ConfigurationIn cofig;
     
     public Scanner(ConfigurationIn cofig){
         this.cofig = cofig;
     }
    
    public abstract void scan() throws Exception;
}
