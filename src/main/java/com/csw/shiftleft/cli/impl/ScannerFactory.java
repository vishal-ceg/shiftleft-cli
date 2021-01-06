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
public class ScannerFactory {
    
    public static Scanner create(ConfigurationIn cofig){
        switch(cofig.scannerType){
            case SAST:
                return new FindSecBug(cofig);
            case DAST:
                return new DependencyCheck(cofig);
            default:
                throw new RuntimeException("Not handled-"+cofig.scannerType);
        }
    } 
}
