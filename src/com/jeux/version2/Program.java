package com.jeux.version2;

import java.io.File;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.jeux.version2.Zone;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Program extends Application  {
	private int time=900;
	private int vie=0;
	private int score=0;

	//element de linterace graphique

	//private HBox hbox=new HBox();
	private GridPane grid=new GridPane();
	private Label lblScor;
	private Label lblTime= new Label("   " + time);;
		
	private double widthWindow=1000;
	private double heightWindow=700;
	private Pane container=new Pane();
	Line line=new Line(0,200,widthWindow,200);
	Zone zone1=new Zone(30, 50,line.getEndX(),line.getEndY()-50);
	Zone zone2=new Zone(line.getStartX()+60,line.getStartY()-50,line.getEndX()-40,heightWindow-50);
	//Zone zone3=new Zone(0,30,line2.getEndX(),30);	

	//les objet de jeux	
	private Player player=new Player(zone2);	
	private Monstre monstre=new Monstre(zone1);
	private List<Monstre> monstres=new ArrayList<>();
	private List<Balle> balles=new ArrayList<Balle>();
	private List<Balle> balles1=new ArrayList<Balle>();
	private Arme arme=new Arme(player);
	private Arme arme2=new Arme(monstre);
	
	
	//annimationTimern
	AnimationTimer animation=new AnimationTimer() {

		@Override
		public void handle(long now) {
			// TODO Auto-generated method stub
			refrechContent();

		}
	};


	//event handler

	EventHandler<KeyEvent>event=new EventHandler<KeyEvent>() {

		@Override
		public void handle(KeyEvent event) {
			// TODO Auto-generated method stub
			if(event.getCode()==KeyCode.X){
				arme.rotateLefte();
			}
			if(event.getCode()==KeyCode.W){
				arme.rotateRight();
			}
			//tirere des balle espace
			if(event.getCode()==KeyCode.SPACE){				
				Balle balle=new Balle(arme);
				container.getChildren().add(balle.getCorps());
				balles.add(balle);
			}
			//deplacer player a gauche
			if(event.getCode()==KeyCode.LEFT){
				player.getCorps().setTranslateX(player.getCorps().getTranslateX()-5);
				arme.attachToPlayer(player);
			}
			//deplacer player a droit
			if(event.getCode()==KeyCode.RIGHT){
				player.getCorps().setTranslateX(player.getCorps().getTranslateX()+5);
				arme.attachToPlayer(player);
			}
			if(event.getCode()==KeyCode.UP){
				player.getCorps().setTranslateY(player.getCorps().getTranslateY()-5);
				arme.attachToPlayer(player);

				 
				/*arme.rotateRight();
				arme.getCorps().setTranslateY(arme.getCorps().getTranslateY()-5);
				arme.attachToPlayer(player);*/

			}
			if(event.getCode()==KeyCode.DOWN){
				player.getCorps().setTranslateY(player.getCorps().getTranslateY()+5);
				arme.attachToPlayer(player);

				/*arme.rotateLefte();
				arme.getCorps().setTranslateY(arme.getCorps().getTranslateY()+5);
				arme.attachToPlayer(player);*/
			}

		}
	};	
	private void refrechContent(){
		//parcourir la collection des balle:mettre ajour leur position

		if(Math.random()<0.03) {
		time--;
		}
		lblTime.setText("   "+time);
		for(Balle balle:balles){
			for(Monstre monstre:monstres){
				if(balle.touch(monstre)){
					container.getChildren().removeAll(balle.getCorps(),monstre.getCorps());
					balle.setAlive(false);
					monstre.setAlive(false);
					this.score++;
					lblScor.setText("    "+score);
				}
			}
		}

		monstres.removeIf(GraphicObject::isDead);
		balles.removeIf(GraphicObject::isDead);
		for(Balle balle:balles1){
			if(balle.touch(player)){
				container.getChildren().removeAll(balle.getCorps());
				
				balle.setAlive(false);
				player.setAlive(false);
				
				ColorAdjust colorAdjust = new ColorAdjust(); 
			      colorAdjust.setContrast(-10);
			      colorAdjust.setHue(-10);
			      colorAdjust.setBrightness(-1);  
			      
			      colorAdjust.setSaturation(2);   
			      ((ImageView) grid.getChildren().get(this.vie)).setEffect(colorAdjust);  
				this.vie++;
			
				
			}
		}
	if(this.vie>=3){
		balles1.forEach(b -> {
			container.getChildren().removeAll(b.getCorps());
		});
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Game Over! Votre score est :"+score);
			animation.stop();
			alert.show();
		}
		
		balles1.removeIf(GraphicObject::isDead);
		
		for(Balle balle : balles){
			balle.update();
		}
		
		for(Balle balle : balles1){
			balle.update();
		}
		
		if(Math.random()<0.01){//configurere la vitess de aparess de monstre
			if(monstres.size()<40){
				Monstre m=new Monstre(zone1);
				container.getChildren().add(m.getCorps());
				monstres.add(m);
			}

		}
		if(Math.random()<0.01) {
			for(Monstre monster:monstres) {
				Balle balle1=new Balle(monster);
				container.getChildren().add(balle1.getCorps());
				balles1.add(balle1);}

		}



	}

	public static void main(String[] args) {
		//launch methode abstract
		Application.launch(args);//affich fenetre 

	}


	//ajouter element graphique
	private void createContante(){

		lblTime=new Label("   "+this.time);
		lblTime.setFont(new Font("Arial", 20));
		lblTime.setTextFill(Color.web("#A9F5F2"));
		lblScor=new Label("    "+this.score);
		lblScor.setFont(new Font("Arial", 20));
		lblScor.setTextFill(Color.web("#A9F5F2"));
		Image dia1=new Image("file:images/diamon.png");
		Image dia2=new Image("file:images/diamon.png");
		Image dia3=new Image("file:images/diamon.png");
		
		Image scor=new Image("file:images/firediamon.png");
		ImageView mvdiamon=new ImageView(dia1);
		ImageView mvdiamon2=new ImageView(dia2);
		ImageView mvdiamon3=new ImageView(dia3);
		ImageView mvscore=new ImageView(scor);
	

		//hbox.getChildren().add(mvdiamon);
		grid.add(mvdiamon,1,1);
		grid.add(mvdiamon2,2,1);
		grid.add(mvdiamon3,3,1);
		grid.add(lblTime, 4, 1);
		grid.add(lblScor, 5, 1);
		grid.add(mvscore, 6, 1);
		lblScor.setTranslateX(667);
		mvscore.setTranslateX(670);
		if(this.vie==1){
			mvdiamon3=new ImageView(new Image("file:images/fire.png"));
		}
	
		Image image=new Image("file:back.png");

		ImageView mv=new ImageView(image);
		mv.setFitHeight(660);
		mv.setFitWidth(widthWindow);
		container.getChildren().addAll(mv,grid);

		container.getChildren().add(line);
		container.getChildren().add(player.getCorps());
		container.getChildren().add(arme.getCorps());
		container.getChildren().add(arme.getSortie());


	}


	
	@Override
	public void start(Stage window) throws Exception {


		window.setWidth(widthWindow);
		window.setHeight(heightWindow);	
		//attribuer un titre a nous fenetre 
		window.setTitle("Jeu de guerre");
		//container.getChildren().add(imageView);

		createContante();

		//definir le pan qui contient autre element graphiq
		Scene scene=new Scene(container);


		window.setScene(scene);//atacher la scene a la ffenetre

		animation.start();
		scene.setOnKeyPressed(event);
		window.show(); 
	}

}








