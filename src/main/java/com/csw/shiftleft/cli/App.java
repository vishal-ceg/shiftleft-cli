/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csw.shiftleft.cli;

import com.csw.shiftleft.cli.impl.ConfigurationIn;
import com.csw.shiftleft.cli.impl.Scanner;
import com.csw.shiftleft.cli.impl.ScannerFactory;
import java.util.concurrent.Callable;
import picocli.CommandLine;

/**
 *
 * @author Vishal
 */
@CommandLine.Command(name = "shiftleft", description = "Performs operations")
public class App implements Callable<String> {

    @CommandLine.Option(names = {"-v", "--version"}, description = "version")
    boolean version = false;

    @CommandLine.Option(names = {"-p", "--path"}, description = "Sorce path")
    String path;

    public static void main(String... args) throws Exception {
        int exitCode = new CommandLine(new App()).execute(args);
         System.exit(exitCode);
    }

    @Override
    public String call() throws Exception {
        if (version) {
            System.out.println("Version: 1.0");
        } else {
            ConfigurationIn config = new ConfigurationIn();
            config.projectRoot = path;
            Scanner scanner = ScannerFactory.create(config);
            scanner.scan();
        }

        return "success";
    }

}
