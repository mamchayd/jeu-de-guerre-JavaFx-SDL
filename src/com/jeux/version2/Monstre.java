package com.jeux.version2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.jeux.version2.Zone;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Monstre extends GraphicObject{
	Image image=null;
	double x,y;
	 private BooleanProperty active = new SimpleBooleanProperty(true);

	    public boolean isActive() {
	        return active.get();
	    }

	    public BooleanProperty activeProperty() {
	        return active;
	    }

	    public void setActive(boolean active) {
	        this.active.set(active);
	    }
	
	
	
	public Monstre(Zone zone){
		

		try {
			image = new Image(new FileInputStream("images/dragon4.gif"),180,90,false,false);
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		corps=new ImageView(image);
		((ImageView)corps).setX(0);
		((ImageView)corps).setY(0);
		//doit appartenir zone
		x=zone.getX1()+(zone.getX2()-zone.getX1())*Math.random();
		y=zone.getY1()+(zone.getY2()-zone.getY1())*Math.random();
		corps.setTranslateX(x);
		corps.setTranslateY(y+70);
		FXMLDocumentController fx=new FXMLDocumentController(((ImageView)corps));
		fx.rotateImage();
	}
	public Image getImage() {
		return image;
	}
	public void setImage(){try {
		 image=new Image(new FileInputStream("images/fire.gif"));
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
				corps=new ImageView(image);
				((ImageView)corps).setX(0);
				((ImageView)corps).setY(0);
			
				
				corps.setTranslateX(this.x);
				corps.setTranslateY(this.y);
	
}
}
