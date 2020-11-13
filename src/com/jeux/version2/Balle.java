package com.jeux.version2;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Balle extends GraphicObject {
	
	private Point2D direction=new Point2D(0,0);
	private Point2D dir;
	
	public Balle(Arme arme){
		corps=new Circle(0,0,4,Color.BLUE);
		corps.setTranslateX(arme.getSortie().getTranslateX());
		corps.setTranslateY(arme.getSortie().getTranslateY());
		updateDirection(arme.getRotate());
	}
	public Balle(Monstre monstre){
		Image img=new Image("file:images/fire.png");
		corps=new Circle(1,2,4,Color.YELLOW);
		ImageView mv=new ImageView(img);
		corps=mv;
		corps.setTranslateX(monstre.getCorps().getTranslateX()+100);
		corps.setTranslateY(monstre.getCorps().getTranslateY()+70);
	    double x=Math.cos(Math.toRadians(90));
	    double y=Math.sin(Math.toRadians(90));
	    dir=new Point2D(x, y);
	    direction=dir.normalize().multiply(7);
		//updateDirection(monstre.getRotate());
	}
	
	private void updateDirection(double rotation){
		Point2D p;
	//la direction de la balle
		double x=Math.cos(Math.toRadians(rotation));
		double y=Math.sin(Math.toRadians(rotation));
		p=new Point2D(x, y);
		direction=p.normalize().multiply(7);
	}
	public void update(){
		corps.setTranslateX(corps.getTranslateX()+direction.getX());
		corps.setTranslateY(corps.getTranslateY()+direction.getY());
	}
}
