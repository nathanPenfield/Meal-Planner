import java.lang.Math;

public class Algorithm {
    private Meal[] meals;
    private int calorieGoal;
    private boolean considerPrice;
    private int n;

    public Algorithm(Meal[] meals,int calorieGoal,boolean meatEligible,boolean dairyEligible,boolean considerPrice){
        this.meals = meals;
        this.calorieGoal = calorieGoal;
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

        // define weight for cost to affect outcome.
        float weight = 0.5f;

        // get distance calories are from goal
        int aVal = Math.abs(a.getCalories()-goal);
        int bVal = Math.abs(b.getCalories()-goal);

        // consider cost if that is selected.
        float aScore = aVal;
        float bScore = bVal;
        if(this.considerPrice){
            aScore = aVal + (weight*a.getPrice());
            bScore = bVal + (weight*b.getPrice());
        }

        // return the best option
        if(aScore<bScore){
            return a;
        }else if(bScore<aScore){
            return b;
        }else{
            // if tie return closest to calorie goal
            if(aVal<bVal){
                return a;
            }
            return b;
        }
    }
}
