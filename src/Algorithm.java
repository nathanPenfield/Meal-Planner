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
        // Initialize L which is hold [meal,meal] all of the meals used for that result
        Meal[][][] L = new Meal[n+1][calorieGoal+1][];
        for(int i=0; i<=n; i++){
            for(int j=0; j<=calorieGoal; j++){
                L[i][j] = new Meal[0];
            }
        }
        // Initialize P which is holding [calories,price]
        float[][][] P = new float[n+1][calorieGoal+1][2];
        for(int i=0; i<=n; i++) {
            for(int j=0; j<=calorieGoal; j++) {
                P[i][j][0] = 0f;
                P[i][j][1] = 0f;
            }
        }
        for(int i = 1; i <= n; i++) {
            for(int weight = 1; weight <= calorieGoal; weight++) {

                int calories = meals[i-1].getCalories();
                float price   = meals[i-1].getPrice();

                int dif = weight - calories;
                if(dif < 0){ 
                    dif = 0;
                }
                float[] result = best(P[i-1][weight][0],P[i-1][dif][0] + calories,calorieGoal,P[i-1][weight][1],P[i-1][dif][1] + price);

                // write best calories + price
                P[i][weight][0] = result[0];
                P[i][weight][1] = result[1];

            
                if(result[2] == 1.0f){
                    Meal[] prev = L[i-1][weight];
                    Meal[] copy = new Meal[prev.length];
                    System.arraycopy(prev, 0, copy, 0, prev.length);
                    L[i][weight] = copy;
                } 
                else {
                    Meal[] prev = L[i-1][dif];
                    Meal[] newArr = new Meal[prev.length + 1];
                    System.arraycopy(prev, 0, newArr, 0, prev.length);
                    newArr[prev.length] = meals[i-1];   // FIXED: i-1, not i

                    L[i][weight] = newArr;
                }
            }
        }

        
        return L[n][calorieGoal];
    }
    private float[] best(float a,float b,int goal,float aPrice,float bPrice){

        // define weight for cost to affect outcome.
        float weight = 65.0f;

        // get distance calories are from goal
        float aVal = Math.abs(a-goal);
        float bVal = Math.abs(b-goal);

        // consider cost if that is selected.
        float aScore = aVal;
        float bScore = bVal;
        if(this.considerPrice){
            aScore = aVal + (weight*aPrice);
            bScore = bVal + (weight*bPrice);
        }

        // return the best option
        if(aScore<bScore){
            float[] result = new float[]{a,aPrice,1.0f};
            return result;
        }else if(bScore<aScore){
            float[] result = new float[]{b,bPrice,0.0f};
            return result;
        }else{
            // if tie return closest to calorie goal
            if(aVal<bVal){
                float[] result = new float[]{a,aPrice,1.0f};
                return result;
            }
            float[] result = new float[]{b,bPrice,0.0f};
            return result;
        }
    }
}