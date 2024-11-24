package sk.wio;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class Hasher {

    public static String calculateHash(String previousHash, String transaction, long nonce) {
        String toHash = previousHash + transaction + nonce;
        return Hashing.sha256()
                .hashString(toHash, StandardCharsets.UTF_8)
                .toString();
    }
}
