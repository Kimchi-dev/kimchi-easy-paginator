package com.github.kimchidev.pagination.util;

import java.io.*;
import java.net.URL;

public class PagingUtil {

    public static String readFile(ClassLoader loader, String path) throws Exception {
        if (loader == null) {
            throw new NullPointerException("Not found Loader.");
        }

        String fileContent;
        StringBuilder builder = new StringBuilder();

        InputStream resourceAsStream = null;
        BufferedReader bufferedReader = null;
        try {
            resourceAsStream = loader.getResourceAsStream(path);
            bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
            String line;
            while((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fileContent = builder.toString();
            resourceAsStream.close();
            bufferedReader.close();
        }

        return fileContent;
    }

    public static void writeFile(String helperPage, String filePath, String extension)  throws Exception {
        FileWriter writer = null;

        try {
            File file = new File(filePath);
            if ( ! file.isDirectory()) {
                String nonLastPath = filePath.replace(filePath.substring(filePath.lastIndexOf("/")), "");
                File onlyPath = new File(nonLastPath);
                onlyPath.mkdirs();
            } else {
                file.mkdirs();
                file = new File(filePath + "/result."+extension);
            }

            writer = new FileWriter(file);
            writer.write(helperPage);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }

    }
}
