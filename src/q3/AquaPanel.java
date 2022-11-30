package q3;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CyclicBarrier;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
/**
 * The department that creates the main panel is called AquaPanel
 *  and is responsible for all the arrangement 
 * of the buttons and the drawing of the fish.
 *
 * @authors Eden Barsheshet ID: 203531918 Toli Kot ID: 324413756
 * 
 * @see 
 */

@SuppressWarnings("deprecation")
public class AquaPanel extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	final HashSet<Swimmable> Animals = new HashSet<Swimmable>();
	final HashSet<SeaCreature> Plants = new HashSet<SeaCreature>();
	public JPanel p1, p2;
	private JButton[] b_num;
	private String[] names = {"Add Animal","Sleep","Wake up","Reset","Food","Add Planet","Duplicate Animal", "info","Decorator","Exit"};
	public static Dimension dm;
	public static int wd, hi;
	JFrame myframe;
	public boolean flagImage = false;
	public static boolean wormFlag = false;
	JDialog dialogTable;
	private CyclicBarrier barrier;
	private Singleton wormIns = null;
	
	
	
	public AquaPanel(JFrame frame) {
		myframe = frame;
	    
	    p1=new JPanel();
		p1.setLayout(new GridLayout(1,4,0,5));
		
		
		
		b_num=new JButton[names.length];
		for(int i=0;i<names.length;i++)
		{
		    b_num[i]=new JButton(names[i]);
		    b_num[i].addActionListener(new ButtonListener(this,i));
		    b_num[i].setBackground(Color.lightGray);
		    p1.add(b_num[i]);		
		}
		
		setLayout(new BorderLayout());
		add("South", p1);

	}
	
	
	public void AddAnimelToHashSet(Swimmable s){
		
		if(Animals.size() < 5) {
			s.addObserver(this);
			Animals.add(s);
			new Thread(s).start();	
		}
		else {
			ImageIcon icon = new ImageIcon("src//q3//sea-urchin.png");
			JOptionPane.showMessageDialog(null, "It is not possible to add more than 5 sea creatures to the aquarium.","System Notice",JOptionPane.PLAIN_MESSAGE,icon);
		}
		
	}
	
	public void AddPlanetToHashSet(SeaCreature s){
		if(Plants.size() < 5)
			Plants.add(s);
		else {
			ImageIcon icon = new ImageIcon("src//q3//sea-urchin.png");
			JOptionPane.showMessageDialog(null, "It is not possible to add more than 5 plants to the aquarium.","System Notice",JOptionPane.PLAIN_MESSAGE,icon);
		}
		
	}
	
	

	public void paintComponent(Graphics g)  {
		super.paintComponent(g);
		
		dm = getSize();
		hi = dm.height;
		wd = dm.width;
		
		if(flagImage) {
			final ImageIcon icon = new ImageIcon("src//q3//Background.png");
			Image img = icon.getImage();
			g.drawImage(img, 0, 0, wd, hi, this);
		}
		
	   	
	   	for (Swimmable swimmable : Animals) 
	   		(swimmable).drawCreature(g);
	   	
	   	for (SeaCreature seacreatrue : Plants) 
	   		seacreatrue.drawCreature(g);
	   		

	   	if(wormFlag){
	   		
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(3));
			g2.setColor(Color.red);
			g2.drawArc(getWidth()/2, getHeight()/2-5, 10, 10, 30, 210);
			g2.drawArc(getWidth()/2, getHeight()/2+5, 10, 10, 180, 270);
			g2.setStroke(new BasicStroke(1));
			repaint();
	   		
		}
	   	repaint();
	   	
   }
	
/**
 * The function is responsible for creating a table of all sea creatures and filling
 *  in the rows the requested data and also displaying them
 */

	public void JTableCreat(){ 
		dialogTable=new JDialog(); 
		JPanel TPanel=new JPanel();
        Object[][] data = new Object[Animals.size() + 1][6];
        String[] components = {"Animal","Color","Size","Hor.speed","Ver.speed","Eat couner"};

        int j = 0;
        int eatCountSum = 0;
        for(Swimmable i: Animals) {
            data[j][0] = i.getAnimalName();
            data[j][1] = RetColName(i.getColor());
            data[j][2] = i.getSize();
            data[j][3] = i.horSpeed;
            data[j][4] = i.verSpeed;
            data[j][5] = i.getEatCount();
            eatCountSum = eatCountSum + i.getEatCount();
            j++;
        }

        data[Animals.size()][0] = "Total";
        data[Animals.size()][5] = eatCountSum;
        JTable table = new JTable(data,components);
        JScrollPane scroll = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        TPanel.add(scroll,BorderLayout.CENTER);
        dialogTable.add(TPanel); 
        dialogTable.setVisible(true);
        dialogTable.setBounds(10, 10, 10, 10);
        dialogTable.setSize(500,250);
		
	}
	public void ClearTable() {
		this.dialogTable.remove(this);
		this.dialogTable.setVisible(false);
	}
	
	/**
	 * worm ate by object
	 */
	public void setFlagWorm(){
		wormFlag=false;
		Singleton.set();
	}
	/**
	 * Activate the function "barrierCall" when the flag "wormFlag" is true with Exception when have no Swimmable
	 */
	public void setFlagFood() {
		if(Singleton.get() != null) {
	        if (wormFlag) {
	            try {
	                barrierCall();
	            } catch (IllegalArgumentException err) {
	                System.out.println("No Swimmable");
	                repaint();
	                return;
	            }
	        }
		}
    }
	/**
	 * barrierCall => update for all Fish / JellyFish the CyclicBarrier
	 */
	public void barrierCall() {
		barrier=new CyclicBarrier(Animals.size());
		for (Swimmable swimmable : Animals) {
			swimmable.setBarrier(barrier);
		}
	}
	/**
	 * Function to return the Color name converting to String
	 * to use in JTable
	 * @param c
	 * @return String name
	 */
	public String RetColName(Color c) {
		if(c == Color.black)
			return "Black";
		else if(c == Color.red)
			return "Red";
		else if(c == Color.blue)
			return "Blue";
		else if(c == Color.green)
			return "Green";
		else if(c == Color.cyan)
			return "Cyan";
		else if(c == Color.orange)
			return "Orange";
		else if(c == Color.yellow)
			return "Yellow";
		else if(c == Color.MAGENTA)
			return "Mangeta";
		else if(c == Color.pink)
			return "Pink";
		else
			return null;
	}
	
	
	public Swimmable getAnimalByIndext(int i) {
		Swimmable[] Geeks = Animals.toArray(new Swimmable[Animals.size()]);
		return Geeks[i];
	}
	/**
	 * Singelton 
	 */
	public void setWormInstance(){
		Singleton.set();
		wormIns=null;
		}
	public Singleton getWormIns(){
		return wormIns;
		}
	public boolean getFlagWorm(){
		return wormFlag;
		}
	
	/**
	 * Obserber update override func
	 */
	@Override
	public void update(Observable o, Object arg) {
		ImageIcon icon = new ImageIcon("src//q3//sea-urchin.png");
		JOptionPane.showMessageDialog(null, arg +" say: Food please! ","Notify Food",JOptionPane.PLAIN_MESSAGE,icon);
	}
}

