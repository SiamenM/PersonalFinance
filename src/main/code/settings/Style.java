package settings;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public final class Style {

    public static final Color COLOR_BUTTON_BG_NORMAL = new Color(240, 240, 240);
    public static final Color COLOR_BUTTON_BG_MOVER = Color.YELLOW;

    public static Font FONT_BUTTON_TOLBAR = new Font("Kornilow", Font.BOLD, 14);
    public static Font FONT_MAIN_BUTTON = new Font("Kornilow", Font.BOLD, 14);

    public static final EmptyBorder BORDER_PANEL = new EmptyBorder(10, 10, 10, 10); //пустая рамка

    public static final ImageIcon ICON_MAIN = new ImageIcon("src/images/main.png");
    public static final ImageIcon ICON_TOOLBAR_OVERVIEW = new ImageIcon("src/images/overview.png");
    public static final ImageIcon ICON_TOOLBAR_ACCOUNTS = new ImageIcon("src/images/accounts.png");
    public static final ImageIcon ICON_TOOLBAR_ARTICLES = new ImageIcon("src/images/articles.png");
    public static final ImageIcon ICON_TOOLBAR_TRANSACTIONS = new ImageIcon("src/images/transactions.png");
    public static final ImageIcon ICON_TOOLBAR_CURRENCIES = new ImageIcon("src/images/currencies.png");
    public static final ImageIcon ICON_TOOLBAR_STATISTICS = new ImageIcon("src/images/statistics.png");
}
