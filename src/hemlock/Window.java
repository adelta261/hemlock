package hemlock;

import java.io.Serializable;

public class Window implements Serializable {

	private boolean open;
	private boolean caught;
	private int turns;
	private int turnsLeft;
	
	public void windowDefault() {
		this.caught = false;
		this.open = false;
		this.turns = 0;
		this.turnsLeft = 0;
	}
	
	//setters
	
	public void setOpen(boolean open) {
		this.open = open;
	}
	
	public void setCaught(boolean caught) {
		this.caught = caught;
	}
	
	public void setTurns(int turns) {
		this.turns = turns;
	}
	
	public void setTurnsLeft(int turnsLeft) {
		this.turnsLeft = turnsLeft;
	}
	
	//getters
	
	public boolean getOpen() {
		return this.open;
	}
	
	public boolean getCaught() {
		return this.caught;
	}
	
	public int getTurns() {
		return this.turns;
	}
	
	public int getTurnsLeft() {
		return this.turnsLeft;
	}
	
}
