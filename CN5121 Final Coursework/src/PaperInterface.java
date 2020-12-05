import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PaperInterface extends JFrame implements ActionListener
{
	
private JButton topNB= new JButton("show me topN Authors");
private JButton topAB = new JButton("Top Authorship");
private JButton topNGPA = new JButton("Top GPA");
private JTextArea mta = new JTextArea (10,35);

	public PaperInterface()
	{
		setTitle("Paper Sort GUI");
		setLayout(new FlowLayout());
		add(topNB);
		add(topAB);
		add(topNGPA);
		add(mta);
		topNB.addActionListener(this);
		topAB.addActionListener(this);
		topNGPA.addActionListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450,270);
		setLocation(300,300);
		getContentPane().setBackground(Color.BLACK);
		setVisible(true);
		mta.setEditable(false);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		BufferedReader in;
		String line;
		mta.setText("");
		
		if(e.getSource()==topNGPA) {

	try {
		in = new BufferedReader(new FileReader("taske.txt"));
		while((line = in.readLine()) != null)
		{
		    mta.append(line);
		    mta.append("\n");
		}
		in.close();
	}
		catch(IOException ex) {
			System.out.println("Error");
		}
		}
		
		if(e.getSource()==topNB) {
			
		try {
			in = new BufferedReader(new FileReader("topN.txt"));
			while((line = in.readLine()) != null)
			{
			    mta.append(line);
			    mta.append("\n");
			}
			in.close();
		}
			catch(IOException ex) {
				System.out.println("Error");
			}
		}
	
		if(e.getSource()==topAB) {
		try {
			in = new BufferedReader(new FileReader("topN_Aauthorship.txt"));
			while((line = in.readLine()) != null)
			{
			    mta.append(line);
			    mta.append("\n");
			}
			in.close();
		}
			catch(IOException ex) {
				System.out.println("Error");
			}
		}
		
	}
	
	public static void main(String[] args) {
		//new PaperInterface();
	}

	
}
