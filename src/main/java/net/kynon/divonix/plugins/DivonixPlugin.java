package net.kynon.divonix.plugins;

public abstract class DivonixPlugin {

    protected PluginProperty property;

    public abstract void onEnable();

    public PluginProperty getProperty() {
        return property;
    }
    public void setProperty(PluginProperty property) {
        this.property = property;
    }
}
