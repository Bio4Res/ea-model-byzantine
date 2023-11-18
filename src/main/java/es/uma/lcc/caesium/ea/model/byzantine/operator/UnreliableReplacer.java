package es.uma.lcc.caesium.ea.model.byzantine.operator;

import java.util.List;

import es.uma.lcc.caesium.ea.base.Individual;
import es.uma.lcc.caesium.ea.fitness.ObjectiveFunction;
import es.uma.lcc.caesium.ea.model.byzantine.base.UnreliableUtil;
import es.uma.lcc.caesium.ea.operator.replacement.ReplacementOperator;

/**
 * Shell class for making replacement according to unreliable fitness
 * @author ccottap
 * @version 1.0
 */
public class UnreliableReplacer extends ReplacementOperator {
	/**
	 * internal replacement operator
	 */
	private ReplacementOperator replacer;
	
	/**
	 * Creates the operator as a shell to a standard replacement operator
	 * @param op name of the subordinate operator
	 */
	public UnreliableReplacer(ReplacementOperator op) {
		replacer = op;
	}
	
	/**
	 * Sets the objective function for potential use inside some operator
	 * @param obj the objective function
	 */
	public void setObjectiveFunction (ObjectiveFunction obj) {
		super.setObjectiveFunction(obj);
		replacer.setObjectiveFunction(obj);
	}

	@Override
	public List<Individual> apply(List<Individual> population, List<Individual> offspring) {
		UnreliableUtil.swapFitness(population);
		UnreliableUtil.swapFitness(offspring);
				
		List<Individual> replaced = replacer.apply(population, offspring);

		UnreliableUtil.swapFitness(population);
		UnreliableUtil.swapFitness(offspring);	
		
		UnreliableUtil.swapFitness(replaced);
		
		return replaced;
	}

	@Override
	public void newRun() {
		super.newRun();
		replacer.newRun();
	}
	
	@Override
	public String toString() {
		return "Unreliable" + replacer;
	}

}
