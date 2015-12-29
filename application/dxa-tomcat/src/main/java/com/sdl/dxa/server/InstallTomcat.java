package com.sdl.dxa.server;

import com.sdl.dxa.utils.InstallHelper;

import java.io.File;

/**
 * Install Tomcat
 *
 * @author nic
 */
public class InstallTomcat {

    public static void main(String[] args) throws Exception {

        if ( args.length < 2 ) {
            System.err.println("Invalid arguments. Usage: InstallTomcat [Tomcat ZIP path] [Tomcat Install Path]");
            System.exit(-1);
        }

        File tomcatZipFile = new File(args[0]);
        File installPath = new File(args[1]);

        System.out.println("Installing DXA Tomcat server at: " + installPath);
        InstallHelper.unzipFile(tomcatZipFile, installPath);
        System.out.println("Done");
    }
}
