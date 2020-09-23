package Pizzas;

public class Human {
    private String name;
    private String surname;

    public Human(){
        this.name = "";
        this.surname = "";
    }
    public Human(String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
