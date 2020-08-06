package lib.units;

public enum Language {
    CPP("C++"), JAVA("Java"), PYTHON("Python"), UNKNOWN("Unknown");
    private final String name;
    private Language(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public static Language getLanguage(String name) {
        if (name.equals("C++")) {
            return CPP;
        } else if (name.equals("Java")) {
            return JAVA;
        } else if (name.equals("Python")) {
            return PYTHON;
        } else {
            return UNKNOWN;
        }
    }
}
