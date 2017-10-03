package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

//Un panel qui nous retourne un champ de text pré-dimensionné
public class Text {

	private JPanel panel = new JPanel();
	private JTextField text = new JTextField();

	public Text(String nom){
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		text.setText(nom);
		panel.add(text);
		text.setForeground(Color.gray);
		getText().setPreferredSize(new Dimension(400,40));
		
		//Le focus listener
		text.addFocusListener(new FocusListener() {
			
			String nom = getText().getText();
			public void focusLost(FocusEvent arg0) {
				if(text.getText().equals("")){
					text.setText(nom);
					text.setForeground(Color.gray);
				}
				
			}
			
			public void focusGained(FocusEvent arg0) {
				if(text.getText().equals(nom)&&text.getForeground()==Color.gray){
					text.setText("");
					text.setForeground(Color.black);					
				}
				
			}
		});
		
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JTextField getText() {
		return text;
	}

	public void setText(JTextField text) {
		this.text = text;
	}
	
}
