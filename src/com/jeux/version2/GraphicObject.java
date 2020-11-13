package com.jeux.version2;

import javafx.scene.Node;

public class GraphicObject {

	protected Node corps;
	private boolean alive=true;
	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public Node getCorps() {
		return corps;
	}

	public void setCorps(Node corps) {
		this.corps = corps;
	}
	
	public boolean touch(GraphicObject objet){
		return corps.getBoundsInParent().intersects(objet.getCorps().getBoundsInParent());
	}
	
	public boolean isDead(){
		return !alive;
	}
}
