package com.sdl.dxa.server;

import java.io.File;
import org.apache.catalina.startup.Tomcat;

/**
 * DXA Embedded Tomcat Server.
 *
 * Handy embedded Tomcat server which can be used to run DXA within an IDE.
 * To set the server, do the following:
 * 1. Create a Java application run configuration
 * 2. Define main class: com.sdl.dxa.server.Main
 * 3. Use the following VM options: -Xms512m -Xmx1024m -XX:MaxPermSize=128M -Dfile.encoding=UTF8 -Djava.awt.headless=true
 * 4. Set working directory to: dxa-kickstart/application/dxa-tomcat/target
 */
public class Main {

    public static void main(String[] args) throws Exception {

        // The location of the DXA webapp. By default use the target directory of the dxa-demo-webapp project
        //
        String webappDirLocation = System.getenv("WEBAPP_DIR_LOCATION");
        if (webappDirLocation == null || webappDirLocation.isEmpty() ) {
            webappDirLocation = "../../dxa-demo-webapp/target/dxa-demo-webapp";
        }
        Tomcat tomcat = new Tomcat();
        //tomcat.setBaseDir();
        //The port that we should run on can be set into an environment variable
        //Look for that variable and default to 8080 if it isn't there.
        //
        String webPort = System.getenv("PORT");
        if(webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }
        tomcat.setPort(Integer.valueOf(webPort));

        tomcat.addWebapp("", new File(webappDirLocation).getAbsolutePath());
        System.out.println("configuring app with basedir: " + new File("./" + webappDirLocation).getAbsolutePath());
        System.out.println("Starting DXA...");
        tomcat.start();
        tomcat.getServer().await();
    }
}