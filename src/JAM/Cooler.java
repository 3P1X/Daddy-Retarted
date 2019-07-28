package JAM;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Cooler {
    boolean isOn = false;
    Circle circle = new Circle(5);
    public void change(){
        isOn = !isOn;
        if(isOn){
            circle.setFill(Color.GREEN);
        }else{
            circle.setFill(Color.RED);
        }
        System.out.println("cooler: "+isOn);

    }

    public Circle getCircle() {
        circle.setCenterX(790);
        circle.setCenterY(505);
        return circle;
    }


}
