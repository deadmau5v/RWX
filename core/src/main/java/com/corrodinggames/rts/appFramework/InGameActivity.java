package com.corrodinggames.rts.appFramework;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.corrodinggames.rts.R;
import com.corrodinggames.rts.game.units.custom.logicBooleans.VariableScope;
import com.corrodinggames.rts.gameFramework.GameEngine;
import com.corrodinggames.rts.gameFramework.GameMode;
import com.corrodinggames.rts.gameFramework.Utility;
import com.corrodinggames.rts.gameFramework.local.Locale;

/**
 * Main in-game Activity for the RWX game on Android.
 *
 * Decompiled from game-lib.jar (JADX: class "g", 431 lines).
 * Restored to use real Android APIs instead of the desktop shim layer.
 *
 * Key JADX→Android API mappings applied:
 *   a(Intent)                           → startActivity(Intent)
 *   a(Intent, int, Bundle)              → startActivityForResult(Intent, int, Bundle)
 *   a(int)                              → startActivityForResult(null, int) [for save game]
 *   a(Class<?>, int)                    → startActivityForResult(new Intent(this, Class), int)
 *   b()                                 → finish() (when called as activity method)
 *   handler.a(Runnable)                 → handler.post(Runnable)
 *   Looper.b()                          → Looper.getMainLooper()
 *   VariableScope.nullOrMissingString   → ""
 *   gameEngine.networkEngine.m(String)  → sendChatMessage (public chat)
 *   gameEngine.networkEngine.l(String)  → sendTeamChatMessage (team chat)
 *   gameEngine.networkEngine.H()        → showPlayersDialog()
 *   gameEngine.networkEngine.ag()       → returnToBattleroom()
 *   gameEngine.networkEngine.m("-surrender") → surrender()
 *   a(0)                                → startActivityForResult for save game
 *
 * Original JADX info:
 *   renamed from: com.corrodinggames.rts.appFramework.g
 *   loaded from: game-lib.jar:com/corrodinggames/rts/appFramework/g.class
 */
public class InGameActivity extends TaskQueueActivity {

    /** The game view (rendering surface). JADX field name: gameView */
    GameView gameView;

    /** Progress dialog for loading operations. JADX field name: progressDialog */
    ProgressDialog progressDialog;

    /** Main thread handler. JADX field name: handler — was new Handler(Looper.b()) */
    Handler handler = new Handler(Looper.getMainLooper());

    /** Whether the game is currently running. JADX field name: running */
    boolean running = true;

