package es.uma.lcc.caesium.ea.model.byzantine.operator;

import java.util.ArrayList;
import java.util.List;

import es.uma.lcc.caesium.ea.base.Individual;
import es.uma.lcc.caesium.ea.fitness.ObjectiveFunction;
import es.uma.lcc.caesium.ea.model.byzantine.base.UnreliableIndividual;
import es.uma.lcc.caesium.ea.operator.variation.VariationOperator;

/**
 * Constructs an unreliable individual. Uses a regular constructor internally.
 * @author ccottap
 * @version 1.1
 *
 */
public class UnreliableVariationOperator extends VariationOperator {
	/**
	 * Internal variation operator
	 */
	private VariationOperator op; 
	
	/**
	 * Creates the operator. The first parameter must be the name of 
	 * the underlying initialization operator, and then any parameters it
	 * might have.
	 * @param op name of the subordinate operator
	 */
	public UnreliableVariationOperator(VariationOperator op) {
		super(new ArrayList<String>(0));
		this.op = op;
		setProbability(op.getProbability());
	}
	
	/**
	 * Sets the objective function for potential use inside some operator
	 * @param obj the objective function
	 */
	public void setObjectiveFunction (ObjectiveFunction obj) {
		super.setObjectiveFunction(obj);
		op.setObjectiveFunction(obj);
	}
	
	@Override
	public Individual apply(List<Individual> parents) {
		Individual i = op.apply(parents);
		UnreliableIndividual ind = new UnreliableIndividual();
		if (i.isEvaluated()) {
			ind.setFitness(i.getFitness());
			ind.setUnreliableFitness(i.getFitness());
		}
		ind.setGenome(i.getGenome());
		return ind;
	}

	@Override
	public int getArity() {
		return op.getArity();
	}
	
	
	@Override
	public void newRun() {
		super.newRun();
		op.newRun();
	}
	
	@Override
	public String toString() {
		return "Unreliable" + op;
	}
	
	// This method will never be called, but has to be defined to comply
	// with the abstract superclass. 
	@Override
	protected Individual _apply(List<Individual> parents) {
		assert false : "_apply() method in unreliable operator invoked";
		return null;
	}

}
