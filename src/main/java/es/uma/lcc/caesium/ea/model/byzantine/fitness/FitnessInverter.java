package es.uma.lcc.caesium.ea.model.byzantine.fitness;

import es.uma.lcc.caesium.ea.fitness.ObjectiveFunction;

/**
 * Hostile fitness function: inverts the fitness sign so as 
 * to completely deceive the optimization sense
 * @author ccottap
 * @version 1.1
 *
 */
public class FitnessInverter extends UnreliableObjectiveFunction {
	/**
	 * min value of the fitness function computed
	 */
	private double minval = Double.POSITIVE_INFINITY;
	/**
	 * max value of the fitness function computed
	 */
	private double maxval = Double.NEGATIVE_INFINITY;
	
	
	/**
	 * Creates the inverter with a predefined unreliability probability
	 * @param f true objective function
	 */
	public FitnessInverter(ObjectiveFunction f) {
		super(f);
	}

	/**
	 * Creates the shell
	 * @param f true objective function
	 * @param p probability of returning an unreliable value
	 */
	public FitnessInverter(ObjectiveFunction f, double p) {
		super(f, p);
	}
	
	@Override
	public void newRun() {
		super.newRun();
		minval = Double.POSITIVE_INFINITY;
		maxval = Double.NEGATIVE_INFINITY;
	}
	
	
	@Override
	protected double transform(double f) {
		if (f<minval)
			minval = f;
		if (f>maxval)
			maxval = f;
		
		return maxval-f+minval;
	}

}
