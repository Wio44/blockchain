package sk.wio;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int DIFFICULTY = 5;

    public static void main(String[] args) {
        List<Block> blockchain = new ArrayList<>();

        Miner miner = new Miner(DIFFICULTY);

        String firstTransaction = "Mary pays Joseph 7 CC";
        Block firstBlock = miner.mineBlock(firstTransaction, "0");
        blockchain.add(firstBlock);

        String secondTransaction = "George pays Mimi 3 CC";
        Block secondBlock = miner.mineBlock(secondTransaction, firstBlock.getHash());
        blockchain.add(secondBlock);

        String thirdTransaction = "Julian pays Wio 10 CC";
        Block thirdBlock = miner.mineBlock(thirdTransaction, secondBlock.getHash());
        blockchain.add(thirdBlock);

        System.out.println("\nIs blockchain valid? " + isBlockchainValid(blockchain));

        String prettyBlockchain = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\nBlockchain:");
        System.out.println(prettyBlockchain);
    }

    private static boolean isBlockchainValid(List<Block> blockchain) {
        if (!blockchain.isEmpty()) {
            for (int i = 0; i < blockchain.size(); i++) {
                Block block = blockchain.get(i);

                String expectedHash = Hasher.calculateHash(block.getPreviousHash(), block.getTransaction(), block.getNonce());
                if (!expectedHash.equals(block.getHash())) {
                    System.out.println("Block has invalid hash");
                    return false;
                }

                if (i > 0) {
                    Block previousBlock = blockchain.get(i - 1);
                    if (!previousBlock.getHash().equals(block.getPreviousHash())) {
                        System.out.println("Previous hash is not equal!");
                        return false;
                    }
                }
            }
        } else {
            System.out.println("Empty blockchain");
            return true;
        }
        return true;
    }
}

