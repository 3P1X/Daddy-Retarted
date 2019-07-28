package JAM;

//185 first floor
//185+350

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Main extends Application {
    private static final int HEIGHT = 700;
    private static final int WIDTH = 1400;

//    private static Map map;
//    private static Bozorgvar bozorgvar;
    private static Son son = new Son();
    private static Father father = new Father();
    private static Cooler cooler = new Cooler();

    private BorderPane startPane;
    private BorderPane dialogPane;
    private BorderPane playGroundPane;
    public static Pane mapPane;

    private Scene startScene;
    private Scene dialogScene;
    private Scene playGroundScene;

    public static boolean window = true;
    public static boolean bathroom = true;
    public static boolean tv = true;
    public static boolean newspaper = true;
    public static boolean gas = true;


    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello World");
        createStart(primaryStage);
        createDialog(primaryStage);
        createPlay(primaryStage);
        primaryStage.show();
        primaryStage.setScene(startScene);

    }

    public void createStart(Stage stage){
        startPane = new BorderPane();
        startScene = new Scene(startPane, WIDTH, HEIGHT);
        Button startButton = new Button("Start");
        startButton.setOnAction(e -> {
            stage.setScene(dialogScene);
        });
        startPane.setCenter(startButton);
    }



    public void createDialog(Stage stage){
        Label dialog = new Label("Protect daddy from heart attack with out being found out\n" +
                "By interacting with items prevent hazards from hurting daddy\n" +
                "Also Watch out for heat, because it's summer and summer is hot!\n" +
                "\n" +
                "If your air conditioner is off then you will slowly get heatstroke and will pass out, then you can't help your daddy anymore! Unfortunately, daddy always tries to turn it off, because … Daddy!!\n" +
                "\n" +
                "Also, you are supposed to be at school right now!(even if you are a 24 years old jobless man) so daddy will not forgive if he sees you skipping class, so DON'T. GET. FOUND. OUT!\n" +
                "\n" +
                "Do your job well so daddy can have a good day and you can go back to school soon\n" +
                "\n" +
                "Over and out.\n" +
                "\n" +
                "~Help daddies association\n" +
                "\n" +
                "Winning Conditions: \n" +
                "Daddy passes all 6 Hazards without getting heart attack from shock\n" +
                "\n" +
                "Losing Conditions:\n" +
                "Daddy has heart attack\n" +
                "Daddy finds you\n" +
                "You get heatstroke");
        dialogPane = new BorderPane();
        dialogScene = new Scene(dialogPane, WIDTH, HEIGHT);
        Button playButton = new Button("let's Do it");
        playButton.setOnAction(e -> {
            stage.setScene(playGroundScene);
            (new Thread(son)).start();
            (new Thread(father)).start();

        });
        dialogPane.setCenter(dialog);
        dialogPane.setBottom(playButton);
    }

    public void createPlay(Stage stage){
        playGroundPane = new BorderPane();
        mapPane = new Pane();
        playGroundScene = new Scene(playGroundPane, WIDTH, HEIGHT);
        keyBoardController(playGroundScene);

//        mapPane.getChildren().add(NewsPaper.getImage());

        mapPane.getChildren().add(createRoom(
                System.getProperty("user.dir")+"/assets/"+"window2.png",
                50,50,300,300));
        mapPane.getChildren().add(createRoom(
                System.getProperty("user.dir")+"/assets/"+"tv2.png",
                350,50,600,300));
        mapPane.getChildren().add(createRoom(
                System.getProperty("user.dir")+"/assets/"+"kitchen2.png",
                950,50,300,300));
        mapPane.getChildren().add(createRoom(
                System.getProperty("user.dir")+"/assets/"+"bathroom2.png",
                50,350,300,300));
        mapPane.getChildren().add(createRoom(
                System.getProperty("user.dir")+"/assets/"+"cooler2.png",
                350,350,600,300));
        mapPane.getChildren().add(createRoom(
                System.getProperty("user.dir")+"/assets/"+"entry2.png",
                950,350,300,300));

        Line left = new Line(50,50,50,650);
        Line up = new Line(50,50,1250,50);
        Line down = new Line(50,650,1250,650);
        Line right = new Line(1250,50,1250,650);
        Line floor = new Line(50,350,1250,350);

        mapPane.getChildren().add(cooler.getCircle());

        mapPane.getChildren().addAll(left,up,down,right,floor);
        mapPane.getChildren().add(son.getPane());
        mapPane.getChildren().add(father.getPane());
        mapPane.getChildren().add(NewsPaper.getImage());
        mapPane.getChildren().add(Gas.getImage());

        playGroundPane.setLeft(mapPane);
        playGroundPane.setRight(son.getItems());

    }

    public static Son getSon() {
        return son;
    }

    public static Cooler getCooler() {
        return cooler;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void keyBoardController(Scene scene) {
        scene.setOnKeyPressed(event -> {
            if(son.isAlive()){
                switch (event.getCode()) {
                    case LEFT:
                        son.goLeft();
                        break;
                    case RIGHT:
                        son.goRight();
                        break;
                    case UP:
                        son.goNextFloor();
                        break;
                    case F:
                        son.lootItem();
                        break;
                    case E:
                        son.doAction();
                        break;
                }
            }
        });
    }

    public ImageView createRoom(String url,int x,int y,int width,int height){
        Image image = null;
        ImageView imageView;
        try {
            FileInputStream fis = new FileInputStream(url);
            image = new Image(fis);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        imageView = new ImageView(image);
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
        return imageView;
    }

    public Pane getMapPane() {
        return mapPane;
    }


}
