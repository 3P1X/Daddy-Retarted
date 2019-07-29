package JAM;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.FileInputStream;

public class Son implements Runnable{
    private ProgressBar healthBarTower = new ProgressBar();
    private BorderPane soldierPane = new BorderPane();
    Image image;
    ImageView imageView;
    private boolean alive = true;
    private double health = 100;
    VBox items = new VBox();
    private int x = 1100;
    private int y = 485;
    private static NewsPaper newsPaper = new NewsPaper();
    private static Spray spray = new Spray();
    private static Gas gas = new Gas();
    private static Water water = new Water();

    public int getMyX(){
        return x;
    }

    public int getMyY(){
        return y;
    }

    Son(){
        try {
            FileInputStream fis =
                    new FileInputStream(System.getProperty("user.dir")+"/assets/"+"son.png");
            this.image = new Image(fis);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        imageView = new ImageView(this.image);
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);
    }

    @Override
    public void run() {
        while(alive){
            getHelth();
            if(Main.getCooler().isOn){
                if(health<=95)
                    health+=5;
                else
                    health=100;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(health<30){
                healthBarTower.getStylesheets().add("/styles/red.css");
            }else if(health>60){
                healthBarTower.getStylesheets().add("/styles/green.css");
            }else{
                healthBarTower.getStylesheets().add("/styles/yellow.css");
            }
        }
    }

    public void getHelth(){
        this.health -= 1;
        if(health == 0){
            alive = false;
        }
        healthBarTower.setProgress(health/100);
    }

    public BorderPane getPane() {
        healthBarTower.setProgress(1);
        healthBarTower.setPrefWidth(50);
        healthBarTower.getStylesheets().add("/styles/Main.css");
        healthBarTower.getStylesheets().add("/styles/green.css");

        soldierPane.setCenter(imageView);
        soldierPane.setAlignment(healthBarTower, Pos.CENTER);
        soldierPane.setTop(healthBarTower);

        soldierPane.setLayoutX(x);
        soldierPane.setLayoutY(y);
        return soldierPane;
    }

    public void goLeft(){
        x-=10;
        soldierPane.setLayoutX(x);
        System.out.println(x +" : " + y);
    }

    public void goRight(){
        x+=10;
        soldierPane.setLayoutX(x);
        System.out.println(x +" : " + y);
    }

    public void goNextFloor(){
        if(x<=430 && x>= 330){
            if(y == 485){
                y-=300;
                soldierPane.setLayoutY(y);
            }else{
                y+=300;
                soldierPane.setLayoutY(y);
            }
        }else if(x<=950 && x>= 900){
            if(y == 485){
                y-=300;
                soldierPane.setLayoutY(y);
            }else{
                y+=300;
                soldierPane.setLayoutY(y);
            }
        }

    }

    public void lootItem(){
        if(newsPaper.isInRange()){
            if(!items.getChildren().contains(newsPaper.getImageView()))
                Platform.runLater(()->{
                    Main.mapPane.getChildren().remove(NewsPaper.getImage());
                });
                items.getChildren().add(newsPaper.getImageView());

        }else if(spray.isInRange()){
            if(!items.getChildren().contains(spray.getImageView()))
                items.getChildren().add(spray.getImageView());

        }else if(gas.isInRange()){
            if(!items.getChildren().contains(gas.getImageView()))
                items.getChildren().add(gas.getImageView());

        }else if(water.isInRange()){
            if(!items.getChildren().contains(water.getImageView()))
                items.getChildren().add(water.getImageView());

        }
    }


    public VBox getItems(){
        return items;
    }

    public void doAction(){
        if(isIn(60,210,185)){
            doWindow();
            throwNews();
        }else if(isIn(710,770,485)){
            onCooler();
        }else if(isIn(40,130,485)){
            fillBath();
        } else if(isIn(1050,1110,185)){
            doGas();
        }else if(isIn(1030,1080,485)){
            doFuse();
        }
    }

    public void doWindow(){
        System.out.println("color window");
        if(items.getChildren().contains(spray.getImageView())){
            items.getChildren().remove(spray.getImageView());
            try {
                FileInputStream fis =
                        new FileInputStream(System.getProperty("user.dir")+"/assets/"+"cross.png");
                this.image = new Image(fis);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            imageView = new ImageView(this.image);
            imageView.setFitWidth(120);
            imageView.setFitHeight(120);
            imageView.setLayoutX(140);
            imageView.setLayoutY(100);
            Platform.runLater(()->{
                Main.mapPane.getChildren().add(imageView);
            });
            Main.window = false;
        }
    }

    public void onCooler(){
        System.out.println("cooler");
        Main.getCooler().change();
    }

    public void fillBath(){
        System.out.println("bath news");
        if(items.getChildren().contains(water.getImageView())){
            items.getChildren().remove(water.getImageView());
            Main.bathroom = false;
        }
    }

    public void throwNews(){
        System.out.println(items.getChildren().contains(newsPaper.getImageView()));
        if(items.getChildren().contains(newsPaper.getImageView())){
            items.getChildren().remove(newsPaper.getImageView());
            Main.newspaper = false;
        }
    }

    public void doFuse(){
        System.out.println("fuse");

        Main.tv = false;
    }

    public void doGas(){
        System.out.println("gas");
        Main.gas = false;
        Platform.runLater(()->{
            if(Main.mapPane.getChildren().contains(Gas.getImage()))
            Main.mapPane.getChildren().remove(Gas.getImage());
        });
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

    public static NewsPaper getNewsPaper() {
        return newsPaper;
    }

    public void kill(){
        this.alive =false;
        Platform.runLater(()->{
            Main.mapPane.getChildren().remove(soldierPane);
        });
        try {
            FileInputStream fis =
                    new FileInputStream(System.getProperty("user.dir")+"/assets/"+"son-wrecket.png");
            this.image = new Image(fis);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        imageView = new ImageView(this.image);
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);
        y+=40;
        soldierPane.setLayoutY(y);
        Platform.runLater(()->{
            Main.mapPane.getChildren().add(getPane());
        });
    }
}
