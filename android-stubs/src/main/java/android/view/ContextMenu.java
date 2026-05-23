package android.view;

public interface ContextMenu extends Menu {
    ContextMenu setHeaderIcon(int iconRes);
    ContextMenu setHeaderView(View view);
    void clearHeader();

    public interface ContextMenuInfo {}
}
