package net.kynon.divonix.config;

import net.kynon.divonix.Main;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ConfigFile {

    public static void loadConfig() throws FileNotFoundException {
        String filename = "divonix_config.yml";
        ClassLoader classLoader = Main.class.getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream(filename)) {
            String result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
                BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
                bw.write(result);
                bw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
