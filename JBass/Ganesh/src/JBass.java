
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

public class JBass { 
	private File ipFile=null;
	
	public JBass(){
		DOfIO.intialize();
		
		JFrame mainFrame=new JFrame("JBass XPlorer");
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout());
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel p=new JPanel();
		p.setLayout(new GridLayout(10,1));
		p.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel ipFileLabel=new JLabel("File Selected : nothing",JLabel.CENTER);
		JLabel ipFileStatus=new JLabel("*note: output will be generated in input file directory", JLabel.CENTER);
		
		JLabel disclaimer= new JLabel("nothing much :)",JLabel.CENTER);
		disclaimer.setForeground(Color.GREEN.darker());
		
		final JFileChooser  ipfileChoose = new JFileChooser();
	    JButton ipFileSelect = new JButton("Select File");
	    ipFileSelect.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	            int returnVal = ipfileChoose.showOpenDialog(p);
	            if (returnVal == JFileChooser.APPROVE_OPTION) {
	               ipFile = ipfileChoose.getSelectedFile();
	               ipFileLabel.setText("File Selected : " 
	               + ipFile.getName());
	               disclaimer.setForeground(Color.GREEN.darker());
	               disclaimer.setText("File has been selected :)" );
	            }
	            else{
	            	if(ipFile==null){
	            		disclaimer.setForeground(Color.RED.darker());
	            		disclaimer.setText("File not selected :|" );
	            	}           
	            }      
	         }
	      });
	    JPanel p1=new JPanel();
	    p1.setLayout(new GridLayout(1,2));
		p1.setBorder(new EmptyBorder(5, 5, 5, 5));
		p1.add(ipFileSelect);
		p1.add(ipFileStatus);
		
		JLabel mapl= new JLabel("Maximum Asymmetric Pattern Length: ",JLabel.RIGHT);
		SpinnerModel sm1 = new SpinnerNumberModel(16, 1, 64, 1);
		JSpinner maplSpinner = new JSpinner(sm1);
		JPanel p2=new JPanel();
	    p2.setLayout(new GridLayout(1,2));
		p2.setBorder(new EmptyBorder(5, 5, 5, 5));
		p2.add(mapl);
		p2.add(maplSpinner);
		
		JLabel mlpbr= new JLabel("Maximum Length of Pattern Being Replaced: ",JLabel.RIGHT);
		SpinnerModel sm2 = new SpinnerNumberModel(16, 1, 128, 1);
		JSpinner mlpbrSpinner = new JSpinner(sm2);
		JPanel p3=new JPanel();
	    p3.setLayout(new GridLayout(1,2));
		p3.setBorder(new EmptyBorder(5, 5, 5, 5));
		p3.add(mlpbr);
		p3.add(mlpbrSpinner);
	    
		JLabel mc= new JLabel("Maximum Cycles: ",JLabel.RIGHT);
		SpinnerModel sm3 = new SpinnerNumberModel(5, 1, 30, 1);
		JSpinner mcSpinner = new JSpinner(sm3);
		JPanel p4=new JPanel();
	    p4.setLayout(new GridLayout(1,2));
		p4.setBorder(new EmptyBorder(5, 5, 5, 5));
		p4.add(mc);
		p4.add(mcSpinner);
	    
	    JSwitchBox jsb1=new JSwitchBox("   compress   ","   decompress   ");
	    //JSwitchBox jsb2=new JSwitchBox("with Shuffling","without Shuffling");
	    /*JPanel p5=new JPanel();
	    p5.setLayout(new GridLayout(1,2));
		p5.setBorder(new EmptyBorder(5, 5, 5, 5));
		p5.add(jsb1);
		p5.add(jsb2);*/
	    
	    JButton go=new JButton("Go");
	    go.setBackground(Color.GREEN.darker());
	    go.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		System.out.println("go pressed!");
	    		disclaimer.setText("have a cup of coffee, it might take some time :)");
    			disclaimer.setForeground(Color.GREEN.darker());
	    		
	    		if(ipFile!=null){
					if(jsb1.isSelected()){
						//compress
						Bass bass=new Bass(Integer.parseInt(maplSpinner.getValue().toString()), Integer.parseInt(mlpbrSpinner.getValue().toString()), Integer.parseInt(mcSpinner.getValue().toString()));
						try{
							bass.Compress(ipFile);
							disclaimer.setText("Compressed file has been generated :)");
			    			disclaimer.setForeground(Color.GREEN.darker());
						}catch(Exception ex){
							disclaimer.setText("Well this is Embarrassing, something went wrong :(");
			    			disclaimer.setForeground(Color.RED.darker());
						}
					}else{
						//decompress
						Bass bass=new Bass();
		    			try{
							bass.Decompress(ipFile);
							disclaimer.setText("Decompressed file has been generated :)");
			    			disclaimer.setForeground(Color.GREEN.darker());
						}catch(Exception ex){
							disclaimer.setText("Well this is Embarrassing, something went wrong :(");
			    			disclaimer.setForeground(Color.RED.darker());
						}
					}
	    		}else{
	    			disclaimer.setText("Please select input file :(");
	    			disclaimer.setForeground(Color.RED.darker());
	    		}
	    	} 
	    });
	    
	    JLabel notifier1=new JLabel("*note : select spinner fields in their range, otherwise default values will be taken regardless",JLabel.CENTER);
	    JLabel notifier2=new JLabel("*note : increasing the values in spinners increases the compression ratio, so that file can be compressed more",JLabel.CENTER);
	    
	    p.add(ipFileLabel);
	    p.add(p1);
	    p.add(notifier1);
	    p.add(notifier2);
	    p.add(p2);
	    p.add(p3);
	    p.add(p4);
	    p.add(jsb1);
	    p.add(go);
	    p.add(disclaimer);
	    
	    panel.add(p);
	    
	    mainFrame.setSize(670, 300);
		mainFrame.add(panel);
		mainFrame.setVisible(true);
	}
	
	public static void main(String[] args){
		new JBass();
	}
}
