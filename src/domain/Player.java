package domain;

import java.sql.Time;

public class Player {
	private double health;
	private double time;
	private double score;

	public Player() {
		this.health=100;
		this.score=0;
		this.time =600;
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public double getScore() {
		return Math.round(score * 100.0) / 100.0;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
	public void updateHealth(double healthEffect) {
		this.setHealth(healthEffect+ this.getHealth());
	}
	
	public void updateScore(double scoreEffect) {
		this.setScore(scoreEffect+ this.getScore());
	}
	
	public void updateTime(double timeEffect) {
		this.setTime(timeEffect + this.getTime());
	}
	
}
