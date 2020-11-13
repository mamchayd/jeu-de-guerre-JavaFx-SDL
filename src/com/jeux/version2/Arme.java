package com.jeux.version2;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Arme {

	private Rectangle corps=new Rectangle(-6,0,7,40);
	private Circle sortie =new Circle(0,0,4);


	public Arme(GraphicObject player){
		corps.setTranslateX(player.getCorps().getTranslateX());
		corps.setTranslateY(player.getCorps().getTranslateY());
		corps.setFill(Color.DARKGREEN);
		sortie.setFill(Color.BROWN);
		updateSortie();
	}


	public void updateSortie(){
		sortie.setTranslateX(corps.getTranslateX()-2);
		sortie.setTranslateY(corps.getTranslateY()+25);
	}
	public Rectangle getCorps() {
		return corps;
	}
	public void setCorps(Rectangle corps) {
		this.corps = corps;
	}
	public Circle getSortie() {
		return sortie;
	}

	public void setSortie(Circle sortie) {
		this.sortie = sortie;
	}
	//sur x:tourner vers adroit 
	public void rotateRight(){
		corps.setRotate(corps.getRotate()-5);
	}
	//sur w :tourner vers la gauche
	public void rotateLefte(){
		corps.setRotate(corps.getRotate()+5);
	}
	public double getRotate(){
		return corps.getRotate()-90;
	}
	public void attachToPlayer(Player player){
		corps.setTranslateX(player.getCorps().getTranslateX());
		corps.setTranslateY(player.getCorps().getTranslateY());
		updateSortie();
	}

	public void attachToMonst(Monstre monstre){
		corps.setTranslateX(monstre.getCorps().getTranslateX());
		corps.setTranslateY(monstre.getCorps().getTranslateY());
		updateSortie();
	}


}

