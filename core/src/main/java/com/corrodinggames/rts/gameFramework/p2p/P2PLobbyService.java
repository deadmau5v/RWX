package com.corrodinggames.rts.gameFramework.p2p;

import java.io.IOException;
import java.util.ArrayList;

public abstract class P2PLobbyService {
    private static P2PLobbyService instance;

    public static P2PLobbyService getInstance() {
        if (instance == null) {
            instance = new DummyP2PLobbyService();
        }
        return instance;
    }

    public static void setInstance(P2PLobbyService inst) {
        instance = inst;
    }

    public boolean inLobby = false;

    public abstract void startIfNeeded() throws IOException;
    public abstract void leaveLobby();
    public abstract String prepareJoin(String str) throws IOException;
    public abstract String getSavedPeerConfig();
    public abstract void hostCurrentServer() throws IOException;
    public abstract void stopSession();
    public abstract void requestRefresh() throws IOException;
    public abstract ArrayList<P2PRoomAdvertisement> getRooms();
    public abstract P2PRoomAdvertisement findRoom(String str);
}

class DummyP2PLobbyService extends P2PLobbyService {
    public void startIfNeeded() throws IOException {}
    public void leaveLobby() {}
    public String prepareJoin(String str) throws IOException { return ""; }
    public String getSavedPeerConfig() { return ""; }
    public void hostCurrentServer() throws IOException {}
    public void stopSession() {}
    public void requestRefresh() throws IOException {}
    public ArrayList<P2PRoomAdvertisement> getRooms() { return new ArrayList<>(); }
    public P2PRoomAdvertisement findRoom(String str) { return null; }
}
