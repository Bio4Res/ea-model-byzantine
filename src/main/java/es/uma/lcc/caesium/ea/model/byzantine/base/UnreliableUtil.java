package es.uma.lcc.caesium.ea.model.byzantine.base;

import java.util.List;

import es.uma.lcc.caesium.ea.base.Individual;

/**
 * Utility functions for handling unreliable fitness 
 * @author ccottap
 * @version 1.0
 *
 */
public class UnreliableUtil {

	/**
	 * Swaps the true fitness and the unreliable fitness of
	 * a population of unreliable individuals
	 * @param pop a population of unreliable individuals
	 */
	public static void swapFitness(List<Individual> pop) {
		for (Individual i: pop) {
			UnreliableIndividual ind = (UnreliableIndividual)i;
			ind.swapFitness();
		}
	}

}
