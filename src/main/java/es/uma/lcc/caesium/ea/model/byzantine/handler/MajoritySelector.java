package es.uma.lcc.caesium.ea.model.byzantine.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * Selects a fitness value by majority (returns the average of the majority values if there is a tie)
 * @author ccottap
 * @version 1.0
 *
 */
public class MajoritySelector extends FitnessSelector {

	@Override
	public double select(List<Double> uFitness) {
		HashMap<Double, Integer> values = new HashMap<Double, Integer>();

		for (Double f: uFitness) {
			Integer num = values.get(f);
	        values.put(f, num == null ? 1 : num + 1);
		}
				
	    Entry<Double, Integer> max = null;
	    double sum = 0.0;
	    int count = 0;

	    for (Entry<Double, Integer> e : values.entrySet()) {
	        if (max == null || e.getValue() > max.getValue()) {
	            max = e;
	            count = 1;
	            sum = max.getKey();
	        }
	        else if (e.getValue() == max.getValue()) {
	            count ++;
	            sum += max.getKey();	        	
	        }
	    }
	    return sum/(double)count;
	}

}
