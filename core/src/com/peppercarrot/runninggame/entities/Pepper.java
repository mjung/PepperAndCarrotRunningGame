package com.peppercarrot.runninggame.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Align;
import com.nGame.utils.scene2d.AnimatedDrawable;
import com.nGame.utils.scene2d.AnimatedImage;
import com.peppercarrot.runninggame.utils.Assets;

/**
 * Witch Pepper. Modify her abilities in initAbilities() function.
 * 
 * @author WinterLicht
 *
 */
public class Pepper extends Runner {
	// TODO: duration of all skills here?
	private static float SWEEP_DURATION = 0.6f;

	public Pepper(String name) {
		super(name);
	}

	@Override
	protected void initAnimations() {
		runningAnim = new AnimatedImage(new AnimatedDrawable(
				new Animation(0.079f, Assets.I.getRegions(name + "_run"), Animation.PlayMode.LOOP)));
		runningAnim.setOrigin(Align.center);
		jumpingAnim = new AnimatedImage(new AnimatedDrawable(
				new Animation(0.144f, Assets.I.getRegions(name + "_jump"), Animation.PlayMode.LOOP_PINGPONG)));
		jumpingAnim.setOrigin(Align.center);
		doubleJumpingAnim = new AnimatedImage(new AnimatedDrawable(
				new Animation(0.144f, Assets.I.getRegions(name + "_doublejump"), Animation.PlayMode.LOOP_PINGPONG)));
		doubleJumpingAnim.setOrigin(Align.center);
		fallingAnim = new AnimatedImage(new AnimatedDrawable(
				new Animation(0.14f, Assets.I.getRegions(name + "_fall"), Animation.PlayMode.LOOP_PINGPONG)));
		fallingAnim.setOrigin(Align.center);
		attackingAnim = new AnimatedImage(new AnimatedDrawable(
				new Animation(SWEEP_DURATION / 8, Assets.I.getRegions(name + "_attack"), Animation.PlayMode.NORMAL)));
		attackingAnim.setOrigin(Align.center);
		hitAnim = new AnimatedImage(new AnimatedDrawable(
				new Animation(0.5f, Assets.I.getRegions(name + "_hit"), Animation.PlayMode.NORMAL)));
		hitAnim.setOrigin(Align.center);
	}

	@Override
	public void act(float delta){
		super.act(delta);
		//
		if (ability2.isRunning()) {
			doubleJumpingAnim.act(delta);
			runnerImage.setDrawable(doubleJumpingAnim.getDrawable());
			pet.setDoubleJumping();
		}
	}
	
	@Override
	protected void initPet() {
		pet = new Carrot("carrot", this);
	}

	@Override
	protected void initAbilities() {
		ability1 = new CarrotCharge(this, 2);
		//ability2 =new ProjectileAttack(this, 2, SWEEP_DURATION); 
		ability2 = new TimeDistortion(this, 0, 1.6f, 2.8f);
		ability3 = new BlackHole(this, 2, 1.5f);
		ability0 = new SweepAttack(this, 0, SWEEP_DURATION);
	}

}
