package pizzas;
/* Enum which represents size of pizza
 * */

public enum Size{
    SMALL,
    MEDIUM,
    LARGE,
    KING;
    public int get_weight() {
        switch(this.toString()){
            case "small": return 10;
            case "medium": return 20;
            case "large": return 30;
            default: return 50;
        }
    }
    @Override
    public String toString(){
        return super.toString().toLowerCase();
    }
}