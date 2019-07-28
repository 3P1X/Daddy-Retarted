package JAM;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;

public class Gas extends Item{
    static ImageView imageView ;
    static Image image;

    public Gas(){
        super(System.getProperty("user.dir")+"/assets/"+"gas.png",0,0,200);
        try {
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/assets/"+"gas.png");
            image = new Image(fis);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        imageView = new ImageView(image);
    }

    public static ImageView getImage(){

        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setLayoutY(250);
        imageView.setLayoutX(1120);
        return imageView;
    }
}