    // ──────────────────────────────────────────────────────────
    // Activity Lifecycle
    // ──────────────────────────────────────────────────────────

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.running = false;
    }

    /**
     * Finish the activity with immersive mode reset.
     * JADX: b() → finish()
     * Original called AppFrameworkUtils.onActivitySetContentView(this, true) before finish.
     */
    @Override
    public void finish() {
        AppFrameworkUtils.onActivitySetContentView(this, true);
        super.finish();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            AppFrameworkUtils.onActivityNewIntent(this, true, true);
            if (this.gameView != null) {
                this.gameView.resume(true);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (this.gameView != null) {
            this.gameView.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (this.gameView != null) {
            this.gameView.onResume();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        AppFrameworkUtils.onActivityNewIntent(this, false, false);
    }

    // ──────────────────────────────────────────────────────────
    // Options Menu
    // ──────────────────────────────────────────────────────────

    /**
     * JADX: a(Menu) → onCreateOptionsMenu(Menu)
     * Original used Locale.get() for localized strings and R.drawable for icons.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        GameEngine gameEngine = GameEngine.getInstance();
        if (gameEngine.isNetworkGameActive()) {
            menu.add(0, 2, 0, Locale.get("menus.ingame.settings", new Object[0]))
                    .setIcon(android.R.drawable.ic_menu_preferences);
            menu.add(0, 13, 0, Locale.get("menus.ingame.chat", new Object[0]))
                    .setIcon(android.R.drawable.ic_menu_send);
            menu.add(0, 16, 0, Locale.get("menus.ingame.teamChat", new Object[0]));
            menu.add(0, 4, 0, Locale.get("menus.ingame.toggleReady", new Object[0]))
                    .setIcon(android.R.drawable.ic_menu_send);
            menu.add(0, 14, 0, Locale.get("menus.ingame.players", new Object[0]));
            menu.add(0, 5, 0, Locale.get("menus.ingame.restart", new Object[0]))
                    .setIcon(android.R.drawable.ic_menu_sort_by_size);
            if (gameEngine.playerTeam != null && gameEngine.playerTeam.isTeamWipedOut) {
                // Skip surrender if team is wiped out
            } else if (!gameEngine.isTouchDown) {
                menu.add(0, 19, 0, Locale.get("menus.ingame.surrender", new Object[0]))
                        .setIcon(android.R.drawable.ic_lock_power_off);
            }
            if (!gameEngine.networkEngine.isServer) {
                menu.add(0, 10, 0, Locale.get("menus.ingame.disconnect", new Object[0]))
                        .setIcon(android.R.drawable.ic_lock_power_off);
            } else {
                menu.add(0, 10, 0, Locale.get("menus.ingame.exitGame", new Object[0]))
                        .setIcon(android.R.drawable.ic_lock_power_off);
            }
        } else {
            if (gameEngine.missionEngine != null && gameEngine.missionEngine.h != null) {
                menu.add(0, 11, 0, Locale.get("menus.ingame.briefing", new Object[0]))
                        .setIcon(android.R.drawable.ic_dialog_info);
            }
            menu.add(0, 15, 0, Locale.get("menus.ingame.exitGame", new Object[0]))
                    .setIcon(android.R.drawable.ic_lock_power_off);
        }
        if (gameEngine != null && gameEngine.settingsEngine.allowGameRecording) {
            if (!gameEngine.isServer) {
                menu.add(0, 9, 0, "Start Recording");
                return true;
            }
            menu.add(0, 9, 0, "Stop Recording");
            return true;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        selectMenuOption(item.getItemId());
        return true;
    }

    /**
     * Handle menu option selection.
     * Posts to main thread then dispatches to onSelectMenuOption().
     * JADX: selectMenuOption(int) → c(int)
     */
    public void selectMenuOption(final int id) {
        GameEngine.log("outer selectMenuOption: " + id);
        this.handler.post(new Runnable() {
            @Override
            public void run() {
                GameEngine.log("inner selectMenuOption: " + id);
                InGameActivity.this.onSelectMenuOption(id);
            }
        });
    }

    /**
     * Dispatch menu selections.
     * JADX: onSelectMenuOption(int) → d(int)
     * Desktop override is in JavaInGameActivity which delegates to ScriptEngine/LibRocket.
     * On Android, this is the native Android UI path.
     */
    public void onSelectMenuOption(int id) {
        switch (id) {
            case 2: // Settings
                startActivityForResult(new Intent(this, SelectFolderActivity.class), 0);
                break;
            case 3: // Skip
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Skip?")
                        .setMessage("Are you sure you want to skip this level?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                GameEngine.getInstance().isPinching = true;
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                break;
            case 4: // Toggle Ready
                GameEngine.getInstance().isGameEngineReady = !GameEngine.getInstance().isGameEngineReady;
                break;
            case 5: // Restart
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Restart?")
                        .setMessage("Are you sure you want to restart this level?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                GameEngine gameEngine = GameEngine.getInstance();
                                gameEngine.stopGameThread();
                                gameEngine.loadGame(true, GameMode.normal);
                                gameEngine.startGameThread();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                break;
            case 6: // Toggle Network
                GameEngine gameEngine = GameEngine.getInstance();
                gameEngine.isNetworkGameActive = !gameEngine.isNetworkGameActive;
                break;
            case 9: // Recording toggle
                GameEngine gameEngine2 = GameEngine.getInstance();
                if (!gameEngine2.isServer) {
                    gameEngine2.isServer = true;
                } else {
                    gameEngine2.isServer = false;
                }
                break;
            case 10: // Disconnect/Exit
                handleDisconnectOrExit();
                break;
            case 11: // Briefing
                GameEngine gameEngine4 = GameEngine.getInstance();
                if (gameEngine4.missionEngine != null && gameEngine4.missionEngine.h != null) {
                    gameEngine4.showMessageBox("Briefing", gameEngine4.missionEngine.h);
                }
                break;
            case 12: // Save Game
                handleSaveGame();
                break;
            case 13: // Chat
                showChatDialog(false);
                break;
            case 14: // Players
                GameEngine gameEngine5 = GameEngine.getInstance();
                if (gameEngine5.networkEngine != null) {
                    gameEngine5.networkEngine.H();
                }
                break;
            case 15: // Exit
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Exit?")
                        .setMessage("Are you sure you want to exit this game?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                InGameActivity.this.finish();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                break;
            case 16: // Team Chat
                showChatDialog(true);
                break;
            case 18: // Export Map
                if (AppFrameworkUtils.requestStoragePermission(this)) {
                    showExportMapDialog(null);
                }
                break;
            case 19: // Surrender
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Disconnect?")
                        .setMessage("Are you sure you want to surrender this game?")
                        .setPositiveButton("Surrender", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                GameEngine.getInstance().networkEngine.m("-surrender");
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                break;
            case 20: // Finish
                finish();
                break;
            case 21: // Return to Battleroom
                finish();
                MultiplayerBattleroomActivity.updateUI();
                MultiplayerBattleroomActivity.refreshChatLog();
                break;
            case 22: // Hide Interface
                GameEngine gameEngine6 = GameEngine.getInstance();
                gameEngine6.isMenuOpen = true;
                gameEngine6.gameUI.isDraggingSelection = false;
                break;
            case 23: // Leaderboard
                GameEngine.log("TODO display leaderboard settings");
                break;
        }
    }

    // ──────────────────────────────────────────────────────────
    // Menu Option Helpers
    // ──────────────────────────────────────────────────────────

    /**
     * Handle case 10: Disconnect (client) or Exit Game (server).
     * Shows appropriate dialog with Return to Battleroom option for server.
     */
    private void handleDisconnectOrExit() {
        GameEngine gameEngine = GameEngine.getInstance();
        String title = Locale.get("menus.ingame.multiplayerClose.titleDisconnect", new Object[0]);
        String message = Locale.get("menus.ingame.multiplayerClose.messageDisconnect", new Object[0]);
        String button = Locale.get("menus.ingame.multiplayerClose.disconnectButton", new Object[0]);
        if (gameEngine.networkEngine.isServer) {
            title = Locale.get("menus.ingame.multiplayerClose.title", new Object[0]);
            message = Locale.get("menus.ingame.multiplayerClose.messageEndGame", new Object[0]);
            button = Locale.get("menus.ingame.exitGame", new Object[0]);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GameEngine.getInstance().networkEngine.disconnectNetworking("exited");
                        InGameActivity.this.finish();
                    }
                })
                .setNegativeButton(Locale.get("menus.common.back", new Object[0]), null);
        if (gameEngine.networkEngine.isServer) {
            builder.setNeutralButton(Locale.get("menus.ingame.multiplayerClose.returnToBattleroom", new Object[0]),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            GameEngine.log("Returning to battleroom clicked.");
                            GameEngine gameEngine4 = GameEngine.getInstance();
                            gameEngine4.networkEngine.ag();
                            gameEngine4.gameUI.isDraggingSelection = false;
                        }
                    });
        }
        builder.show();
    }

    /**
     * Handle case 12: Save game with storage permission check.
     */
    private void handleSaveGame() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (AppFrameworkUtils.requestStoragePermission(InGameActivity.this)) {
                    InGameActivity.this.showExportMapDialog(null);
                }
            }
        };
        if (!AppFrameworkUtils.askForStoragePermission(this, runnable)) {
            runnable.run();
        }
    }

    // ──────────────────────────────────────────────────────────
    // Dialogs
    // ──────────────────────────────────────────────────────────

    /**
     * Show chat dialog using R.layout.alert_chat.
     * JADX: showChatDialog(boolean) → a(boolean)
     * Uses networkEngine.m(string) for public chat, networkEngine.l(string) for team chat.
     */
    private void showChatDialog(final boolean teamOnly) {
        GameEngine gameEngine = GameEngine.getInstance();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (!teamOnly) {
            builder.setTitle("Send Message");
        } else {
            builder.setTitle("Send Team Message");
        }
        View view = LayoutInflater.from(this).inflate(R.layout.alert_chat, (ViewGroup) null);
        builder.setView(view);
        TextView chatMessages = (TextView) view.findViewById(R.id.chat_messages);
        final EditText chatText = (EditText) view.findViewById(R.id.chat_text);
        chatMessages.setText(gameEngine.networkEngine.chatLog.a());
        chatText.setText(VariableScope.nullOrMissingString);
        chatText.requestFocus();

        builder.setPositiveButton(teamOnly ? "Send Team" : "Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String message = chatText.getText().toString();
                GameEngine gameEngine2 = GameEngine.getInstance();
                if (!message.trim().equals(VariableScope.nullOrMissingString)) {
                    if (teamOnly) {
                        gameEngine2.networkEngine.l(message);
                    } else {
                        gameEngine2.networkEngine.m(message);
                    }
                }
                gameEngine2.gameUI.isDraggingSelection = false;
            }
        });
        builder.setNeutralButton("Send & Ping Map", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String message = chatText.getText().toString();
                GameEngine gameEngine2 = GameEngine.getInstance();
                if (!message.trim().equals(VariableScope.nullOrMissingString)) {
                    if (teamOnly) {
                        gameEngine2.networkEngine.l(message);
                    } else {
                        gameEngine2.networkEngine.m(message);
                    }
                }
                gameEngine2.gameUI.isDraggingSelection = false;
                gameEngine2.gameUI.activatePingMapMode();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

    /**
     * Show export map dialog (save map to file).
     * JADX: showSaveGameDialog(String) → e(String)
     * Note: JADX named the export map dialog as "showSaveGameDialog" and vice versa,
     * but logically this handles map export.
     */
    public void showSaveGameDialog(String initialName) {
        final GameEngine gameEngine = GameEngine.getInstance();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Export Map");
        builder.setMessage("Enter a name to export the map as");
        final EditText editText = new EditText(this);
        if (initialName == null) {
            editText.setText(("New " + gameEngine.getCurrentMapName() + " (" +
                    Utility.formatDate("d MMM yyyy").replace(".", VariableScope.nullOrMissingString) + " " +
                    Utility.formatDate("HH.mm.ss") + ")").replace("  ", " "));
        } else {
            editText.setText(initialName);
        }
        builder.setView(editText);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String name = editText.getText().toString();
                if (containsIllegalFilenameChars(name)) {
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(InGameActivity.this);
                    builder2.setTitle("Bad Map Name");
                    builder2.setMessage("The characters /\\:*?\"<> are not allowed (fat32 formatting)");
                    builder2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog2, int which2) {
                            InGameActivity.this.showSaveGameDialog(name);
                        }
                    });
                    builder2.show();
                    return;
                }
                gameEngine.tileMap.isWorldPointVisibleForTeam(gameEngine.currentMapPath,
                        "/SD/rustedWarfare/maps/" + name + ".tmx");
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

    /**
     * Show save game dialog (save game state to file).
     * JADX: showExportMapDialog(String) → f(String)
     * Note: JADX naming is swapped — this is actually the save game dialog.
     */
    public void showExportMapDialog(String initialName) {
        GameEngine gameEngine = GameEngine.getInstance();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Save Game");
        builder.setMessage("Enter a name to save the game under");
        final EditText editText = new EditText(this);
        if (initialName == null) {
            editText.setText(gameEngine.getCurrentMapName() + " (" +
                    Utility.formatDate("d MMM yyyy").replace(".", VariableScope.nullOrMissingString) + " " +
                    Utility.formatDate("HH.mm.ss") + ")");
        } else {
            editText.setText(initialName);
        }
        builder.setView(editText);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String name = editText.getText().toString();
                if (containsIllegalFilenameChars(name)) {
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(InGameActivity.this);
                    builder2.setTitle("Bad Save Name");
                    builder2.setMessage("The characters /\\:*?\"<> are not allowed (fat32 formatting)");
                    builder2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog2, int which2) {
                            InGameActivity.this.showExportMapDialog(name);
                        }
                    });
                    builder2.show();
                    return;
                }
                InGameActivity.this.saveGame(name);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
    }

    /**
     * Check for illegal filename characters: /\:*?"<>
     */
    private boolean containsIllegalFilenameChars(String name) {
        return name.contains("/") || name.contains("") || name.contains(":") ||
               name.contains("*") || name.contains("?") || name.contains("\"") ||
               name.contains("<") || name.contains(">");
    }

    // ──────────────────────────────────────────────────────────
    // Save/Load Game
    // ──────────────────────────────────────────────────────────

    /**
     * Save the game. Uses startActivityForResult(0) and LoadGameRunnable.
     * JADX: d(String) — save game with name
     */
    public void saveGame(String name) {
        startActivityForResult(null, 0);
        LoadGameRunnable loadGameRunnable = new LoadGameRunnable(this);
        loadGameRunnable.saveName = name;
        new Thread(loadGameRunnable).start();
    }

    /**
     * Close and restart — opens Play Store page.
     * JADX: l() posts to handler, closeAndRestart() → n()
     */
    public void restartViaPlayStore() {
        this.handler.post(new Runnable() {
            @Override
            public void run() {
                InGameActivity.this.closeAndRestart();
            }
        });
    }

    /**
     * Open Play Store listing.
     * JADX: closeAndRestart() → n()
     */
    private void closeAndRestart() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=com.corrodinggames.rts")));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getApplicationContext(), "Failed to open Android Market", 0).show();
        }
    }

    /**
     * Empty method — no-op in original.
     * JADX: m()
     */
    public void m() {
    }

    /**
     * TODO: Deobfuscate - likely shows rate/review dialog.
     * JADX: l()
     */
    public void l() {
    }
}
