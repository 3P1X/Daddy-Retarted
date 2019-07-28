package JAM;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;


public class Item {
    int xStart;
    int xEnd;
    int y;
    private Image image;
    ImageView imageView;


    public Item(String url,int xStart, int xEnd, int y){
        try {
            FileInputStream fis = new FileInputStream(url);
            this.image = new Image(fis);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        this.xEnd = xEnd;
        this.xStart = xStart;
        this.y =y;
        imageView = new ImageView(this.image);
    }


    public boolean isInRange(){
        int x = Main.getSon().getMyX();
        int y = Main.getSon().getMyY();
        System.out.println(" : " + this.y);
        System.out.println(xStart+" : "+xEnd);
        System.out.println(x+" : "+y);

        if(y==this.y && x>=xStart && x<= xEnd){
            return true;
        }
        return false;
    }

    public ImageView getImageView() {
        imageView.setFitHeight(64);
        imageView.setFitWidth(64);
        return imageView;
    }
}
