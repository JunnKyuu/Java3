package token;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
public class TokenManager {
    private static final long TOKEN_EXPIRY_DURATION = TimeUnit.MINUTES.toMillis(10);
    private static Map<String, TokenInfo> tokens = new HashMap<>();
    private static class TokenInfo {
        String token;
        long expiryTime;
        String ID;
        String role;
        TokenInfo(String token, long expiryTime, String ID, String role) {
            this.token = token;
            this.expiryTime = expiryTime;
            this.ID = ID;
            this.role = role;
        }
    }
    public static String createToken(String ID, String role) {
        String token = UUID.randomUUID().toString();
        long expiryTime = System.currentTimeMillis() + TOKEN_EXPIRY_DURATION;
        tokens.put(token, new TokenInfo(token, expiryTime, ID, role));
        return token;
    }
    public static boolean isValidToken(String token) {
        TokenInfo tokenInfo = tokens.get(token);
        if (tokenInfo == null) return false;
        if (System.currentTimeMillis() > tokenInfo.expiryTime) {
            tokens.remove(token);
            return false;
        }
        return true;
    }
    public static boolean invalidateToken(String token) {
        if (tokens.containsKey(token)) {
            tokens.remove(token);
            return true;
        } else return false;
    }
    public static String getID(String token) {
        TokenInfo tokenInfo = tokens.get(token);
        return tokenInfo.ID;
    }
    public static String getRole(String token) {
        TokenInfo tokenInfo = tokens.get(token);
        return tokenInfo.role;
    }
}