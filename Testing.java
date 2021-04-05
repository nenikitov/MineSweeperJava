public class Testing {
    public static void main(String[] args) {
        Field field = new Field(10,5,0.2);
        field.openTile(0, 0);
        field.openTile(1, 0);
        field.openTile(2, 0);
        field.openTile(3, 0);

        System.out.println(field);
    }    
}
