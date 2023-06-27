package es.uma.lcc.caesium.ea.model.byzantine.handler;

import es.uma.lcc.caesium.ea.model.byzantine.fitness.UnreliableObjectiveFunction;


/**
 * Class that handles unreliability in the objective function by calling it a 
 * number of times and returning the most repeated value (if any - otherwise 
 * the mean value is returned).
 * @author ccottap
 * @version 1.1
 */
public class MajorityHandler extends UnreliabilityHandler {
	
	/**
	 * Creates the handler given the unreliable objective function, using a
	 * default value for the number of replicas.
	 * @param f the underlying unreliable objective function
	 */
	public MajorityHandler(UnreliableObjectiveFunction f) {
		this(f, NUMREPS);
	}
	

	/**
	 * Creates the handler given the unreliable objective function and the number of replicas.
	 * @param f the unreliable objective function
	 * @param k the number of replicas
	 */
	public MajorityHandler(UnreliableObjectiveFunction f, int k) {
		super(f, new MajoritySelector(), k);
	}
	
	/**
	 * Sets the fitness selector
	 * @param fs the fitness selector
	 */
	public void setFitnessSelector(FitnessSelector fs) {
		assert false : "Cannot change the fitness selector in MajorityHandler";
	}


}
