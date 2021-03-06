package settings;

import org.ini4j.Ini;
import org.ini4j.IniPreferences;
import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.prefs.Preferences;

public final class Settings {

    public static final File SAVE_DIR = new File("saves/");
    public static final String SAVE_FILE_EXT = "myfin";

    public static final String FORMAT_AMOUNT = "%.2f";
    public static final String FORMAT_RATE = "%.4f";
    public static final String FORMAT_DATE = "dd.MM.yyyy";
    public static final String FORMAT_DATE_MONTH = "MMMM yyyy";
    public static final String FORMAT_DATE_YEAR = "yyyy";
    public static final SimpleDateFormat PARSER_DATE = new SimpleDateFormat(FORMAT_DATE);

    public static final String[] CURRENCIES_CODES = {"BLR", "USD", "EUR", "RUB", "UAH"};

    private static final File FILE_SETTINGS = new File("saves/settings.ini");
    private static File fileSave = new File("saves//default.myfin");
    private static String programLanguage = "ru";

    public static void init() {
        try {
            Ini ini = new Ini(FILE_SETTINGS);
            Preferences prefs = new IniPreferences(ini);
            String file = prefs.node("Settings").get("fileSave", null);
            String language = prefs.node("Settings").get("programLanguage",null);
            if (language != null){
                programLanguage = language;
            }
            if (file != null) {
                fileSave = new File(file);
            }
            setLocale();
        } catch (IOException e) {
            save();
        }
    }

    public static String getProgramLanguage() {
        return programLanguage;
    }

    public static void setProgramLanguage(String programLanguage) {
        Settings.programLanguage = programLanguage;
        setLocale();
        save();
    }

    private static void setLocale() {
        if("ru".equals(programLanguage)){
            Locale.setDefault(new Locale("ru"));
        } else {
            Locale.setDefault(new Locale("en"));
        }
    }

    public static File getFileSave() {
        return fileSave;
    }

    public static void setFileSave(File fileSave) {
        Settings.fileSave = fileSave;
        save();
    }

    private static void save() {
        Wini ini;
        try {
            ini = new Wini(FILE_SETTINGS);
            if (fileSave != null) {

                //check path for checking
                ini.put("Settings", "fileSave", fileSave.getPath().replace("\\", "\\\\"));
            }
            ini.store();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
