package kr.devis.util.offsetpaginator.util;

import kr.devis.util.offsetpaginator.constant.PagingExceptConstant;
import kr.devis.util.offsetpaginator.exception.PagingException;

import java.io.*;

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
            String lastDepth = filePath.substring(filePath.lastIndexOf('/'));
            File fileOrDir = null;
            //== Not Directory ==//
            if(lastDepth.contains(".")) {
                String directoryPath = filePath.replace(lastDepth, "");
                fileOrDir = new File(directoryPath);
            }
            //== Directory ==//
            else {
                fileOrDir = new File(filePath);
                filePath = filePath + "/result."+extension;
            }

            fileOrDir.mkdirs();
            fileOrDir = new File(filePath);

            writer = new FileWriter(fileOrDir);
            writer.write(helperPage);
        } catch (Exception e) {
            if(e instanceof FileNotFoundException) {
                throw new PagingException(PagingExceptConstant.ALREADY_EXIST_FILE);
            }

            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

    }
}
