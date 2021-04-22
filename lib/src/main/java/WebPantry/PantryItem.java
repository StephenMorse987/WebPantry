package WebPantry;

public class PantryItem {
    private int itemIndex;
    public float amount;
    public int measureIndex;
    public String ingredientName;

    public PantryItem (int itemIndex, float amount, int measureIndex, String ingredientName) {
        this.itemIndex = itemIndex;
        this.amount = amount;
        this.measureIndex = measureIndex;
        this.ingredientName = ingredientName;
    }

    public int getItemIndex () {
        return this.itemIndex;
    }

    public String getMeasure () {
        switch (this.measureIndex) {
            case 1:
                return "ct";
            case 2:
                return "dl";
            case 3:
                return "L";
            case 4:
                return "cc";
            case 5:
                return "mL";
            case 6:
                return "c";
            case 7:
                return "cup";
            case 8:
                return "dash";
            case 9:
                return "fl oz";
            case 10:
                return "gal";
            case 11:
                return "pinch";
            case 12:
                return "fl pt";
            case 13:
                return "p";
            case 14:
                return "pt";
            case 15:
                return "fl qt";
            case 16:
                return "q";
            case 17:
                return "qt";
            case 18:
                return "T";
            case 19:
                return "tbl";
            case 20:
                return "tbs";
            case 21:
                return "tbsp";
            case 22:
                return "t";
            case 23:
                return "tsp";
            case 24:
                return "g";
            case 25:
                return "kg";
            case 26:
                return "mg";
            case 27:
                return "oz";
            case 28:
                return "lb";
            default:
                return "ct";
        }
    }
}
