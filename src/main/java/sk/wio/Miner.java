package sk.wio;

public class Miner {
    private final int difficulty;
    private final String target;

    public Miner(int difficulty) {
        this.difficulty = difficulty;
        this.target = new String(new char[difficulty]).replace('\0', '0');
    }

    public Block mineBlock(String transaction, String previousHash) {
        System.out.println("Mining block ...");

        long nonce = 0;
        String hash = Hasher.calculateHash(previousHash, transaction, nonce);
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = Hasher.calculateHash(previousHash, transaction, nonce);
        }

        System.out.println("Yeah, mined a block at " + nonce + " attempt. Hash => " + hash);
        return new Block(hash, previousHash, transaction, nonce);
    }
}
