package android.view;

import android.content.ComponentName;
import android.content.Intent;

public interface Menu {
    MenuItem add(CharSequence title);
    MenuItem add(int titleRes);
    MenuItem add(int groupId, int itemId, int order, CharSequence title);
    default MenuItem add(int groupId, int itemId, int order, String title) { return add(groupId, itemId, order, (CharSequence) title); }
    MenuItem add(int groupId, int itemId, int order, int titleRes);
    int addIntentOptions(int groupId, int itemId, int order, ComponentName caller, Intent[] specifics, Intent intent, int flags, MenuItem[] outSpecificItems);
    SubMenu addSubMenu(CharSequence title);
    SubMenu addSubMenu(int titleRes);
    SubMenu addSubMenu(int groupId, int itemId, int order, CharSequence title);
    SubMenu addSubMenu(int groupId, int itemId, int order, int titleRes);
    void clear();
    void close();
    MenuItem findItem(int id);
    MenuItem getItem(int index);
    boolean hasVisibleItems();
    boolean isShortcutKey(int keyCode, KeyEvent event);
    boolean performIdentifierAction(int id, int flags);
    boolean performShortcut(int keyCode, KeyEvent event, int flags);
    void removeGroup(int groupId);
    void removeItem(int id);
    void setGroupCheckable(int group, boolean checkable, boolean exclusive);
    void setGroupEnabled(int group, boolean enabled);
    void setGroupVisible(int group, boolean visible);
    default void setHeaderTitle(CharSequence title) {}
    default void setHeaderTitle(int titleRes) {}
    void setQwertyMode(boolean isQwerty);
    int size();
}
