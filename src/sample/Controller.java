package sample;

import javafx.animation.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Ashutosh
 */
public class Controller implements Initializable {

    @FXML
    private ImageView imageView;


    @FXML
    private Button selectImage;

    @FXML
    private Slider slider;

    Image or;

    @FXML
    void openImageSelection(ActionEvent event) {//choosing the image from the local space
        FileChooser fileChooser=new FileChooser();
        File f= fileChooser.showOpenDialog(null);
        try {
           BufferedImage originalImage= ImageIO.read(f);
           or= SwingFXUtils.toFXImage(originalImage,null);
            imageView.setImage(or);




        }catch (Exception e){
            System.out.println(e);

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageView.setFitHeight(785.0);
        imageView.setFitWidth(1194.0);
      }

    private Image enableNoise(Image image){//setting the nosie of the image
        PixelReader pixelReader=image.getPixelReader();
        int w=(int)image.getWidth();
        int h=(int)image.getHeight();

        WritableImage image1=new WritableImage(w,h);
        PixelWriter pixelWriter=image1.getPixelWriter();

        for (int i=0; i<h; i++){
            for (int j=0; j<w; j++){
                Color color=pixelReader.getColor(j,i);
                double noise=Math.random()/1;


                Color newColor=new Color(
                        Math.min(color.getRed()+noise,1),
                        Math.min(color.getGreen()+noise,1),
                        Math.min(color.getBlue()+noise,1),
                        1);

                pixelWriter.setColor(j,i,newColor);
            }
        }
        return image1;
    }

    @FXML
    void slide(MouseEvent event) {//Adjusting the noise of the image
       Image modifed=enableNoise(or);
        imageView.setImage(modifed);
    }

    @FXML
    void exit(ActionEvent event) {//Action for exit
      System.exit(0);
    }


    @FXML
    void fadein(ActionEvent event) {//Transition apply on picture open

           FadeTransition ft=new FadeTransition(Duration.millis(3000),imageView);
            ft.setFromValue(1);
            ft.setToValue(0.1);
            ft.setCycleCount(Timeline.INDEFINITE);
            ft.setAutoReverse(true);
            ft.play();

    }






}
