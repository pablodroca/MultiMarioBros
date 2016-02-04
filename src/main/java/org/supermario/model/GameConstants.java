package org.supermario.model;

public class GameConstants {
	public static final int GAME_LOOP_MS = 1000/25;
	public static final int MARIO_WIDTH = 200;
	public static final int MARIO_HEIGHT = 400;
	public static final int MARIO_WALK_STEP = 20;
	public static final int MARIO_JUMP_STEP = 70;
	public static final int KOOPA_WIDTH = MARIO_WIDTH;
	public static final int KOOPA_HEIGHT = 5 * MARIO_WIDTH / 4;
	public static final int KOOPA_WALK_STEP = 3 * MARIO_WALK_STEP / 4;
	public static final int BLOCK_SIDE_SIZE = MARIO_WIDTH;
	public static final int BOUNDARIES_COLLISION_TOLERANCE = MARIO_WIDTH / 10;
	public static final int BOUNDARIES_RELATIVE_POSITION_TOLERANCE = 5*BOUNDARIES_COLLISION_TOLERANCE;
	public static final int GRAVITY_ACCELERATION = 5;
}
