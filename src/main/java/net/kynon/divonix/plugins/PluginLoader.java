package net.kynon.divonix.plugins;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;

public class PluginLoader {

    private static PluginProperty loadPluginProperties(File file) throws IOException {
        URL url = file.toURI().toURL();
        String jarURL = "jar:" + url +"!/plugin.properties";

        InputStream input;
        URL inputURL = new URL(jarURL);
        JarURLConnection conn = (JarURLConnection)inputURL.openConnection();
        input = conn.getInputStream();

        Properties property = new Properties();

        property.load(input);

        String main = property.getProperty("main");
        String name = property.getProperty("name");
        String description = property.getProperty("description");
        String version = property.getProperty("version");

        return new PluginProperty(main, name, description, version);
    }

    public static DivonixPlugin loadPlugin(File file) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        if(!file.exists()) {
            return null;
        }

        PluginProperty property = loadPluginProperties(file);

        URL url = file.toURI().toURL();
        String jarURL = "jar:" + url + "!/";
        URL urls[] = {new URL(jarURL)};
        URLClassLoader ucl = new URLClassLoader(urls);
        DivonixPlugin plugin = (DivonixPlugin) Class.forName(property.getMain(), true, ucl).getDeclaredConstructor().newInstance();
        plugin.setProperty(property);

        return plugin;
    }
}
