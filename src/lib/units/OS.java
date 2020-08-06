package lib.units;

public enum OS {
    MACOS("macOS"), WINDOWS("Windows"), LINUX("Linux");
    private final String name;
    private OS(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
}
