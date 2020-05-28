package es.javier.diver;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * JavaFX App
 */
public class App extends Application {

    final short SCENE_HEIGHT = 480;
    final short SCENE_WIDTH = 640;
    final short TEXT_SIZE = 24;
    int posicionFondo = 0;
    int posicionFondo2 = SCENE_WIDTH;
    int posicionbuzo = 0;
    short buzoHeight = 0;
    short buzoPosY = (short) ((SCENE_HEIGHT - buzoHeight) / 2);
    byte velocidadbuzo = 0;
    int direccionpez = 1;
    int posicionTiburon = SCENE_WIDTH;
    int direcciontiburon = 1;
    int velocidadpeces = SCENE_WIDTH;
    int peces = 0;
    int vidas = 3;

    // textos para la puntuaciones 
    Text textScore;
    Text textHighScore;
    // Puntuación actual
    int score = 0;
    // Puntuación máxima
    int highScore;

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        var scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        //imagen fondo 1
        Image image2 = new Image(getClass().getResourceAsStream("/images/ocean.gif"));
        ImageView imageOceano1 = new ImageView(image2);
        imageOceano1.setX(posicionFondo); // POSICION HORIZONTALMENTE 
        imageOceano1.setY(0); // POSICION DE LA FOTO VERTICALMETE
        imageOceano1.setFitHeight(SCENE_HEIGHT);
        imageOceano1.setFitWidth(SCENE_WIDTH);
        root.getChildren().add(imageOceano1);

        //imagen fondo 2
        Image image3 = new Image(getClass().getResourceAsStream("/images/ocean2.gif"));
        ImageView imageOceano2 = new ImageView(image3);
        imageOceano2.setX(posicionFondo2);
        imageOceano2.setY(0);
        imageOceano2.setFitHeight(SCENE_HEIGHT);
        imageOceano2.setFitWidth(SCENE_WIDTH);
        root.getChildren().add(imageOceano2);

        // peces con forma 
        //pez 1 
        // cuerpo del pez
        Ellipse cuerpopez = new Ellipse();
        {
            cuerpopez.setCenterX(20);
            cuerpopez.setCenterY(20);
            cuerpopez.setRadiusX(20);
            cuerpopez.setRadiusY(8);
            cuerpopez.setFill(Color.ORANGE);
            root.getChildren().add(cuerpopez);
        }
        //cola del pez 
        Ellipse colapez = new Ellipse();
        {
            colapez.setCenterX(43);
            colapez.setCenterY(20);
            colapez.setRadiusX(8);
            colapez.setRadiusY(4);
            colapez.setFill(Color.ORANGE);
            root.getChildren().add(colapez);
        }

        // ojo del pez 
        Circle ojopez = new Circle();
        {
            ojopez.setCenterX(8);
            ojopez.setCenterY(15);
            ojopez.setRadius(4);
            ojopez.setFill(Color.WHITE);
            root.getChildren().add(ojopez);
        }

        // pupila del pez 
        Circle pupilapez = new Circle();
        pupilapez.setCenterX(8);
        pupilapez.setCenterY(15);
        pupilapez.setRadius(2);
        pupilapez.setFill(Color.BLACK);
        root.getChildren().add(pupilapez);

        //pez 2
        // cuerpo del pez
//        Ellipse cuerpopez2 = new Ellipse();
//        {
//            cuerpopez2.setCenterX(20);
//            cuerpopez2.setCenterY(20);
//            cuerpopez2.setRadiusX(20);
//            cuerpopez2.setRadiusY(8);
//            cuerpopez2.setFill(Color.FUCHSIA);
//            root.getChildren().add(cuerpopez2);
//        }
//        //cola del pez 
//        Ellipse colapez2 = new Ellipse();
//        {
//            colapez2.setCenterX(43);
//            colapez2.setCenterY(20);
//            colapez2.setRadiusX(8);
//            colapez2.setRadiusY(4);
//            colapez2.setFill(Color.INDIGO);
//            root.getChildren().add(colapez2);
//        }
//
//        // ojo del pez 
//        Circle ojopez2 = new Circle();
//        {
//            ojopez2.setCenterX(8);
//            ojopez2.setCenterY(15);
//            ojopez2.setRadius(4);
//            ojopez2.setFill(Color.WHITE);
//            root.getChildren().add(ojopez2);
//        }
//
//        // pupila del pez 
//        Circle pupilapez2 = new Circle();
//        pupilapez2.setCenterX(8);
//        pupilapez2.setCenterY(15);
//        pupilapez2.setRadius(2);
//        pupilapez2.setFill(Color.BLACK);
//        root.getChildren().add(pupilapez2);
        //imagen de bombona de oxigeno (seran las vidas que le queden)
        // imagen de vida 1 
        Image image5 = new Image(getClass().getResourceAsStream("/images/bombona.png"));
        ImageView imagebombona1 = new ImageView(image5);
        imagebombona1.setX(500);
        imagebombona1.setY(4);
        imagebombona1.setFitHeight(60);
        imagebombona1.setFitWidth(20);
        root.getChildren().add(imagebombona1);

