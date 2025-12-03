package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Shooter {
	private Controller controller;
	private int velocity;
	private int originXPos;
	private int originYPos;
	private double angle;
	private double[][] coordinations;
	private int atomToShoot;
	private int powerupToShoot;
	private int L = GameEnvironment.L;
	private int gameWidth;
	private final int height = L;
	private final int width = L / 2;

	/**
	 * @param velocity
	 * @param xPos
	 * @param yPos
	 * @param angleX
	 * @param angleY
	 * @param height
	 * @param width
	 */
	public Shooter(int velocity, int originXPos, int originYPos, Controller controller) {
		this.velocity = velocity;
		this.originXPos = originXPos;
		this.originYPos = originYPos;
		this.angle = 0;
		this.controller = controller;
		this.gameWidth = controller.getGameWidth();

		this.coordinations = new double[4][2];
		this.atomToShoot=0;
		this.powerupToShoot=-1;
		initCoordinations();
	}

	public void initialize(int vel, int orX, int orY, double angle, List<Double> coord, int atomToS, int pwToS) {
		this.velocity = vel;
		this.originXPos = orX;
		this.originYPos = orY;
		this.angle = angle;
		initializeCoordinationsWhenLoading(coord);
		this.atomToShoot = atomToS;
		this.powerupToShoot = pwToS;
	}
	
	private void initializeCoordinationsWhenLoading(List<Double> coord) {
		coordinations[0][0] = coord.get(0);
		coordinations[0][1] = coord.get(1);
		coordinations[1][0] = coord.get(2);
		coordinations[1][1] = coord.get(3);
		coordinations[2][0] = coord.get(4);
		coordinations[2][1] = coord.get(5);
		coordinations[3][0] = coord.get(6);
		coordinations[3][1] = coord.get(7);
		
	}

	private void initCoordinations() {
		// TODO Auto-generated method stub
		coordinations[0][0] = originXPos - width / 2;
		coordinations[0][1] = originYPos - height;
		coordinations[1][0] = originXPos + width / 2;
		coordinations[1][1] = originYPos - height;
		coordinations[2][0] = originXPos + width / 2;
		coordinations[2][1] = originYPos;
		coordinations[3][0] = originXPos - width / 2;
		coordinations[3][1] = originYPos;
	}

	private void moveCoordinationsHorizontally(int l) {
		setOriginXPos(originXPos + l);
		coordinations[0][0] += l;
		coordinations[1][0] += l;
		coordinations[2][0] += l;
		coordinations[3][0] += l;
	}

	private void rotateCoordinations(double angleinDegrees) {
		// TODO Auto-generated method stub
		double angleinRad = Math.toRadians(angleinDegrees);
		double x1 = coordinations[0][0];
		double y1 = coordinations[0][1];
		double x2 = coordinations[1][0];
		double y2 = coordinations[1][1];
		double x3 = coordinations[2][0];
		double y3 = coordinations[2][1];
		double x4 = coordinations[3][0];
		double y4 = coordinations[3][1];
		double newx1 = Math.cos(angleinRad) * (x1 - originXPos) - Math.sin(angleinRad) * (y1 - originYPos) + originXPos;
		double newy1 = Math.sin(angleinRad) * (x1 - originXPos) + Math.cos(angleinRad) * (y1 - originYPos) + originYPos;
		double newx2 = Math.cos(angleinRad) * (x2 - originXPos) - Math.sin(angleinRad) * (y2 - originYPos) + originXPos;
		double newy2 = Math.sin(angleinRad) * (x2 - originXPos) + Math.cos(angleinRad) * (y2 - originYPos) + originYPos;
		double newx3 = Math.cos(angleinRad) * (x3 - originXPos) - Math.sin(angleinRad) * (y3 - originYPos) + originXPos;
		double newy3 = Math.sin(angleinRad) * (x3 - originXPos) + Math.cos(angleinRad) * (y3 - originYPos) + originYPos;
		double newx4 = Math.cos(angleinRad) * (x4 - originXPos) - Math.sin(angleinRad) * (y4 - originYPos) + originXPos;
		double newy4 = Math.sin(angleinRad) * (x4 - originXPos) + Math.cos(angleinRad) * (y4 - originYPos) + originYPos;
		if (checkBoundries(newx1, newx2, newx3, newx4)) {
			angle += angleinDegrees;
			coordinations[0][0] = newx1;
			coordinations[0][1] = newy1;
			coordinations[1][0] = newx2;
			coordinations[1][1] = newy2;
			coordinations[2][0] = newx3;
			coordinations[2][1] = newy3;
			coordinations[3][0] = newx4;
			coordinations[3][1] = newy4;
		}

	}

	private boolean checkBoundries(double x1, double x2, double x3, double x4) {
		return (0 < x1 && x1 < gameWidth) && (0 < x2 && x2 < gameWidth) && (0 < x3 && x3 < gameWidth)
				&& (0 < x4 && x4 < gameWidth);
	}

	public void move(int direction) {
		switch (direction) {
		case 0:
			return;
		case 1:
			if (checkBoundries(coordinations[0][0] + L, coordinations[1][0] + L, coordinations[2][0] + L,
					coordinations[3][0] + L)) {
				moveCoordinationsHorizontally(L/2);
			} else {
				double first = coordinations[1][0];
				double second = coordinations[2][0];
				double max = Math.max(first, second);
				double small = gameWidth - max;
				moveCoordinationsHorizontally((int) small);
			}
			break;
		case -1:
			if (checkBoundries(coordinations[0][0] - L, coordinations[1][0] - L, coordinations[2][0] - L,
					coordinations[3][0] - L)) {
				moveCoordinationsHorizontally(-L/2);
			} else {
				double first = coordinations[0][0];
				double second = coordinations[3][0];
				double small = Math.min(first, second);
				moveCoordinationsHorizontally((int) (-1 * small));
			}
			break;
		default:
		}

	}

	public void rotate(int direction) { // instead of degree direction added

		if (angle + (10 * direction) < -80) {
			angle = -80;
		} else if (angle + (10 * direction) > 80) {
			angle = 80;
		} else {
			rotateCoordinations(10 * direction);
		}

	}

	public int getVelocity() {
		return velocity;
	}

	public int getOriginXPos() {
		return originXPos;
	}

	public int getOriginYPos() {
		return originYPos;
	}

	public double getAngle() {
		return angle;
	}

	public double[][] getCoordinations() {
		return coordinations;
	}

	public int getAtomToShoot() {
		return atomToShoot;
	}

	public int getGameWidth() {
		return gameWidth;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	public void setOriginXPos(int originXPos) {
		this.originXPos = originXPos;
	}

	public void setOriginYPos(int originYPos) {
		this.originYPos = originYPos;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public void setAtomToShoot(int type) {
		this.atomToShoot = type;
	}

	public void setGameWidth(int gameWidth) {
		this.gameWidth = gameWidth;
	}

	public AtomAbstract changeAtom() {
		Random rand = new Random();
		int type = rand.nextInt(4);
		if (controller.isAtomPresent(type, 1)) {
			int atomWidth = this.L/4;
			int atomHeight = this.L/4;
			int xPos=(int)controller.getShooterMiddleXPos()-atomWidth/2;
			int yPos=(int)controller.getShooterMiddleYPos()-atomHeight;
			double angle =controller.getShooterAngle();
			
			AtomAbstract atom = controller.getShowShootableAtom(type);
			atom.setxLoc(xPos);
			atom.setyLoc(yPos);
			atom.setDx(Math.sin(Math.toRadians(angle)));
			atom.setDy(-Math.cos(Math.toRadians(angle)));
		
			setAtomToShoot(type);
			return atom;
		} else if(!controller.isAtomPresent(0, 1) && !controller.isAtomPresent(1, 1)
				&& !controller.isAtomPresent(2, 1) && !controller.isAtomPresent(3, 1)) {
			//error 
			return null;
		} 
		/*else {
			changeAtom();

		}*/
		return changeAtom();
	}
	public AtomAbstract afterShooting(int type) {
		if (controller.isAtomPresent(type, 1)) {
			int atomWidth = this.L/4;
			int atomHeight = this.L/4;
			int xPos=(int)controller.getShooterMiddleXPos()-atomWidth/2;
			int yPos=(int)controller.getShooterMiddleYPos()-atomHeight;
			double angle =controller.getShooterAngle();
			
			AtomAbstract atom = controller.getShowShootableAtom(type);
			atom.setxLoc(xPos);
			atom.setyLoc(yPos);
			atom.setDx(Math.sin(Math.toRadians(angle)));
			atom.setDy(-Math.cos(Math.toRadians(angle)));
		
			setAtomToShoot(type);
			return atom;
		} else if(!controller.isAtomPresent(0, 1) && !controller.isAtomPresent(1, 1)
				&& !controller.isAtomPresent(2, 1) && !controller.isAtomPresent(3, 1)) {
			//error 
			return null;
		} 
		/*else {
			changeAtom();

		}*/
		return changeAtom();
	}
	
	public void pickPowerUp(int n) {
		if(controller.isPowerupPresent(n)) {
			powerupToShoot = n;
		}
	}
	
	public void afterShootingPowerup() {
		powerupToShoot = -1;
	}
	


	public double getShotableXPos() {
		return (coordinations[0][0] + coordinations[1][0]) / 2;
	}

	public double getShotableYPos() {
		return (coordinations[0][1] + coordinations[1][1]) / 2;
	}

	public int getPowerupToShoot() {
		return powerupToShoot;
	}

	public void setPowerupToShoot(int powerupToShoot) {
		this.powerupToShoot = powerupToShoot;
	}

	public int[] getXpoints() {
		int[] xPoints = { (int) coordinations[0][0], (int) coordinations[1][0], (int) coordinations[2][0],
				(int) coordinations[3][0] };
		return xPoints;
	}

	public int[] getYpoints() {
		int[] yPoints = { (int) coordinations[0][1], (int) coordinations[1][1], (int) coordinations[2][1],
				(int) coordinations[3][1] };
		return yPoints;
	}


	public boolean isCollidedwithPowerup(DroppingPowerUp powerup) {


		int powerup_x = powerup.getxLoc();
		int powerup_y = powerup.getyLoc();
		double powerup_width = powerup.getWidth();
		double powerup_height = powerup.getHeight();
		
		double shooter_0x = coordinations[0][0];
		double shooter_0y = coordinations[0][1];
		double shooter_1x = coordinations[1][0];
		double shooter_1y = coordinations[1][1];
		double shooter_2x = coordinations[2][0];
		double shooter_2y = coordinations[2][1];
		double shooter_3x = coordinations[3][0];
		double shooter_3y = coordinations[3][1];

		double x_min = Math.min(shooter_0x, shooter_3x);
		double x_max = Math.max(shooter_1x, shooter_2x);
		double y_max = Math.max(shooter_0y, shooter_1y);
		
		if ((powerup_x <= x_max) && (powerup_x >= x_min) && (powerup_y + powerup_height >= y_max)) {
			return true;
		}
		else if ((powerup_x + powerup_width <= x_max) && (powerup_x + powerup_width >= x_min) && (powerup_y + powerup_height >= y_max)) {
			return true;
		}
		else {
			return false;
		}
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	
}