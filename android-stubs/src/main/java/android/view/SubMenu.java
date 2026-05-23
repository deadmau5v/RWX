package android.view;

public interface SubMenu extends Menu {
    MenuItem getItem();
    SubMenu setHeaderIcon(int iconRes);
    SubMenu setIcon(int iconRes);
}
