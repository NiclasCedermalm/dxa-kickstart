package com.sdl.dxa.install;

import com.sdl.dxa.utils.InstallHelper;

import java.io.*;

/**
 * Install DXA Source
 *
 * @author nic
 */
public class InstallDXASource {

    public static void main(String[] args) throws Exception {

        if ( args.length < 5 ) {
            System.err.println("Invalid arguments. Usage: InstallDXASource [source dir] [DXA GIT repo] [DXA branch] [DXA Modules GIT repo] [DXA Modules branch]");
            System.exit(-1);
        }
        File sourceDir = new File(args[0]);
        if ( !sourceDir.exists() ) {
            sourceDir.mkdirs();
        }
        String dxaGitRepo = args[1];
        String dxaBranch = args[2];
        String dxaModulesGitRepo = args[3];
        String dxaModulesBranch = args[4];

        // DXA
        //
        System.out.println("Install DXA sources from GIT in source dir: " + sourceDir);
        InstallHelper.executeCommand("git clone -b " + dxaBranch + " " + dxaGitRepo, sourceDir);
        System.out.println("Done.");
        System.out.println("Compiling & Installing DXA sources...");
        InstallHelper.executeCommand("mvn install", new File(sourceDir, "dxa-web-application-java"));
        System.out.println("Done.");

        // DXA Modules
        //
        System.out.println("Install DXA Modules sources from GIT in source dir: " + sourceDir);
        InstallHelper.executeCommand("git clone -b " + dxaModulesBranch + " " + dxaModulesGitRepo, sourceDir);
        System.out.println("Done.");
        System.out.println("Compiling & Installing DXA sources...");
        InstallHelper.executeCommand("mvn install", new File(sourceDir, "dxa-modules/webapp-java"));
        System.out.println("Done.");
    }

}
