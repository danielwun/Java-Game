package org.newdawn.spaceinvaders;

/**
 * An entity which represents one of our space invader aliens.
 * 
 * @author Kevin Glass
 */
public class BossEntity extends Entity {
	/** The speed at which the alient moves horizontally */
	private double moveSpeed = 90;
	/** The game in which the entity exists */
	private Game game;
	private double time = 0 ;
	/** The time at which last fired a shot */
	private long lastFire2 = 0;
	/** The interval between our players shot (ms) */
	private long firingInterval = 500;

	/**
	 * Create a new alien entity
	 * 
	 * @param game The game in which this entity is being created
	 * @param ref The sprite which should be displayed for this alien
	 * @param x The intial x location of this alien
	 * @param y The intial y location of this alient
	 */
	public BossEntity(Game game,String ref,int x,int y) {
		super(ref,x,y);
		this.game = game;
		dx = moveSpeed ;
	}
	// �����ɶ�
	public void setTime( double time ){
		if( time - lastFire2 >= firingInterval ){
			lastFire2 = System.currentTimeMillis();
			game.setBossShot( (int)this.getX()) ;
			//BossShotEntity bossShot = new BossShotEntity(this,"sprites/shot.gif",this.getX(),0);
			//game.setEntities( bossShot ) ;
		}
	}
	public double getTime(){
		return time ;
	} 
	// �R���z��
	public void delete(){}
	/**
	 * Request that this alien moved based on time elapsed
	 * 
	 * @param delta The time that has elapsed since last move
	 */
	public void move(long delta) {
		// if we have reached the left hand side of the screen and
		// are moving left then request a logic update 
		if ((dx < 0) && (x < 10)) {
			game.updateLogic();
		}
		// and vice vesa, if we have reached the right hand side of 
		// the screen and are moving right, request a logic update
		if ((dx > 0) && (x > 750)) {
			game.updateLogic();
		}
		
		// proceed with normal move
		super.move(delta);
	}
	
	/**
	 * Update the game logic related to aliens
	 */
	
	public void doLogic() {
		// swap over horizontal movement and move down the
		// screen a bit
		dx = -dx;
	}
	/**
	 * Notification that this alien has collided with another entity
	 * 
	 * @param other The other entity
	 */
	public void collidedWith(Entity other) {
		// collisions with aliens are handled elsewhere
	}
}