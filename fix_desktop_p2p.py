import re

with open("desktop/src/main/kotlin/com/corrodinggames/rts/gameFramework/p2p/DesktopP2PLobbyService.kt", "r") as f:
    content = f.read()

# Replace class declaration
content = content.replace("class P2PLobbyService private constructor() {", "class DesktopP2PLobbyService private constructor() : P2PLobbyService() {")

# Add overrides
content = re.sub(r'fun startIfNeeded\(', 'override fun startIfNeeded(', content)
content = re.sub(r'fun getSavedPeerConfig\(', 'override fun getSavedPeerConfig(', content)
content = re.sub(r'fun leaveLobby\(', 'override fun leaveLobby(', content)
content = re.sub(r'fun hostCurrentServer\(', 'override fun hostCurrentServer(', content)
content = re.sub(r'fun prepareJoin\(', 'override fun prepareJoin(', content)
content = re.sub(r'fun stopSession\(', 'override fun stopSession(', content)
content = re.sub(r'fun requestRefresh\(', 'override fun requestRefresh(', content)
content = re.sub(r'fun getRooms\(', 'override fun getRooms(', content)
content = re.sub(r'fun findRoom\(', 'override fun findRoom(', content)

# Remove @JvmField var inLobby
content = re.sub(r'@JvmField\s+var inLobby = false', '', content)

# Update companion object
content = content.replace("private val singleton: P2PLobbyService = P2PLobbyService()", "private val singleton: DesktopP2PLobbyService = DesktopP2PLobbyService()")
content = content.replace("fun getInstance(): P2PLobbyService = singleton", "fun getInstance(): DesktopP2PLobbyService = singleton")

with open("desktop/src/main/kotlin/com/corrodinggames/rts/gameFramework/p2p/DesktopP2PLobbyService.kt", "w") as f:
    f.write(content)
