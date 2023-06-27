package es.uma.lcc.caesium.ea.model.byzantine.fitness;

import es.uma.lcc.caesium.ea.base.Individual;
import es.uma.lcc.caesium.ea.fitness.ObjectiveFunction;
import es.uma.lcc.caesium.ea.fitness.OptimizationSense;
import es.uma.lcc.caesium.ea.model.byzantine.base.UnreliableIndividual;
import es.uma.lcc.caesium.ea.util.EAUtil;

/**
 * Abstract class for an unreliable shell of an objective function. 
 * With some probability an unreliable fitness is computed.
 * @author ccottap
 * @version 1.1
 *
 */
public abstract class UnreliableObjectiveFunction extends ObjectiveFunction {
	/**
	 * Default unreliability probability
	 */
	public static final double PROBABILITY = 0.0;
	/**
	 * the true objective function
	 */
	protected ObjectiveFunction trueFunction;
	/**
	 * probability of being unreliable
	 */
	protected double prob;
	
	/**
	 * Creates the shell with a predefined unreliability probability
	 * @param f true objective function
	 */
	public UnreliableObjectiveFunction(ObjectiveFunction f) {
		this(f, PROBABILITY);
	}
	
	/**
	 * Creates the shell
	 * @param f true objective function
	 * @param p probability of returning an unreliable value
	 */
	public UnreliableObjectiveFunction(ObjectiveFunction f, double p) {
		super(f.getNumVars());		
		trueFunction = f;
		prob = p;
	}
	
	/**
	 * Returns the probability of computing unreliable fitness
	 * @return the probability of computing unreliable fitness
	 */
	public double getUnreliabilityProb () {
		return prob;
	}
	
	/**
	 * Sets the probability of computing unreliable fitness
	 * @param p probability of computing unreliable fitness
	 */
	public void setUnreliabilityProb (double p) {
		prob = p;
	}

	@Override
	public OptimizationSense getOptimizationSense() {
		return trueFunction.getOptimizationSense();
	}
	
	
	@Override
	public void newRun() {
		super.newRun();
		trueFunction.newRun();
	}
	
	
	@Override
	protected double _evaluate(Individual i) {
		double f = trueFunction.evaluate(i);
		UnreliableIndividual ind = (UnreliableIndividual)i;
		if (EAUtil.random01()<prob)
			ind.setUnreliableFitness(transform(f));
		else
			ind.setUnreliableFitness(f);
		return f;
	}

	/**
	 * Transforms the true fitness into an unreliable value
	 * @param f true fitness value
	 * @return an unreliable fitness value
	 */
	protected abstract double transform(double f);


}