        // imagen de vida 2 
        Image image6 = new Image(getClass().getResourceAsStream("/images/bombona2.png"));
        ImageView imagebombona2 = new ImageView(image6);
        imagebombona2.setX(540);
        imagebombona2.setY(4);
        imagebombona2.setFitHeight(60);
        imagebombona2.setFitWidth(20);
        root.getChildren().add(imagebombona2);

        //imagen de vida 3
        Image image7 = new Image(getClass().getResourceAsStream("/images/bombona3.png"));
        ImageView imagebombona3 = new ImageView(image7);
        imagebombona3.setX(580);
        imagebombona3.setY(4);
        imagebombona3.setFitHeight(60);
        imagebombona3.setFitWidth(20);
        root.getChildren().add(imagebombona3);

        //imagen buzo
        Image image1 = new Image(getClass().getResourceAsStream("/images/diver.png"));
        ImageView imageBuzo = new ImageView(image1);
        imageBuzo.setX(posicionbuzo); // posicion del buzo horizontal
        imageBuzo.setY(posicionbuzo); // posicion del buzo verticalmente
        //root.getChildren().add(imageBuzo);
        imageBuzo.setFitHeight(80);
        imageBuzo.setFitWidth(100);

        //rectangulo para el buzo
        Rectangle rectanglepersonaje = new Rectangle();
        rectanglepersonaje.setX(posicionbuzo);
        rectanglepersonaje.setY(posicionbuzo);
        rectanglepersonaje.setWidth(100);
        rectanglepersonaje.setHeight(80);
        rectanglepersonaje.setVisible(false);
        //root.getChildren().add(rectanglepersonaje);

        //grupo de personaje 
        Group grupopersonaje = new Group();
        grupopersonaje.getChildren().add(rectanglepersonaje);
        grupopersonaje.getChildren().add(imageBuzo);
        root.getChildren().add(grupopersonaje);

        //grupo de tiburon
        Group grupotiburon = new Group();      
        grupotiburon.setLayoutX(SCENE_WIDTH);
        grupotiburon.setLayoutY(365);
        root.getChildren().add(grupotiburon);

        //Rectangulo tiburon
        Rectangle rectangletiburon = new Rectangle();
        rectangletiburon.setWidth(130);
        rectangletiburon.setHeight(60);
        rectangletiburon.setVisible(false);
        grupotiburon.getChildren().add(rectangletiburon);

        //imagen tiburon        
        Image image4 = new Image(getClass().getResourceAsStream("/images/tiburon.png"));
        ImageView imagetiburon = new ImageView(image4);
        imagetiburon.setFitHeight(60);
        imagetiburon.setFitWidth(130);
        grupotiburon.getChildren().add(imagetiburon);

        //grupo del pez 1
        Group grupopez = new Group();
        grupopez.getChildren().add(cuerpopez);
        grupopez.getChildren().add(ojopez);
        grupopez.getChildren().add(pupilapez);
        grupopez.getChildren().add(colapez);
        root.getChildren().add(grupopez);

        //grupo del pez 2
//        Group grupopez2 = new Group();
//        grupopez2.getChildren().add(cuerpopez2);
//        grupopez2.getChildren().add(ojopez2);
//        grupopez2.getChildren().add(pupilapez2);
//        grupopez2.getChildren().add(colapez2);
//        root.getChildren().add(grupopez2);

        // Panel para mostrar los textos (puntuaciones)
        HBox paneTextScore = new HBox();
        paneTextScore.setTranslateY(20);
        paneTextScore.setMinWidth(SCENE_WIDTH);
        paneTextScore.setAlignment(Pos.CENTER);
        root.getChildren().add(paneTextScore);

        // Texto de etiqueta para la puntuación
        Text textTitleScore = new Text("Score: ");
        textTitleScore.setFont(Font.font(TEXT_SIZE));
        textTitleScore.setFill(Color.WHITE);
        // Texto para la puntuación
        textScore = new Text("0");
        textScore.setFont(Font.font(TEXT_SIZE));
        textScore.setFill(Color.WHITE);
        // Texto de etiqueta para la puntuación máxima
        Text textTitleMaxScore = new Text("          Max.Score: ");
        textTitleMaxScore.setFont(Font.font(TEXT_SIZE));
        textTitleMaxScore.setFill(Color.WHITE);
        // Texto para la puntuación máxima
        textHighScore = new Text("0");
        textHighScore.setFont(Font.font(TEXT_SIZE));
        textHighScore.setFill(Color.WHITE);

