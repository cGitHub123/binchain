import java.util.Date;

/**
 * author caibin@58.com
 * date 2021-02-19
 */
public class Block {

    public String hash;

    public String previousHash;

    private String data;

    private long timeStamp;

    private int nonce;

    // 设置难度为5,如果设置成1分分钟就给你算出来.
    public static int difficulty = 5;

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        // 区块的hash值由四部分组成,
        String calculatedhash = StringUtil.applySha256(
                previousHash +
                        Long.toString(timeStamp) +
                        Integer.toString(nonce) +
                        data
        );
        return calculatedhash;
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0"
        // 判断前N位是否都全是0.
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

}
