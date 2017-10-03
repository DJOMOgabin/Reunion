package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;

public class DateChooser {

	private JPanel total = new JPanel();
	private JPanel paneLabelNom = new JPanel();
	private JPanel paneTextNom = new JPanel();
	private JLabel labelNom;
	private JDateChooser date = new JDateChooser();

	//Un panel qui contient la possibilité de choisir une date
	public DateChooser(String nom){
		
		labelNom = new JLabel(nom);
		date = new JDateChooser();
		labelNom.setForeground(Color.gray);
		date.setPreferredSize(new Dimension(400,35));
		labelNom.setPreferredSize(new Dimension(400,10));
		paneLabelNom.setLayout(new FlowLayout(FlowLayout.CENTER));
		paneTextNom.setLayout(new FlowLayout(FlowLayout.CENTER));
		labelNom.setLayout(new FlowLayout(FlowLayout.CENTER));
		paneLabelNom.add(labelNom);
		paneTextNom.add(date);
		date.setBackground(Color.blue);
		total.add(paneLabelNom);
		total.add(paneTextNom);
		
	}

	public JPanel getTotal() {
		return total;
	}

	public void setTotal(JPanel total) {
		this.total = total;
	}

	public JPanel getPaneLabelNom() {
		return paneLabelNom;
	}

	public void setPaneLabelNom(JPanel paneLabelNom) {
		this.paneLabelNom = paneLabelNom;
	}

	public JPanel getPaneTextNom() {
		return paneTextNom;
	}

	public void setPaneTextNom(JPanel paneTextNom) {
		this.paneTextNom = paneTextNom;
	}

	public JLabel getLabelNom() {
		return labelNom;
	}

	public void setLabelNom(JLabel labelNom) {
		this.labelNom = labelNom;
	}

	public JDateChooser getDate() {
		return date;
	}

	public void setDate(JDateChooser date) {
		this.date = date;
	}

}
