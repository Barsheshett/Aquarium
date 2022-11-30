
  package q3; import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.Hashtable;
import java.util.Observer;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

  
 /**
	 * An class that creates Fish-type instances and other methods that manipulate
	 * the change and / or display of the Fish. extends Swimmable
	 *
	 * @authors Eden Barsheshet ID: 203531918 Toli Kot ID: 324413756
	 * 
	 * @see Fish, MultiColorFish, UnusualFish
	 */

  public class Fish extends Swimmable implements MarineAnimal{ 
	private final int EAT_DISTANCE = 4;
	  private int size; 
	  private int col; 
	  private int eatCount; 
	  private int x_front;
	  private int y_front; 
	  private int x_dir; 
	  private int y_dir; 
	  protected int counter = 0;
	  private int x_flag = 1, y_flag = 1;
	  private Thread timer;
	  boolean suspended, resetFlag = false;
	  private CyclicBarrier barrier = null;
	  private int totalEat = 0;
	  private boolean obserFlag = true;
	  private HungerState currState;
	  private Color newClr;
	  private boolean changeClrFlag = false;
	  
	  
  
  

/**
	 * Parameters constructor
	 * 
	 * @param size
	 * @param x_front
	 * @param y_front
	 * @param horSpeed
	 * @param verSpeed
	 * @param col
	 */

  public Fish(int size, int x_front, int y_front , int horSpeed, int verSpeed , int col, int foodFrequency){ 
	  super(horSpeed, verSpeed, foodFrequency); 
	  this.size = size; 
	  this.col = col;
	  this.x_front = x_front; 
	  this.y_front = y_front; 
	  x_dir = 1; 
	  y_dir = 1;
	  eatCount = 0; 
	  counter = col; 
	  
	  timer = new Thread(this);
	  timer.start();
	  }
 /**
	 * Default constructor
	 */

  public Fish() { 
	  super(0, 0, 0); 
	  this.size = 0; 
	  this.col = 0; 
	  this.x_front = 0;
	  this.y_front = 0; 
	  x_dir = 1; 
	  y_dir = 1; 
	  eatCount = 0; 
	  counter = 0;
	  timer = new Thread(this);
	  timer.start();
	  }
 /**
	 * Copy constructor
	 * 
	 * @param other
	 */

  public Fish(Fish other) { 
	  super(other.horSpeed , other.verSpeed, other.foodFrequency); 
	  this.size = other.size; 
	  this.col = other.col; 
	  this.x_front = other.x_front; 
	  this.y_front = other.y_front; 
	  this.x_dir = 1; 
	  this.y_dir = 1; 
	  this.eatCount = 0;
	  this.counter = col; 
	  this.foodFrequency = other.foodFrequency;
	  }
 /**
	 * Override abstract methods
	 */

  @Override public String getAnimalName() { return
  this.getClass().getSimpleName(); }
  
  @Override public int getEatCount() { return totalEat; }
  
  @Override public int getSize() { return size; }
 /**
	 * getters & setters methods
	 * 
	 * @return
	 */

  public int getCol() { return col; } public void setCol(int col) { this.col =
  col; } public int getEAT_DISTANCE() { return EAT_DISTANCE; } public void
  setEatCount(int eatCount) { this.eatCount = eatCount; } public void
  setSize(int size) { this.size = size; }
 /**
	 * Override method: return the (string)color using "col" field. order by
	 * dictionary
	 */

  @Override public Color getColor() {
  
  Hashtable<Integer, Color> my_dict = new Hashtable<Integer, Color>();
  my_dict.put(1, Color.black); 
  my_dict.put(2, Color.red); 
  my_dict.put(3, Color.blue);
  my_dict.put(4, Color.green); 
  my_dict.put(5, Color.cyan); 
  my_dict.put(6, Color.orange);
  my_dict.put(7, Color.yellow); 
  my_dict.put(8, Color.magenta); 
  my_dict.put(9, Color.pink);
  
  return my_dict.get(col); 
  }
  
 /**
	 * Override method: by activate - Feeds the fish (eatCount field increased by)
	 * if eatCount = "Maximum amount of food" => increase Size of fish by 1
	 */

  @Override 
  public void eatInc() { 
	  eatCount += 1;
	  if(eatCount == EAT_DISTANCE){ 
		  changeFish();
		  eatCount = 0; 
	  }   
	  totalEat += 1;
  }
 /**
	 * increase Size of fish by 1
	 */

  public void changeFish() { size += 1; }
 /**
	 * change color of the Fish instance
	 */
		  public void changeColor() { if(counter > 9) counter = 1; counter ++; col =
		  counter; }
		  
	public Thread getTimer() {
		return timer;
	}
	public void setTimer(Thread timer) {
		this.timer = timer;
	}

	@Override
	public String toString() {
		return "Fish [EAT_DISTANCE=" + EAT_DISTANCE + ", size=" + size + ", col=" + col + ", eatCount=" + eatCount
				+ ", x_front=" + x_front + ", y_front=" + y_front + ", x_dir=" + x_dir + ", y_dir=" + y_dir + ", counter="
				+ counter + "]";
	}
	/**
	 * flag using to use wait() Thread function
	 */
	@Override
	public void setSuspend() {
		suspended = true;
		obserFlag = true;
	
	}
	/**
	 * flag using to use notify() Thread function
	 */
	@Override
	public void setResume() {
		suspended = false;
		obserFlag = false;
		synchronized (this) {
			notify();
		}
		
	
	}
	/**
	 * update Barrier => sending from AquaPanel
	 */
	@Override
	public void setBarrier(CyclicBarrier b) {
		barrier = b;
	} 
	/**
	 * kill the Thread
	 */
	public void resetSwimmable()
	{
		resetFlag=true;
        Thread.currentThread().interrupt();

	}
	/**
	 * run function, extends Thread class 
	 */
	@SuppressWarnings("deprecation")
	public void run() {
		Thread me = Thread.currentThread();
		
		while (timer == me) {
			
			if(barrier!=null){
                try {
                    System.out.println("await fish");
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
			
			synchronized(this) {
	            while(suspended) {
	               try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	            }
	            }
				

			try{
	                Thread.sleep(50);
	                
	        }catch (InterruptedException e) {
	        	
	            	System.out.println("Sleep ERROR!");
	        }
	            

			x_front += horSpeed*x_flag;
			y_front += verSpeed*y_flag;
			
			
			if(x_front > AquaPanel.wd)
		    {
		    	x_flag = -1;
		    	x_front = AquaPanel.wd;
		    	x_dir = -1;
		    }
		    else if(x_front < 0)
		    {
		    	x_flag = 1;
		    	x_front = 0;
		    	x_dir = 1;
		    }
	
		    if(y_front > AquaPanel.hi)
		    {
		    	y_flag = -1;
		    	y_front = AquaPanel.hi;
		    }
		    else if(y_front < 0)
		    {
		    	y_flag = 1;
		    	y_front = 0;
		    }
		    
		    if(!suspended) {
	            if(AquaPanel.wormFlag)
	            	if(currState instanceof Hungry)
	            		TryEatWorm();
	            
	  		}
		    
		    FrequencyCounter++;
		    
	        if (FrequencyCounter != 0 && FrequencyCounter%foodFrequency == 0) {
	        	obserFlag = true;
	        	currState = new Hungry();
	        	currState.doAction(this);
                setChanged();
                notifyObservers(ID);
            }
		}	
	}
	/**
	 * try eat function => try eat a worm when she show on
	 */
	public void TryEatWorm()
    {
        if(x_front > (AquaPanel.wd)/2)
        {
            x_flag = -1;
            x_dir = -1;
        }
        else if(x_front < (AquaPanel.wd)/2)
        {
            x_flag = 1;
            x_dir = 1;
        }

        if(y_front > (AquaPanel.hi)/2)
        {
            y_flag = -1;
        }
        else if(y_front < (AquaPanel.hi)/2)
        {
            y_flag = 1;
        }
        barrier = null;

        synchronized(this){
            if((Math.abs(AquaPanel.wd/2-x_front)<=5) && (Math.abs(AquaPanel.hi/2-y_front)<=5)){ 
                AquaPanel.wormFlag = false;
                this.size++;
                callback();
                currState = new Satiated();
                currState.doAction(this);
                obserFlag = false;
            }
        }

    }
	/**
	 * Increase eat variable by one
	 */
    private void callback() {
        eatInc();
    }
    
	@Override
	public void drawCreature(Graphics g) {

		Color col1 = new Color(0 , 0 , 0);
		if(!changeClrFlag)
			col1 = getColor();
		else
			col1 = newClr;
		
		g.setColor(col1);
	   if(x_dir==1) // fish swims to right side
	   {
		// Body of fish
		g.fillOval(x_front - size, y_front - size/4, size, size/2);

		// Tail of fish
		int[] x_t={x_front-size-size/4,x_front-size-size/4,x_front-size};
		int [] y_t = {y_front - size/4, y_front + size/4, y_front};
		Polygon t = new Polygon(x_t,y_t,3);		
		g.fillPolygon(t);

		// Eye of fish
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(255-col1.getRed(),255-col1.getGreen(),255- col1.getBlue()));
		g2.fillOval(x_front-size/5, y_front-size/10, size/10, size/10);
				
		// Mouth of fish
		if(size>70)
			g2.setStroke(new BasicStroke(3));
		else if(size>30)
			g2.setStroke(new BasicStroke(2));
		else
			g2.setStroke(new BasicStroke(1));
	      g2.drawLine(x_front, y_front, x_front-size/10, y_front+size/10);
	      g2.setStroke(new BasicStroke(1));
	   }
	   else // fish swims to left side
	   {
		// Body of fish
		g.fillOval(x_front, y_front - size/4, size, size/2);

		// Tail of fish
		int[] x_t={x_front+size+size/4,x_front+size+size/4,x_front+size};
		int [] y_t = {y_front - size/4, y_front + size/4, y_front};
		Polygon t = new Polygon(x_t,y_t,3);		
		g.fillPolygon(t);
		// Eye of fish
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(255-col1.getRed(),255-col1.getGreen(),255-col1.getBlue()));
		g2.fillOval(x_front+size/10, y_front-size/10, size/10, size/10);
				
		// Mouth of fish
		if(size>70)
			g2.setStroke(new BasicStroke(3));
		else if(size>30)
			g2.setStroke(new BasicStroke(2));
		else
			g2.setStroke(new BasicStroke(1));
	      g2.drawLine(x_front, y_front, x_front+size/10, y_front+size/10);
	      g2.setStroke(new BasicStroke(1));
	   }
		
	}
	@Override
	public Object clone() {
		// Creating a deep copy for Fish
	     return new Fish(size,0,0,horSpeed, verSpeed,col,foodFrequency);
	   }
	@Override
	public int getId() {
		return super.ID;
	}
	@Override
	public HungerState getState() {
		return currState;
	}
	@Override
	public void setHungeryState(HungerState state) {
		currState=state;
		
	}
	@Override
	public void PaintFish(Color clr) {
		changeClrFlag = true;
		newClr = clr;
	}
	@Override
	public int getX_front() {
		return x_front;
	}
	@Override
	public int getY_front() {
		return y_front;
	}
	@Override
	public int getX_dir() {
		return x_dir;
	}
	@Override
	public int getY_dir() {
		return y_dir;
	}
	//Memento state
	@Override
	public void setState(int size, int x_front, int y_front , int horSpeed, int verSpeed , Color clr, int foodFrequency) {
		this.size = size;
		this.x_front = x_front;
		this.y_front = y_front;
		this.horSpeed = horSpeed;
		this.verSpeed = verSpeed;
		PaintFish(clr);
		changeClrFlag = false;
		this.foodFrequency = foodFrequency;
	}
	
	public boolean isObserFlag() {
		return obserFlag;
	}
	public void setObserFlag(boolean obserFlag) {
		this.obserFlag = obserFlag;
	}
	
	
	
	
	
	

}
		 