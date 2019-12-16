package settings;


import javafx.scene.image.Image;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

public final class Style {

    public static final Color COLOR_BUTTON_BG_NORMAL = new Color(240, 240, 240);
    public static final Color COLOR_BUTTON_BG_MOVER = Color.YELLOW;

    public static Font FONT_BUTTON_TOLBAR = new Font("Kornilow", Font.BOLD, 14);
    public static Font FONT_MAIN_BUTTON = new Font("Kornilow", Font.BOLD, 14);

    public static final EmptyBorder BORDER_PANEL = new EmptyBorder(10, 10, 10, 10); //пустая рамка


    public static final Image ICON_TOOLBAR_OVERVIEW = new Image("src/images/overview.png");
    public static final Image ICON_TOOLBAR_ACCOUNTS = new Image("src/images/accounts.png");
    public static final Image ICON_TOOLBAR_ARTICLES = new Image("src/images/articles.png");
    public static final Image ICON_TOOLBAR_TRANSACTIONS = new Image("src/images/transactions.png");
    public static final Image ICON_TOOLBAR_CURRENCIES = new Image("src/images/currencies.png");
    public static final Image ICON_TOOLBAR_STATISTICS = new Image("src/images/statistics.png");
}
