public class word {
    public final String word;
    public final int count;
    /**
     * Constructor for word object
     * @param word
     * @param count
     */
    public word(String word, int count) {
        this.word = word;
        this.count = count;
    }
    public String getWord() {
        return word;
    }
    public int getCount() {
        return count;
    }
}
