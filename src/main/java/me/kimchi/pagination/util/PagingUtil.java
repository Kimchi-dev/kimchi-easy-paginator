package me.kimchi.pagination.util;

import java.io.*;
import java.net.URL;

public class PagingUtil {

    public static String readFile(URL resource) throws Exception {
        if (resource == null) {
            throw new NullPointerException("Not found URL");
        }

        String fileContent;
        StringBuilder builder = new StringBuilder();

        FileInputStream inputStream = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = new FileInputStream(resource.getFile());
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fileContent = builder.toString();
            inputStream.close();
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
