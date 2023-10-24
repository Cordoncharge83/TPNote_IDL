package bad4debug;
import java.util.logging.Logger;

import java.util.Vector;

/**
 * The Class Test2Debug.
 */
public class Test2Debug {
	private static final Logger log = Logger.getLogger(Test2Debug.class.getName());
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		count2();
	}

	/**
	 * a simple example
	 *  P_1:4 --2-> T_0 --1-> P_2:0.
	 */
	public static void pn1P1T() {
		PetriNetInterface pn = new PetriNet();
		Place p1 = pn.addPlace(1);
		p1.addTokens(3);
		Transition t1 = pn.addTransition();
		pn.addPlace2TransitionArc(p1, t1, 2);
		Place p2 = pn.addPlace(0);
		pn.addTransition2PlaceArc(t1, p2, 1);
		//System.out.println(" Description de pn \n" + pn);
		log.fine(" Description de pn \n" + pn);
		//System.out.println(" t1.isTriggerable() " + t1.isTriggerable());
		log.fine(" t1.isTriggerable() " + t1.isTriggerable());
		//System.out.println(" pn.trigger(t1); -----> ");
		log.fine(" pn.trigger(t1); -----> ");
		pn.trigger(t1);
		//System.out.println(" p1.getTokens() == 2 " + (p1.getTokens() == 2));
		log.fine(" p1.getTokens() == 2 " + (p1.getTokens() == 2));
		//System.out.println(" p2.getTokens() == 1 " + (p2.getTokens() == 1));
		log.fine(" p2.getTokens() == 1 " + (p2.getTokens() == 1));
		//System.out.println(" pn.trigger(t1);  -----> ");
		log.fine(" pn.trigger(t1);  -----> ");
		pn.trigger(t1);
		//System.out.println(" p1.getTokens() == 0 " + (p1.getTokens() == 0));
		//System.out.println(" p2.getTokens() == 2 " + (p2.getTokens() == 2));		
		//System.out.println(" Description de pn \n" + pn);
	}
	
	/**
	 *  P_0:1 --1-> T_0 --2-> P_1
	 *  P_1:  --1-> T_1 --2-> P_0.
	 */
	// possible for main test
	public static void count2() {
		PetriNetInterface pnl = new PetriNet();
		Place p0 = pnl.addPlace(1);
		Place p1 = pnl.addPlace(0);
		Transition t0 = pnl.addTransition();
		Transition t1 = pnl.addTransition();
		pnl.addPlace2TransitionArc(p0, t0, 1);
		pnl.addTransition2PlaceArc(t0, p1, 2);
		pnl.addPlace2TransitionArc(p1, t1, 1);
		pnl.addTransition2PlaceArc(t1, p0, 2);
//		System.out.println(" Description de pn \n" + pnl);
//		System.out.println(" t1.isTriggerable() " + t0.isTriggerable());
//		System.out.println(" pn.trigger(t0); -----> ");
//		pnl.trigger(t0);
//		//System.out.println(" p0.getTokens() == 2 " + (p0.getTokens() == 2));
//		System.out.println(" pnl.trigger(t0);  -----> ");
//		pnl.trigger(t1);
//		//System.out.println(" p0.getTokens() == 3 " + (p0.getTokens() == 3));
//		System.out.println(" Description de pn \n" + pnl);
		
		// add a third transition
		Transition t3 = pnl.addTransition();
		pnl.addPlace2TransitionArc(p1, t3, 10);
		pnl.addTransition2PlaceArc(t3, p1, 1);	
		pnl.addPlace2TransitionArc(p0, t3, 10);
		pnl.addTransition2PlaceArc(t3, p0, 1);				
		t3 = pnl.getTransitionNth(2);
		
		Vector<Transition> vt = new Vector<Transition>();
		vt.add(t0); 
		vt.add(t1);
		//vt.add(t0); //==> Deux fois t0 dans le vecteur
		vt.add(t3);
		pnl.triggerMore(vt);		
		
		// this result is suspicious ==> Indeed, on the screen of our computer we can't
		//see any movement from a place to t3
		System.out.println("test effect of t3 ? " + t3.testEffect());

	}

}