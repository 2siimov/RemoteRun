package lib.config;

import lib.units.Language;

public class CompileConfig {
    public Language language;
    public String filePath;
    public CompileConfig(Language language, String filePath) {
        this.language = language;
        this.filePath = filePath;
    }
    public CompileConfig(Language language) {
        this.language = language;
        this.filePath = "Not Set";
    }
    public CompileConfig() {
        this.language = Language.UNKNOWN;
        this.filePath = "Not Set";
    }
}
