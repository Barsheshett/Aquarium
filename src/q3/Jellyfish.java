
 package q3; import java.awt.Color;
import java.awt.Graphics;
import java.util.Hashtable;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
  
 /**
	 * An class that creates Jellyfish-type instances and other methods that
	 * manipulate the change and / or display of the Jellyfish. extends Swimmable
	 *
	 * @authors Eden Barsheshet ID: 203531918 Toli Kot ID: 324413756
	 * 
	 * @see Fish
	 */

public class Jellyfish extends Swimmable implements MarineAnimal { 
	  private final int EAT_DISTANCE = 4;
	  private int size; private int col;
	  private int eatCount;
	  private int x_front;
	  private int y_front; 
	  protected int counter = 0;
	  private int x_flag = 1, y_flag = 1;
	  private Thread timer;
	  boolean suspended, resetFlag=false;
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

  public Jellyfish(int size, int x_front, int y_front, int horSpeed, int verSpeed, int col, int foodFrequency){ 
	  super(horSpeed, verSpeed,foodFrequency); 
	  this.size = size; 
	  this.col = col; 
	  this.x_front = x_front; 
	  this.y_front = y_front; 
	  eatCount = 0; 
	  counter = col; 
	  timer = new Thread(this);
	  timer.start();
	  }
  /**
   * Default Contractor
   */
  public Jellyfish() {
	  super(1, 1,0); 
	  this.size = 20; 
	  this.col = 1; 
	  this.x_front = 0; 
	  this.y_front = 0; 
	  eatCount = 0; 
	  counter = col; 
	  timer = new Thread(this);
	  timer.start();
	  }
  
 /**
	 * Override methods
	 */

  @Override public String getAnimalName() { return
  this.getClass().getSimpleName(); }
  
  @Override public int getEatCount() { return totalEat; }
  
  @Override public int getSize() { return size; }
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

  @Override public void eatInc(){
	  if(eatCount == EAT_DISTANCE){
		  changeJellyfish(); 
		  eatCount = 0; 
		  } 
	  totalEat +=1;
	  eatCount += 1;
	  }
  
  	/**
	* increase Size of fish by 1
	*/
  	public void changeJellyfish() { size += 1; }
  	
  
	public Thread getTimer() {
		return timer;
	}
	public void setTimer(Thread timer) {
		this.timer = timer;
	}
	@Override
	public void setSuspend() {
		suspended = true;
	
	}
	@Override
	public void setResume() {
		suspended = false;
		synchronized (this) {
			notify();
		}
		
	
	}
	@Override
	public void setBarrier(CyclicBarrier b) {
		barrier = b;
	
	}
	public void resetSwimmable()
	{
		resetFlag=true;
        Thread.currentThread().interrupt();
	}
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            }
	            }

	            try 
	            {
	                Thread.sleep(50);
	            } 
	            catch (InterruptedException e) 
	            {
	            	System.out.println("Sleep ERROR!");
	            }
	            
			x_front += horSpeed*x_flag;
			y_front += verSpeed*y_flag;
			
			
			if(x_front > AquaPanel.wd)
		    {
		    	x_flag = -1;
		    	x_front = AquaPanel.wd;
		    }
		    else if(x_front < 0)
		    {
		    	x_flag = 1;
		    	x_front = 0;
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

			    FrequencyCounter++;  
		         
		        if (FrequencyCounter != 0 && FrequencyCounter%foodFrequency == 0) {
		        	currState = new Hungry();
		        	currState.doAction(this);
		        	if(obserFlag) {
		        		setChanged();
		        		notifyObservers(ID);
		        	}
		        }
		    }
		}
	}
	
	public void TryEatWorm()
    {
        if(x_front > (AquaPanel.wd)/2)
        {
            x_flag = -1;
        }
        else if(x_front < (AquaPanel.wd)/2)
        {
            x_flag = 1;
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
		
		int numLegs;
		
		if(size<40)
		    numLegs = 5;
		else if(size<80)
		    numLegs = 9;
		else
		    numLegs = 12;
	
		g.setColor(col1);
		g.fillArc(x_front - size/2, y_front - size/4, size, size/2, 0, 180);
				
		for(int i=0; i<numLegs; i++)
			  g.drawLine(x_front - size/2 + size/numLegs + size*i/(numLegs+1), y_front, x_front - size/2 + size/numLegs + size*i/(numLegs+1), y_front+size/3);
		
	}
	@Override
	public Object clone() {
		return new Jellyfish(size,0,0,horSpeed,verSpeed,col,foodFrequency);
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
		currState = state;
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
		return 1;
	}
	@Override
	public int getY_dir() {
		return 1;
	}
	@Override
	public void setState(int size, int x_front, int y_front, int horSpeed, int verSpeed, Color clr, int foodFrequency) {
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
