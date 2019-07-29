package JAM;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;

public class Father implements Runnable{
    private BorderPane soldierPane = new BorderPane();
    Image image;
    ImageView imageView;
    private boolean alive = true;
    VBox items = new VBox();
    private int x = 500;
    private int y = 185;

    private boolean left = true;
    private boolean door1 = false;
    private boolean door2 = true;

    public int getMyX(){
        return x;
    }

    public int getMyY(){
        return y;
    }

    Father(){
        try {
            FileInputStream fis =
                    new FileInputStream(System.getProperty("user.dir")+"/assets/"+"daddy.png");
            this.image = new Image(fis);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        imageView = new ImageView(this.image);
        imageView.setFitWidth(180);
        imageView.setFitHeight(180);
    }

    @Override
    public void run() {
        while(alive){

            if(Main.getCooler().isOn){
                cooler();
            }else{
                goNextFloor();
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(left)
                goLeft();
            else
                goRight();

            check();
        }
    }

    public void goLeft(){
        x-=5;
        soldierPane.setLayoutX(x);
        if(x<50)    left = false;
    }

    public void goRight(){
        x+=5;
        soldierPane.setLayoutX(x);
        if(x>1100)  left = true;
    }

    public void goNextFloor(){
        if(x<=430 && x>= 330 && door1){
            door1 = false;
            door2 = true;
            if(y == 485){
                y-=300;
                soldierPane.setLayoutY(y);
            }else{
                y+=300;
                soldierPane.setLayoutY(y);
            }
        }else if(x<=950 && x>= 900 && door2){
            door1 = true;
            door2 = false;
            if(y == 485){
                y-=300;
                soldierPane.setLayoutY(y);
            }else{
                y+=300;
                soldierPane.setLayoutY(y);
            }
        }

    }

    public void cooler(){
        if(y==185){
            door1 = true;
            door2 = true;
            if(y<300){
                left=false;
            }
            if(y>950){
                left=true;
            }
        }else{
            door1 = false;
            door2 = false;
            if(y<300){
                left=false;
            }
            if(y>950){
                left=true;
            }
        }
        goNextFloor();
        if(x<=770 && x>= 710 && y==485){
          Main.getCooler().change();
          door1=true;
          door2=false;
        }

    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isIn(int x1,int x2,int y){
        if(y==this.y && x>=x1 && x<= x2){
            return true;
        }
        return false;
    }

    public BorderPane getPane() {
        soldierPane.setCenter(imageView);

        soldierPane.setLayoutX(x);
        soldierPane.setLayoutY(y);
        return soldierPane;

    }

    public void check() {
        sameRoom();
        if(isIn(60,200,185) && Main.window){
            die();
        }else if(isIn(40,130,485) && Main.bathroom){
            die();
        } else if(isIn(1050,1110,185) && Main.gas){
            die();
        }else if(isIn(1030,1080,485) && Main.tv){
            die();
        }
    }

    public void sameRoom() {
        if (Main.getSon().isIn(0, 280, 185) && this.isIn(0, 280, 185)) {
            doneWithSon();

        } else if (Main.getSon().isIn(280, 885, 185) && this.isIn(280, 885, 185)) {
            doneWithSon();

        } else if (Main.getSon().isIn(885, 1180, 185) && this.isIn(885, 1180, 185)) {
            doneWithSon();

        } else if (Main.getSon().isIn(0, 280, 485) && this.isIn(0, 280, 485)) {
            doneWithSon();

        } else if (Main.getSon().isIn(280, 885, 485) && this.isIn(280, 885, 485)) {
            doneWithSon();

        } else if (Main.getSon().isIn(885, 1180, 485) && this.isIn(885, 1180, 485)) {
            doneWithSon();

        }
    }

    public void doneWithSon(){
        this.alive = false;
        Platform.runLater(()->{
            Main.mapPane.getChildren().remove(soldierPane);
        });
        try {
            FileInputStream fis =
                    new FileInputStream(System.getProperty("user.dir")+"/assets/"+"daddy-angry.png");
            this.image = new Image(fis);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        imageView = new ImageView(this.image);
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        Platform.runLater(()->{
            Main.mapPane.getChildren().add(getPane());
        });
        Main.getSon().kill();
    }

    public void die(){
        this.alive = false;
        Platform.runLater(()->{
            Main.mapPane.getChildren().remove(soldierPane);
        });
        try {
            FileInputStream fis =
                    new FileInputStream(System.getProperty("user.dir")+"/assets/"+"daddy-dead.png");
            this.image = new Image(fis);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        imageView = new ImageView(this.image);
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        Platform.runLater(()->{
            Main.mapPane.getChildren().add(getPane());
        });
    }
}
