package JAM;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;

public class NewsPaper extends Item {
    static ImageView imageView ;
    static Image image;

    public NewsPaper(){
        super(System.getProperty("user.dir")+"/assets/"+"newspaper.png",1050,1100,485);
        try {
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/assets/"+"newspaper.png");
            image = new Image(fis);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        imageView = new ImageView(image);
    }

    public static ImageView getImage(){

        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setLayoutY(580);
        imageView.setLayoutX(1120);
        return imageView;
    }
}
