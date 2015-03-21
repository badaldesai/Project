//import statement for ActionListener and GUI
import javax.swing.*;
import java.awt.event.*;

//Implement the interface for button will give events
public class SimpleGui implements ActionListener {
	JButton button;
	
	public static void main(String[] args) {
		SimpleGui gui = new SimpleGui();
		gui.go();
	}
	
	public void go(){
		JFrame frame = new JFrame();
		button = new JButton("click me");
		
		//register your interest with the button, this says add me to your list of listener
		button.addActionListener(this);
		
		frame.getContentPane().add(button);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300,300);
		frame.setVisible(true);
	}
	//Implement the Action Listener interface, this is actual event handling method.
	public void actionPerformed(ActionEvent event){
		button.setText("I've been clicked");
	}
}
