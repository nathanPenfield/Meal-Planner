public class Meal {
    private String mealName;
    private int calories;
    private float price;
    private boolean hasDairy;
    private boolean hasProtein;
    private String mealLink;

    public Meal(String mealName,int calories,float price,boolean hasDairy,boolean hasProtein,String mealLink){
        this.mealName = mealName;
        this.calories = calories;
        this.price = price;
        this.hasDairy = hasDairy;
        this.hasProtein = hasProtein;
        this.mealLink = mealLink;
    }
    public String getMealName(){
        return this.mealName;
    }
    public int getCalories(){
        return this.calories;
    }
    public float getPrice(){
        return this.price;
    }
    public boolean hasDairy(){
        return this.hasDairy;
    }
    public boolean hasProtein(){
        return this.hasProtein;
    }
    public String getMealLink(){
        return this.mealLink;
    }
}