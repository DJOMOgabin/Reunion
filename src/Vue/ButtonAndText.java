package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

//Cette classe permet de créer un panel qui contient à la fois un champs pour remplir de valeurs
//et un bouton soumettre 
public class ButtonAndText {
	private JPanel[] panel = new JPanel[3];
	private JButton bouton;
	private JTextField text;
	
	public ButtonAndText(String nomText,String nomButton){
		for(int i=0;i<getPanel().length;i++){
			getPanel()[i]=new JPanel();
		}
		bouton = new JButton(nomButton);
		text = new JTextField(nomText);
		getText().setForeground(Color.gray);
		
		bouton.setBackground(Color.blue);
		getPanel()[1].setLayout(new FlowLayout(FlowLayout.CENTER));
		getPanel()[2].setLayout(new FlowLayout(FlowLayout.CENTER));
		
		getPanel()[1].add(getText());
		getPanel()[2].add(getBouton());		
		
		
		getPanel()[0].add(getPanel()[1]);
		getPanel()[0].add(getPanel()[2]);
		
		getText().setPreferredSize(new Dimension(290,40));
		getBouton().setPreferredSize(new Dimension(100,40));
		
		//Le focus listener
		getText().addFocusListener(new FocusListener() {
					
					String nom = getText().getText();
					
					public void focusLost(FocusEvent arg0) {
						System.out.println(nom);
						if(text.getText().equals("")){
							text.setText(nom);
							text.setForeground(Color.gray);
						}
						
					}
					
					public void focusGained(FocusEvent arg0) {
						System.out.println(nom);
						if(text.getText().equals(nom)&&text.getForeground()==Color.gray){
							text.setText("");
							text.setForeground(Color.black);					
						}
						
					}
				});
	}

	public JPanel[] getPanel() {
		return panel;
	}

	public void setPanel(JPanel[] panel) {
		this.panel = panel;
	}

	public JButton getBouton() {
		return bouton;
	}

	public void setBouton(JButton bouton) {
		this.bouton = bouton;
	}

	public JTextField getText() {
		return text;
	}

	public void setText(JTextField text) {
		this.text = text;
	}
	
	
}
