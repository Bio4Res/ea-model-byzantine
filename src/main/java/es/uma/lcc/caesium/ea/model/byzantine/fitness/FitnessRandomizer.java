package es.uma.lcc.caesium.ea.model.byzantine.fitness;

import java.util.ArrayList;
import java.util.List;

import es.uma.lcc.caesium.ea.fitness.ObjectiveFunction;
import es.uma.lcc.caesium.ea.util.EAUtil;

/**
 * Noise-inducing fitness function: with some probability returns an
 * unrelated fitness value (taken from previously computed fitness values).
 * @author ccottap
 * @version 1.1
 *
 */
public class FitnessRandomizer extends UnreliableObjectiveFunction {
	/**
	 * List of previously computed fitness values
	 */
	private List<Double> fitnessLog = new ArrayList<Double>();
	
	
	/**
	 * Creates the randomizer with a predefined unreliability probability
	 * @param f true objective function
	 */
	public FitnessRandomizer(ObjectiveFunction f) {
		super(f);
	}

	/**
	 * Creates the shell
	 * @param f true objective function
	 * @param p probability of returning an unreliable value
	 */
	public FitnessRandomizer(ObjectiveFunction f, double p) {
		super(f, p);
	}
	
	@Override
	public void newRun() {
		super.newRun();
		fitnessLog.clear();
	}
	
	
	@Override
	protected double transform(double f) {
		fitnessLog.add(f);
		return fitnessLog.get(EAUtil.random(fitnessLog.size()));
	}

}
