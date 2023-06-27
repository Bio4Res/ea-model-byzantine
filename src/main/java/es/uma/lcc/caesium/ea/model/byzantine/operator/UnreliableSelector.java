package es.uma.lcc.caesium.ea.model.byzantine.operator;

import java.util.List;

import es.uma.lcc.caesium.ea.base.Individual;
import es.uma.lcc.caesium.ea.fitness.ObjectiveFunction;
import es.uma.lcc.caesium.ea.model.byzantine.base.UnreliableUtil;
import es.uma.lcc.caesium.ea.operator.selection.SelectionOperator;

/**
 * Shell class for making selection according to unreliable fitness
 * @author ccottap
 * @version 1.0
 */
public class UnreliableSelector extends SelectionOperator {
	/**
	 * Internal selection operator
	 */
	private SelectionOperator selector;

	/**
	 * Creates the operator as a shell to a standard selection operator
	 * @param op name of the subordinate operator
	 */
	public UnreliableSelector(SelectionOperator op) {
		selector = op;
	}
	
	/**
	 * Sets the objective function for potential use inside some operator
	 * @param obj the objective function
	 */
	public void setObjectiveFunction (ObjectiveFunction obj) {
		super.setObjectiveFunction(obj);
		selector.setObjectiveFunction(obj);
	}

	@Override
	public List<Individual> apply(List<Individual> population, int num) {
		UnreliableUtil.swapFitness(population);
		// selection according to unreliable fitness
		List<Individual> selected = selector.apply(population, num);
		// restore fitness
		UnreliableUtil.swapFitness(population);		
		UnreliableUtil.swapFitness(selected);	
		return selected;
	}

	@Override
	public String toString() {
		return "Unreliable" + selector;
	}

}
