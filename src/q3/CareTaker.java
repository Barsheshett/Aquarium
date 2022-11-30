package q3;

import java.util.HashMap;
//CareTake - a class that save all the backups.( Memento array).
public class CareTaker {

	private HashMap<Integer, Memento> swimMementoLst = new HashMap<Integer, Memento>(); 
	private HashMap<Integer, Memento> planMementoLst = new HashMap<Integer, Memento>();
	private static int counter =0;
	public final int MementoID;
	/**
	 * Constractor
	 */
	public CareTaker()
	{
		this.MementoID=++counter;
	}
	/**
	 * Add Memento State to Swimmables List 
	 * @param Mid
	 * @param state
	 */
	public void addSwimMemento(int Mid, Memento state) {
		swimMementoLst.put(Mid, state);
	}
	/**
	 * Add Memento State to Plnats List 
	 * @param Mid
	 * @param state
	 */
	public void addPlanMemento(int Mid, Memento state) {
		planMementoLst.put(Mid, state);
	}
	/**
	 * return Memento State by getting Index
	 * @param idx
	 * @return
	 */
	public Memento getSwimMemento(int idx) {
		return swimMementoLst.get(idx);
	}
	/**
	 * return Memento State by getting index
	 * @param idx
	 * @return
	 */
	public Memento getPlanMemento(int idx) {
		return planMementoLst.get(idx);
	}
	
	/**
	 * Getters
	 */
	
	public HashMap<Integer, Memento> getSwimMementoLst() {
		return swimMementoLst;
	}
	public HashMap<Integer, Memento> getPlanMementoLst() {
		return planMementoLst;
	}
	
	
}
