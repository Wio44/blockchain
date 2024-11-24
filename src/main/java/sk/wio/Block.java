package sk.wio;

public class Block {
    // digital signature
    private final String hash;
    // digital signature previous block
    private final String previousHash;
    // data of the block
    private final String transaction;
    // number which miner has solved
    private final long nonce;

    public Block(String hash, String previousHash, String transaction, long nonce) {
        this.nonce = nonce;
        this.transaction = transaction;
        this.previousHash = previousHash;
        this.hash = hash;
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getTransaction() {
        return transaction;
    }

    public long getNonce() {
        return nonce;
    }
}
