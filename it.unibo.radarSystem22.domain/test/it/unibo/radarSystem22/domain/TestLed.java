package it.unibo.radarSystem22.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.*;

import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.mock.LedMock;

public class TestLed {
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println("[new]");
		
	}
	
	@AfterClass
	public static void afterClass() {
		
		System.out.println("[end]");
	}
	
	
	@Before
	public void before() {
		System.out.println("up");
	}
	
	@After
	
	public void after() {
		
		System.out.println("down");
	}

	@Test
	public void testLedMockINIT() {

		ILed led = new LedMock();
		assertFalse(led.getState());
	}

	@Test
	public void testLedMockON() {
		ILed led = new LedMock();
		assertFalse(led.getState());
		led.turnOn();
		assertTrue(led.getState());
	}

	@Test
	public void testLedMockOFF() {
		ILed led = new LedMock();
		assertFalse(led.getState());
		led.turnOn();
		assertTrue(led.getState());
		led.turnOff();
		assertFalse(led.getState());

	}

	@Test
	
	public void wrongTest() {
		
		assertFalse(true);
	}
}