        // Añadir los textos al panel reservado para ellos 
        paneTextScore.setSpacing(10);
        paneTextScore.getChildren().add(textTitleScore);
        paneTextScore.getChildren().add(textScore);
        paneTextScore.getChildren().add(textTitleMaxScore);
        paneTextScore.getChildren().add(textHighScore);

        // CONTROL DEL TECLADO DEL BUZO
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(final KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {

                    case UP:
                        if (grupopersonaje.getLayoutY() > 0) {
                            // buzoPosY = 0;

                            posicionbuzo -= 10;
                            grupopersonaje.setLayoutY(posicionbuzo);
                        }
                        break;

                    case DOWN:
                        if ((grupopersonaje.getLayoutY() + imageBuzo.getFitHeight()) < 480) {
                            // System.out.println(imageBuzo.getY());
                            posicionbuzo += 10;
                            grupopersonaje.setLayoutY(posicionbuzo);
                        }
                        break;
                }
            }
        });

        Timeline timeline;
        timeline = new Timeline(
                // 0.017 ~= 60 FPS
                new KeyFrame(Duration.seconds(0.017), new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent ae) {

                        //animacion fondo de oceano   
                        posicionFondo--;
                        posicionFondo2--;

                        imageOceano1.setX(posicionFondo);
                        imageOceano2.setX(posicionFondo2);

                        // animacion del fondo 2    
                        if (posicionFondo2 == 0) {
                            posicionFondo = 0;
                            posicionFondo2 = SCENE_WIDTH;
                            imageOceano1.setX(posicionFondo);
                            imageOceano2.setX(posicionFondo2);

                        }
                        //animacion tiburones Y peces 
                        //animacion del tiburon 
                        if (grupotiburon.getLayoutX() > (-image4.getWidth())) {
                            posicionTiburon -= 3;
                            grupotiburon.setLayoutX(posicionTiburon);
                        } else {
                            posicionTiburon = SCENE_WIDTH;
                            grupotiburon.setLayoutX(SCENE_WIDTH);
                        }

                        //animacion del pez 1 (naranja )
                        if (grupopez.getLayoutX() > (-image4.getWidth())) {
                            velocidadpeces -= 3;
                            grupopez.setLayoutX(velocidadpeces);
                        } else {
                            velocidadpeces = SCENE_WIDTH;
                            grupopez.setLayoutX(SCENE_WIDTH);
                        }

                        // animacipon del pez 2 (rosa)
//                        if (grupopez2.getLayoutX() > (-image4.getWidth())) {
//                            velocidadpeces -= 1;
//                            grupopez2.setLayoutX(velocidadpeces);
//                        } else {
//                            velocidadpeces = SCENE_WIDTH;
//                            grupopez2.setLayoutX(SCENE_WIDTH);
//                        }
                        // colision tiburon quita las vidas (son bombonas)
                        Shape shapeCollision = Shape.intersect(rectanglepersonaje, rectangletiburon);
                        boolean colisionVacia = shapeCollision.getBoundsInLocal().isEmpty();
                      
                        
                        if (colisionVacia == false && vidas == 3) {
                            grupotiburon.setLayoutX(SCENE_WIDTH);
                            vidas--;
                            imagebombona1.setVisible(false);
                            System.out.println(vidas);
                        //quiero que el tiburon vuelva a la posicon 640 
                           // grupotiburon.setLayoutX();
                        }
                        if (colisionVacia == false && vidas == 2) {
                            grupotiburon.setLayoutX(SCENE_WIDTH);
                            vidas--;
                            imagebombona2.setVisible(false);
                            System.out.println(vidas);
                        }
                        if (colisionVacia == false && vidas == 1) {
                            grupotiburon.setLayoutX(SCENE_WIDTH);
                            vidas--;
                            imagebombona3.setVisible(false);
                            System.out.println(vidas);
                        }
                        //colision con el pez naranja 
                        Shape shapeCollision2 = Shape.intersect(rectanglepersonaje, cuerpopez);
                        boolean colisionVacia2 = shapeCollision2.getBoundsInLocal().isEmpty();
                        if (colisionVacia2 == false) {
                            direccionpez = 0;

                            System.out.println(colisionVacia);
                            score++;
                            textScore.setText(String.valueOf(score));

                        }

                    }
                }
                )
        );

        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.play();

    }

    public static void main(String[] args) {
        launch();
    }

}
