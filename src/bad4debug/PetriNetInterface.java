package bad4debug;


import java.util.Vector;

/**
 * The Interface PetriNetInterface.
 */
public interface PetriNetInterface {
	
	/**
	 * Adds the place.
	 *
	 * @param var1 number of tokens
	 * @return the place
	 */
	Place addPlace(int var1);

	/**
	 * Adds the place.
	 *
	 * @param var1 the name
	 * @param var2 the tokens
	 * @return the place
	 */
	Place addPlace(String var1, int var2);

	/**
	 * Adds the transition.
	 *
	 * @return the transition
	 */
	Transition addTransition();

	/**
	 * Adds the transition.
	 *
	 * @param var1 the name
	 * @return the transition
	 */
	Transition addTransition(String var1);

	/**
	 * Adds the place 2 transition arc.
	 *
	 * @param var1 the place
	 * @param var2 the transition
	 * @param var3 the weight
	 */
	void addPlace2TransitionArc(Place var1, Transition var2, int var3);

	/**
	 * Adds the transition 2 place arc.
	 *
	 * @param var1 the transition
	 * @param var2 the place
	 * @param var3 the weight
	 */
	void addTransition2PlaceArc(Transition var1, Place var2, int var3);

	/**
	 * Trigger.
	 *
	 * @param var1 the transition
	 */
	void trigger(Transition var1);
	

	/**
	 * Trigger a sequence of transition.
	 * @param vt
	 */
	void triggerMore(Vector<Transition> vt);

	/**
	 * Retrive the Nth transition
	 * @param i
	 * @return
	 */
	Transition getTransitionNth(int i);
	
	
}