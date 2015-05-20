import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CreateForms  {
	public static void main(String[] args) {
		LoginForm loginForm = new LoginForm();
		loginForm.createLoginForm();
		
		//SendFileEmail s = new SendFileEmail();
		//s.sendEmail();
	}
}