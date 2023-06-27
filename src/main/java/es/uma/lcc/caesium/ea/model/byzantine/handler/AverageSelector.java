package es.uma.lcc.caesium.ea.model.byzantine.handler;

import java.util.List;

/**
 * Selects a fitness value by averging
 * @author ccottap
 * @version 1.0
 *
 */
public class AverageSelector extends FitnessSelector {

	@Override
	public double select(List<Double> uFitness) {
	    double sum = 0.0;

		for (Double f: uFitness) {
			sum += f;
		}
				
	    return sum/(double)uFitness.size();
	}

}
