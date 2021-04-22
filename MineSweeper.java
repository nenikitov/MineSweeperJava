public class MineSweeper {
    public static void main(String[] args) {
        MineField mineField = new MineField(6, 4, 0.25);
        mineField.populateField(0, 0);
    }
}