package net.kynon.divonix.plugins;

public class PluginProperty {

    private String main;
    private String name;
    private String description;
    private double version;

    public PluginProperty(String main, String name, String description, double version) {
        this.main = main;

        this.name = name;
        this.description = description;
        this.version = version;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }
}
