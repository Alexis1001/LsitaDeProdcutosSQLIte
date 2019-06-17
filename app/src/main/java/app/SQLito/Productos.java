package app.SQLito;

public class Productos {

    String id;
    String price;
    String name;

    public Productos(){

    }

    public Productos(String id ,String name,String price){
        this.id=id;
        this.name=name;
        this.price=price;
    }

    public  void setId(String id){
       this.id=id;
    }

    public String getId() {

        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setPrice(String price) {

        this.price = price;
    }

    public String getPrice() {

        return price;
    }

    //@androidx.annotation.NonNull
    @Override
    public String toString() {
        return "id "+id+" name "+name+" price "+price;
    }
}
