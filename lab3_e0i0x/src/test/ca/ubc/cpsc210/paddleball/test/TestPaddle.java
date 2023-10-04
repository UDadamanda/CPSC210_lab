package ca.ubc.cpsc210.paddleball.test;

import ca.ubc.cpsc210.paddleball.model.PBG;
import ca.ubc.cpsc210.paddleball.model.Puddle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * Unit tests for the Paddle class.
 */
class TestPaddle {
	private static final int XLOC = PBG.DIMENSION1 / 2;
	private Puddle p;
	
	@BeforeEach
	void runBefore() {
		p = new Puddle(XLOC);
	}
	
	@Test
	void testGetX() {
		assertEquals(XLOC, p.getCoordX());
	}
	
	@Test
	void testUpdate() {
		final int NUM_UPDATES = 8;
		
		p.moveOnTick();
		assertEquals(XLOC + Puddle.DX, p.getCoordX());
		
		for(int count = 1; count < NUM_UPDATES; count++) {
			p.moveOnTick();
		}
		
		assertEquals(XLOC + NUM_UPDATES * Puddle.DX, p.getCoordX());
	}
	
	@Test
	void testFlipDirection() {
		p.moveOnTick();
		assertEquals(XLOC + Puddle.DX, p.getCoordX());
		p.moveToLeft();
		p.moveOnTick();
		assertEquals(XLOC, p.getCoordX());
		p.moveToRight();
		p.moveOnTick();
		assertEquals(XLOC + Puddle.DX, p.getCoordX());
	}
	
	@Test 
	void testLeftBoundary() {
		p.moveToLeft();
		for(int count = 0; count < (PBG.DIMENSION1 / 2 - Puddle.DIMENSION1 / 2) / Puddle.DX + 1; count++)
			p.moveOnTick();
		assertEquals(Puddle.DIMENSION1 / 2, p.getCoordX());
		p.moveOnTick();
		assertEquals(Puddle.DIMENSION1 / 2, p.getCoordX());
	}
	
	@Test
	void testRightBoundary() {
		p.moveToRight();
		for(int count = 0; count < (PBG.DIMENSION1 / 2 - Puddle.DIMENSION1 / 2) / Puddle.DX + 1; count++)
			p.moveOnTick();
		assertEquals(PBG.DIMENSION1 - Puddle.DIMENSION1 / 2, p.getCoordX());
		p.moveOnTick();
		assertEquals(PBG.DIMENSION1 - Puddle.DIMENSION1 / 2, p.getCoordX());
	}
}
