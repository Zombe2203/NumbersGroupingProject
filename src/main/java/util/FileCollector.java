package util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.GZIPInputStream;

public class FileCollector {

    public static void collectFile() {
        String urlString = "https://github.com/PeacockTeam/new-job/releases/download/v1.0/lng-4.txt.gz";
        String outputFilePath = "./inputFile.txt";

        try {
            URL url = new URL(urlString);
            InputStream urlInputStream = url.openStream();
            GZIPInputStream gzipInputStream = new GZIPInputStream(urlInputStream);

            FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath);

            byte[] buffer = new byte[1024];
            int length;

            while ((length = gzipInputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, length);
            }

            urlInputStream.close();
            gzipInputStream.close();
            fileOutputStream.close();

            System.out.println("Extraction finished");
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

}
