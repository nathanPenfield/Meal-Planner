import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// https://www.youtube.com/watch?v=5o3fMLPY7qY
// followed tutorial to 8 minutes. We need to add input fields and make the submission button actually work and call optimization algorithm (not made yet)



public class GUI implements ActionListener{
    private JFrame frame;
    private JPanel panel;
    private JLabel title;
    private JTextField calorieInput;
    private JComboBox<String> dairyInput;
    private JComboBox<String> meatInput;
    private JComboBox<String> priceInput;
    private JLabel errorMessage;
    private JTextArea outputLabel;
    public GUI(){

        frame = new JFrame();
        panel = new JPanel();
        title = new JLabel("Meal Planning Helper!");

        JButton submitButton = new JButton("Submit");
        calorieInput = new JTextField();
        JLabel calorieLabel = new JLabel("Calorie Goal:");
        String[] yesOrNo = {"Yes","No"};
        dairyInput = new JComboBox<>(yesOrNo);
        JLabel dairyLabel = new JLabel("Can You Eat Dairy:");
        meatInput = new JComboBox<>(yesOrNo);
        JLabel meatLabel = new JLabel("Can You Eat Meat:");
        priceInput = new JComboBox<>(yesOrNo);
        JLabel priceLabel = new JLabel("Are You Concerned With Price:");
        errorMessage = new JLabel("");
        outputLabel = new JTextArea("");
        // configure frame
        frame.setSize(1200,750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Meal Planning Helper");
        frame.add(panel);

        panel.setLayout(null);

        title.setBounds(135,50,230,40);
        title.setFont(new Font("Serif", Font.PLAIN, 24));
        panel.add(title,BorderLayout.CENTER);

        calorieLabel.setBounds(100,130,300,20);
        calorieLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        calorieInput.setBounds(100,150,300,40);
        panel.add(calorieLabel);
        panel.add(calorieInput);

        dairyLabel.setBounds(100,230,300,20);
        dairyLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        dairyInput.setBounds(100,250,300,40);
        panel.add(dairyLabel);
        panel.add(dairyInput);

        meatLabel.setBounds(100,330,300,20);
        meatLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        meatInput.setBounds(100,350,300,40);
        panel.add(meatLabel);
        panel.add(meatInput);

        priceLabel.setBounds(100,430,300,20);
        priceLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        priceInput.setBounds(100,450,300,40);
        panel.add(priceLabel);
        panel.add(priceInput);

        submitButton.setBounds(200,520,100,50);
        panel.add(submitButton);
        submitButton.addActionListener(this);
        
        errorMessage.setBounds(100,570,300,50);
        errorMessage.setFont(new Font("Serif", Font.PLAIN, 14));
        panel.add(errorMessage);

        outputLabel.setBounds(500,100,500,800);
        outputLabel.setBackground(panel.getBackground());
        outputLabel.setFont(new Font("Serif", Font.PLAIN, 10));
        panel.add(outputLabel);
        frame.setVisible(true);

    }

    public static void main(String[] args){
        new GUI();
    }
    @Override
    public void actionPerformed(ActionEvent e){
        errorMessage.setText("");
        outputLabel.setText("");
        int calories;
        try {
            calories = Integer.parseInt(calorieInput.getText());
            if(calories<=0){
                errorMessage.setText("Calories must be a positive integer");
                return;
            }
        } catch (Exception ex) {
            errorMessage.setText("Calories must be an integer");
            return;
        }
        Object dairy = dairyInput.getSelectedItem();
        boolean dairyEligible;
        if(dairy=="Yes"){
            dairyEligible = true; 
        }else{
            dairyEligible = false;
        }
        Object meat = meatInput.getSelectedItem();
        boolean meatEligible;
        if(meat=="Yes"){
            meatEligible = true; 
        }else{
            meatEligible = false;
        }
        Object cost = priceInput.getSelectedItem();
        boolean costEligible;
        if(cost=="Yes"){
            costEligible = true; 
        }else{
            costEligible = false;
        }
        MealArray meals = new MealArray(98);
        meals.loadCSV();
        Meal[] mealsArray = meals.getMeals();
        Algorithm algorithm = new Algorithm(mealsArray,calories,meatEligible,dairyEligible,costEligible);
        Meal[] finalMeals = algorithm.findMealPlan();
        
        String finalDisplay = "Name, Calories, Price, Link\n";
        int totCal = 0;
        float totCost = 0f;
        for(int i=0;i<finalMeals.length;i++){
            String mealName = finalMeals[i].getMealName();
            int calorie = finalMeals[i].getCalories();
            totCal+=calorie;
            float price = finalMeals[i].getPrice();
            totCost+=price;
            String mealLink = finalMeals[i].getMealLink();
            finalDisplay+=""+mealName+", "+calorie+", "+price+"\n "+mealLink+"\n\n";
        }
        finalDisplay+="Total Calories = "+totCal+"\n";
        finalDisplay+="Total Cost = "+totCost+"\n";
        outputLabel.setText(finalDisplay);
           
        

    }
}  