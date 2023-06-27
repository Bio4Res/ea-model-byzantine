package es.uma.lcc.caesium.ea.model.byzantine.handler;

import es.uma.lcc.caesium.ea.model.byzantine.fitness.UnreliableObjectiveFunction;

/**
 * Factory class to create unreliability handlers
 * @author ccottap
 * @version 1.0
 */
public class UnreliabilityHandlerFactory {

	/**
	 * Creates an unreliability handler given its name and parameters,
	 * using the default number of fitness replications.
	 * @param name name of the unreliability handler
	 * @param f unreliable objective function
	 * @return the unreliability handler
	 */
	public static UnreliabilityHandler createHandler (String name, UnreliableObjectiveFunction f) {
		return createHandler(name, f, UnreliabilityHandler.NUMREPS);
	}
	
	/**
	 * Creates an unreliability handler given its name and parameters
	 * @param name name of the unreliability handler
	 * @param f unreliable objective function
	 * @param k number of fitness replications
	 * @return the unreliability handler
	 */
	public static UnreliabilityHandler createHandler (String name, UnreliableObjectiveFunction f, int k) {
		UnreliabilityHandler handler = null;
		
		switch (name.toUpperCase()) {
		case "MAJORITY":
			handler = new MajorityHandler(f, k);
			break;
			
		case "AVERAGE":
			handler = new AverageHandler(f, k);
			break;
			
		}
		return handler;
	}

}
