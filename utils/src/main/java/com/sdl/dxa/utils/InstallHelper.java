package com.sdl.dxa.utils;

import org.apache.commons.lang.SystemUtils;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Install Helper
 *
 * @author nic
 */
public abstract class InstallHelper {


    static Pattern MAVEN_DOWNLOAD_STATUS_PATTERN = Pattern.compile("[0-9]+/[0-9]+ KB.*");

    public static void executeCommand(String command, File directory) throws IOException {

        String cmdPrefix = "";
        if (SystemUtils.IS_OS_WINDOWS ) {
            cmdPrefix = "cmd /c ";
        }
        Process process = Runtime.getRuntime().exec(cmdPrefix + command, null, directory);
        BufferedReader reader = new BufferedReader (new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine ()) != null) {
            if ( ! MAVEN_DOWNLOAD_STATUS_PATTERN.matcher(line).matches() ) {
                System.out.println(line);
            }
        }
    }

    public static void downloadFile(URL downloadUrl, File destination) throws IOException {
        System.out.println("Downloading file from URL: " + downloadUrl + " to destination: " + destination);
        Files.copy(downloadUrl.openStream(), destination.toPath());
    }

    public static void unzipFile(File zipFile, File directory) throws IOException {
        byte[] buffer = new byte[1024];

        System.out.println("Unzipping " + zipFile + " ...");

        // Create output directory is not exists
        //
        if (!directory.exists()) {
            directory.mkdir();
        }

        // Get the zip file content
        //
        ZipInputStream zis =
                new ZipInputStream(new FileInputStream(zipFile));

        // Get the zipped file list entry
        //
        ZipEntry ze = zis.getNextEntry();

        while (ze!=null) {
            String fileName = ze.getName();
            File newFile = new File(directory + File.separator + fileName);

            System.out.println("  " + newFile.getAbsoluteFile());

            // Create all non exists folders
            // else you will hit FileNotFoundException for compressed folder
            //
            if (ze.isDirectory()) {
                newFile.mkdirs();
            }
            else {
                newFile.getParentFile().mkdirs();
                newFile.createNewFile();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
            }
            ze = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();

        System.out.println("Done");
    }
}
