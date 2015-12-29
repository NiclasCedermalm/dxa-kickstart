package com.sdl.dxa.install;

import com.sdl.dxa.utils.InstallHelper;

import java.io.File;
import java.net.URL;

/**
 * DownloadDXADistribution
 *
 * @author nic
 */
public class DownloadDXADistribution {

    public static void main(String[] args) throws Exception {

        if ( args.length < 2 ) {
            System.err.println("Invalid arguments. Usage: DownloadDXADistribution [install dir] [DXA download URL]");
            System.exit(-1);
        }
        File installDir = new File(args[0]);
        if ( !installDir.exists() ) {
            installDir.mkdirs();
        }
        URL downloadUrl = new URL(args[1]);
        File dxaZipFile = new File(installDir, "dxa-dist.zip");
        File dxaInstallDir = new File(installDir, "dxa-dist");

        System.out.println("Downloading DXA distribution...");
        InstallHelper.downloadFile(downloadUrl, dxaZipFile);
        InstallHelper.unzipFile(dxaZipFile, dxaInstallDir);

        System.out.println("Install DXA JAR dependencies...");

        // Install JAR supplied by the DXA dist
        //
        InstallHelper.executeCommand("mvn install", new File(dxaInstallDir, "web/tridion-libs"));

        // Re-install the ua-parser JAR as it get's installed incorrectly by the install POM above
        //
        InstallHelper.executeCommand("mvn -q install:install-file -DgroupId=ua_parser -DartifactId=ua-parser -Dversion=1.2.2 -Dpackaging=jar -Dfile=ua-parser-1.2.2.jar",
                new File(dxaInstallDir, "web/tridion-libs/7.1.0"));

        System.out.println("Done");

    }

}

