package it.unibo.radarSystem22.domain.mock;

import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.model.LedModel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LedMockWithGUI extends LedModel implements ILed, ActionListener {

	// followed tutorial
	// https://www.tutorialsfield.com/jbutton-click-event/

	private JFrame frame = new JFrame();
	private JButton button = new JButton("Spento");

	public LedMockWithGUI() {
		super();
		prepareGUI();
		buttonProperties();
	}

	private void prepareGUI() {
		frame.setTitle("Led Gui");
		frame.getContentPane().setBackground(Color.blue);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setBounds(200, 200, 400, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void buttonProperties() {
		button.setBounds(130, 200, 100, 40);
		frame.add(button);
		button.addActionListener(this);
	}

	@Override
	protected void ledActivate(boolean value) {
		System.out.println("led activate, state " + value);
		if (value) {
			button.setText("Acceso");
			frame.getContentPane().setBackground(Color.pink);
		} else {
			button.setText("Spento");
			frame.getContentPane().setBackground(Color.blue);

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// il bottone ad ogni pressione cambia lo stato del led
		System.out.println("action performed, state " + getState());
		if (getState())
			turnOff();
		else
			turnOn();
	}

	public static void main(String[] args) {

		new LedMockWithGUI();
	}
}
