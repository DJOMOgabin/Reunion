package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

//Un panel qui retourne un text et un bouton ci-contre
public class LabelAndButton {
	private JPanel[] panel = new JPanel[3];
	private JButton bouton;
	private JLabel text;
	
	public LabelAndButton(String nomText,String nomButton){
		for(int i=0;i<getPanel().length;i++){
			getPanel()[i]=new JPanel();
		}
		bouton = new JButton(nomButton);
		text = new JLabel(nomText);
		
		bouton.setBackground(Color.blue);
		getPanel()[1].setLayout(new FlowLayout(FlowLayout.CENTER));
		getPanel()[2].setLayout(new FlowLayout(FlowLayout.CENTER));
		
		getPanel()[1].add(getText());
		getPanel()[2].add(getBouton());		
		
		
		getPanel()[0].add(getPanel()[1]);
		getPanel()[0].add(getPanel()[2]);
		
		getText().setPreferredSize(new Dimension(290,40));
		getBouton().setPreferredSize(new Dimension(100,40));
		
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

	public JLabel getText() {
		return text;
	}

	public void setText(JLabel text) {
		this.text = text;
	}
	
	
}
