package Pizzas;

/* Enum which represents base type of pizza
 * */
public enum Base_type {
    DOUGH,
    PITA;
    public int get_weight() {
        if (this.toString().equals("dough")){
            return 30;
        } else {
            return 20;
        }
    }
    @Override
    public String toString(){
        return super.toString().toLowerCase();
    }
}
