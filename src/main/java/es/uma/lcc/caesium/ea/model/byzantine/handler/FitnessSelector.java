package es.uma.lcc.caesium.ea.model.byzantine.handler;

import java.util.List;

/**
 * Abstract class for selecting a fitness value given a list of unreliable fitness
 * evaluations
 * @author ccottap
 * @version 1.0
 *
 */
public abstract class FitnessSelector {

	/**
	 * Returns a selected fitness value given the list of
	 * unreliable fitness evaluations
	 * @param uFitness the unreliable fitness values
	 * @return a fitness value
	 */
	public abstract double select(List<Double> uFitness);
}
