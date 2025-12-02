import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MealArray{
    private Meal[] meals;
    private int size;
    
    public MealArray(int capacity){
        this.meals = new Meal[capacity];
        this.size = 0;
    }
    public void loadCSV(){
        try (BufferedReader br = new BufferedReader(new FileReader("meals.csv"))) {

            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                
                // skip first line in csv
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                // if capacity reached
                if (this.size >= this.meals.length) {
                    System.out.println("Meal array capacity reached.");
                    break;
                }

                String[] fields = line.split(",");

                this.meals[this.size] = new Meal(fields[0], Integer.parseInt(fields[1]), Float.parseFloat(fields[2]), Boolean.parseBoolean(fields[3]), Boolean.parseBoolean(fields[4]), fields[5]);
                this.size++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Meal[] getMeals(){
        return this.meals;
    }
    public int getSize(){
        return this.size;
    }
}