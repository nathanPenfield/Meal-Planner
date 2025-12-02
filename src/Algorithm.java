import java.lang.Math;

public class Algorithm {
    private Meal[] meals;
    private int calorieGoal;
    private boolean meatEligible;
    private boolean dairyEligible;
    private boolean considerPrice;
    private int n;

    public Algorithm(Meal[] meals,int calorieGoal,boolean meatEligible,boolean dairyEligible,boolean considerPrice){
        this.meals = meals;
        this.calorieGoal = calorieGoal;
        this.meatEligible = meatEligible;
        this.dairyEligible = dairyEligible;
        this.considerPrice = considerPrice;
        this.n = meals.length;
        
        // filter for dairy/meat || done in O(n) time by using in place removal
        if(!meatEligible||!dairyEligible){
            int j = 0;
            for(int i=0;i<meals.length;i++){
                if((!meatEligible&&meals[i].hasProtein())||(!dairyEligible&&meals[i].hasDairy())){
                    // meal is not eligible
                    continue;
                }else{
                    meals[j]=meals[i];
                    j++;
                }   
            }
            this.n = j;
        }
    }
    public Meal[] findMealPlan(){
        
        return this.meals; // placeholder so it don't get mad.
    }
    private Meal best(Meal a,Meal b,int goal){
        // get calories of meals
        int aVal = a.getCalories();
        int bVal = b.getCalories();

        // get distance from goal
        aVal = Math.abs(aVal-goal);
        bVal = Math.abs(bVal-goal);

        // consider cost if that is selected.
        //if(this.considerPrice){
            
        //}
        if(aVal<bVal){
            return a;
        }else{
            return b;
        }
    }
}
