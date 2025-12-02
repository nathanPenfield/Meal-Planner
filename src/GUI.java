import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;


// https://www.youtube.com/watch?v=5o3fMLPY7qY
// followed tutorial to 8 minutes. We need to add input fields and make the submission button actually work and call optimization algorithm (not made yet)



public class GUI{
    public GUI(){

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JLabel title = new JLabel("Meal Planning Helper!");
        JButton submitButton = new JButton("Submit");
        
        // correct format is top,left,bottom,right. Controls padding or blank space between edge of window and content
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        
        // 0,1 default parameters
        panel.setLayout(new GridLayout(0,1));

        panel.add(title);
        panel.add(submitButton);

        frame.add(panel,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Our GUI");
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args){
        //new GUI();
        MealArray meals = new MealArray(40);
        meals.loadCSV();
        Meal[] mealsArray = meals.getMeals();
        System.out.println(mealsArray[0].getMealName());
    }
}