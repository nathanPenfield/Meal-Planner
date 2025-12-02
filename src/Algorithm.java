public class Algorithm {
    private Meal[] meals;
    private int calorieGoal;
    private boolean meatEligible;
    private boolean dairyEligible;
    public Algorithm(Meal[] meals,int calorieGoal,boolean meatEligible,boolean dairyEligible){
        this.meals = meals;
        this.calorieGoal = calorieGoal;
        this.meatEligible = meatEligible;
        this.dairyEligible = dairyEligible;


    }
}
