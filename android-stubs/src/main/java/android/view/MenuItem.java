package android.view;

import android.content.Intent;
import android.graphics.drawable.Drawable;

public interface MenuItem {
    int getItemId();
    CharSequence getTitle();
    MenuItem setTitle(CharSequence title);
    MenuItem setTitle(int title);
    MenuItem setIcon(Drawable drawable);
    MenuItem setIcon(int iconRes);
    Drawable getIcon();
    MenuItem setEnabled(boolean enabled);
    boolean isEnabled();
    MenuItem setVisible(boolean visible);
    boolean isVisible();
    MenuItem setChecked(boolean checked);
    boolean isChecked();
    MenuItem setCheckable(boolean checkable);
    boolean isCheckable();
    int getGroupId();
    int getOrder();
    MenuItem setIntent(Intent intent);
    Intent getIntent();
    MenuItem setOnMenuItemClickListener(OnMenuItemClickListener menuItemClickListener);
    MenuItem setOnActionExpandListener(OnActionExpandListener listener);
    void setShowAsAction(int actionEnum);
    MenuItem setShowAsActionFlags(int actionEnum);
    MenuItem setActionView(View view);
    MenuItem setActionView(int resId);
    MenuItem setActionProvider(ActionProvider actionProvider);
    ActionProvider getActionProvider();
    View getActionView();
    char getAlphabeticShortcut();
    char getNumericShortcut();
    MenuItem setAlphabeticShortcut(char alphaChar);
    MenuItem setNumericShortcut(char numericChar);
    MenuItem setShortcut(char numericChar, char alphaChar);
    SubMenu getSubMenu();
    boolean hasSubMenu();
    CharSequence getTitleCondensed();
    MenuItem setTitleCondensed(CharSequence title);
    ContextMenu.ContextMenuInfo getMenuInfo();
    boolean collapseActionView();
    boolean expandActionView();
    boolean isActionViewExpanded();

    public interface OnMenuItemClickListener {
        boolean onMenuItemClick(MenuItem item);
    }

    public interface OnActionExpandListener {
        boolean onMenuItemActionExpand(MenuItem item);
        boolean onMenuItemActionCollapse(MenuItem item);
    }
}
