package currencyconverter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.regex.*;

public class MyFrame extends JFrame implements ActionListener{
	//Initialize Components
	JComboBox nativeCurr, foreignCurr;
	JTextField amount, convertedAmount;
	HashMap<String, Float> rates;
	
	MyFrame(HashMap<String, Float> r){
		rates = r;
		//Edit Frame
		this.setTitle("Currency Calculator");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(450, 200);
		this.setVisible(true);
		this.getContentPane().setBackground(new Color(255,165,0));
		this.setLayout(new FlowLayout());
		
		//this.getContentPane().setBackground(Color.blue); //Change color of background
		ImageIcon image = new ImageIcon("euro.png"); // Creates icon
		this.setIconImage(image.getImage()); //Changes icon 
		
		//Components
		amount = new JTextField("Enter amount");
		amount.addActionListener(this);
		this.add(amount);
		
		TreeSet countries = new TreeSet(rates.keySet());
		
		nativeCurr = new JComboBox(countries.toArray());
		this.add(nativeCurr);
		
		convertedAmount = new JTextField(8);
		convertedAmount.setBackground(Color.gray);
		convertedAmount.setEditable(false);
		convertedAmount.setForeground(Color.white);
		this.add(convertedAmount);
		
		foreignCurr = new JComboBox(countries.toArray());
		this.add(foreignCurr);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Check for valid input
		Pattern p = Pattern.compile("\\d+\\.*\\d*");
		Matcher m = p.matcher(amount.getText());
		
		if(e.getSource() == amount && m.matches()) {
			float nativeAmount = Float.parseFloat(amount.getText());
			float foreignRate = rates.get(foreignCurr.getSelectedItem());
			float nativeRate = rates.get(nativeCurr.getSelectedItem());
			float finalAmount = nativeAmount * (foreignRate/nativeRate);
			convertedAmount.setText(String.valueOf(finalAmount));
		}
	}
}
 