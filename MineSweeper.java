public class MineSweeper {
    public static void main(String[] args) {
        MineField mineField = new MineField(10, 5, 0.25);

        for (int y = 0; y < 5; y++)
            for (int x = 0; x < 10; x++)
                mineField.markFlagAt(x, y);

        System.out.println(mineField);
    }
}