//****************************//
	


/**
 * A Class that creates and arranges all the 
 * buttons in AquaPanel and is responsible for their listener 
 *
 * @authors Eden Barsheshet ID: 203531918 Toli Kot ID: 324413756
 * 
 * @see 
 */
class ButtonListener implements ActionListener{
	
   private AquaPanel panel;
   private int btn_ind;
   DefaultTableModel model;
   private int counter = 0;
   
   public ButtonListener(AquaPanel p, int b){  
	   panel = p;
	   btn_ind = b;
   }
   
   public void actionPerformed(ActionEvent e){
	   switch(btn_ind)
	   {
	   case 0:
		   //Add animal to Panel
		   	AddAnimalDialog AAD = new AddAnimalDialog(panel);
		   	AAD.setVisible(true);
	        break;
	   case 1:
		   //Send all Threads Sleep
		   for (Swimmable swimmable : panel.Animals) 
			   swimmable.setSuspend();
		   break;
	   case 2:
		   //Active notify in all Threads
			for (Swimmable swimmable : panel.Animals) 
				 swimmable.setResume();
	        break;
	   case 3:
		   //Delete all Thread (interrupt)
		   for (Swimmable swimmable : panel.Animals){
			   if(swimmable instanceof Fish) {
				   ((Fish)swimmable).setObserFlag(false);
				   ((Fish)swimmable).resetSwimmable();
				   System.out.println("isAlive() Activated: " + "Thread ID: " + swimmable.getId() + " is " +new Thread((Fish)swimmable).isAlive());
				   
			   }
			   else{
				   ((Jellyfish)swimmable).setObserFlag(false);
				   ((Jellyfish)swimmable).resetSwimmable();
				   System.out.println("isAlive() Activated: " + "Thread ID: " + swimmable.getId() + " is " +new Thread((Jellyfish)swimmable).isAlive());
			   }
		   	}
		      panel.Animals.clear();

		    break;
	   case 4:
		   //Feeding swimmables
		   	AquaPanel.wormFlag = true;
		   	panel.setFlagFood();
		    break;
	   case 5:
		   //Feeding swimmables
		   AddPlanetDialog APD = new AddPlanetDialog(panel);
		   APD.setVisible(true);
		    break;
	   case 6:
		  //Duplicate Animal
		   if(panel.Animals.size() < 5 && panel.Animals.size() > 0) {
			   DuplicateAnimalDialog DAD = new DuplicateAnimalDialog(panel);
			   DAD.setVisible(true);
		   }
		   else if(panel.Animals.size() == 0){
			   ImageIcon icon = new ImageIcon("src//q3//sea-urchin.png");
			   JOptionPane.showMessageDialog(null, "It is not possible to duplicate beacuse aquarium is empty.","System Notice",JOptionPane.PLAIN_MESSAGE,icon);
		   }
		   else {
			   ImageIcon icon = new ImageIcon("src//q3//sea-urchin.png");
			   JOptionPane.showMessageDialog(null, "It is not possible to add more than 5 plants to the aquarium.","System Notice",JOptionPane.PLAIN_MESSAGE,icon);
		   }
		    break;
	   case 7:
		   //Create the table
		   counter++;
		   if(counter%2!=0)
		   		panel.JTableCreat();
		   else
			   panel.ClearTable();
		    break;
	   case 8:
		   JDialog ChangeClrdialog = new JDialog();
		   ChangeClrdialog.setSize(520,550);
		   ChangeClrdialog.setLayout(null);
		   ChangeClrdialog.setVisible(true);
		   ChangeClrdialog.setTitle("Decorator Design Pattern");
		   JPanelDecorator Decorator = new JPanelDecorator(panel);
		   ChangeClrdialog.add(Decorator);
		   break;
	   case 9:
		   // Shot down the program
		   System.exit(0);
		    break;
	}
   }
  }
