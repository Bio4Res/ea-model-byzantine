package es.uma.lcc.caesium.ea.model.byzantine.handler;

import java.util.ArrayList;
import java.util.List;

import es.uma.lcc.caesium.ea.base.Individual;
import es.uma.lcc.caesium.ea.model.byzantine.base.UnreliableIndividual;
import es.uma.lcc.caesium.ea.model.byzantine.fitness.UnreliableObjectiveFunction;


/**
 * Class that handles unreliability in the objective function by calling it a 
 * number of times and returning the value selected by a given FitnessSelector
 * @author ccottap
 * @version 1.0
 */
public class UnreliabilityHandler extends UnreliableObjectiveFunction {
	/**
	 * Default value of the number of calls to the underlying objective function
	 */
	public static final int NUMREPS = 3;
	/**
	 * number of calls to the underlying objective function to determine
	 * a majority decision.
	 */
	private int numreps;
	/**
	 * fitness selection procedure
	 */
	private FitnessSelector selector;
	

	/**
	 * Creates the handler given the unreliable objective function and the
	 * fitness selector mechanism, using a default value for the number of replicas.
	 * @param f the underlying unreliable objective function
	 * @param selector the fitness selector
	 */
	public UnreliabilityHandler(UnreliableObjectiveFunction f, FitnessSelector selector) {
		this(f, selector, NUMREPS);
	}

	/**
	 * Creates the handler given the unreliable objective function and the number of replicas.
	 * @param f the unreliable objective function
	 * @param selector the fitness selector
	 * @param k the number of replicas
	 */
	public UnreliabilityHandler(UnreliableObjectiveFunction f, FitnessSelector selector, int k) {
		super(f);
		this.selector = selector;
		setNumReps(k);
	}


	/**
	 * Sets the number of replicas
	 * @param k the number of replicas
	 */
	public void setNumReps(int k) {
		numreps = k;
	}
	
	/**
	 * Gets the number of replicas
	 * @return the number of replicas
	 */
	public int getNumReps() {
		return numreps;
	}
	
	
	/**
	 * Returns the fitness selector
	 * @return the fitness selector
	 */
	public FitnessSelector getFitnessSelector() {
		return selector;
	}

	/**
	 * Sets the fitness selector
	 * @param selector the fitness selector
	 */
	public void setFitnessSelector(FitnessSelector selector) {
		this.selector = selector;
	}

	@Override
	public long getEvals() {
		return trueFunction.getEvals();
	}
	
	@Override
	public void newRun() {
		super.newRun();
		trueFunction.newRun();
	}
	

	@Override
	protected double _evaluate(Individual ind) {		
		UnreliableIndividual uInd = (UnreliableIndividual)ind;
		List<Double> values = new ArrayList<Double>(numreps);
		
		for (int i=0; i<numreps; i++) {
			uInd.touch();
			trueFunction.evaluate(uInd);
			values.add(uInd.getUnreliableFitness());
		}
		
	    double f = selector.select(values);
	    
		uInd.setUnreliableFitness(f);
		
		return uInd.getFitness();
	}
	


	@Override
	protected double transform(double f) {
		assert false : "UnreliabilityHandler :: transform() should not be called";

		return 0;
	}

}
