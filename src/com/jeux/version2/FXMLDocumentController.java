package com.jeux.version2;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class FXMLDocumentController {
	int i,j;
	@FXML
	ImageView rotateImage;
	Timeline rotate=new Timeline();
	public FXMLDocumentController(ImageView rotateImage) {
		this.rotateImage=rotateImage;
	
	}

public void rotateImage() {
 DoubleProperty r=rotateImage.rotateProperty();
 rotate.getKeyFrames().addAll(
		 new KeyFrame(new Duration(0),new KeyValue(rotateImage.translateXProperty(), 10)),
		 new KeyFrame(new Duration(9000),new KeyValue(rotateImage.translateXProperty(), 1500))
		 );
 rotate.play();

}
public void rotateImagecontre() {
	 DoubleProperty r=rotateImage.rotateProperty();
	 rotate.getKeyFrames().addAll(
			 new KeyFrame(new Duration(15000),new KeyValue(rotateImage.translateXProperty(), 7000)),
			 new KeyFrame(new Duration(0),new KeyValue(rotateImage.translateXProperty(), 10))
			 
			 );
	 rotate.play();

	}
}