package es.uma.lcc.caesium.ea.model.byzantine.base;

import es.uma.lcc.caesium.ea.base.Individual;

/**
 * Extension of the Individual class to handle potentially 
 * unreliable fitness information  
 * @author ccottap
 * @version 1.0
 *
 */
public class UnreliableIndividual extends Individual {
	/**
	 * fitness assigned in an unreliable way
	 */
	private double unreliableFitness;
	
	/**
	 * Basic constructor
	 */
	public UnreliableIndividual() {
		super();
	}
	
	/**
	 * Copy constructor
	 * @param i an unreliable individual to be copied
	 */
	public UnreliableIndividual(UnreliableIndividual i) {
		super(i);
		setUnreliableFitness(i.getUnreliableFitness());
	}
	
	/**
	 * Returns a copy of the individual
	 * @return a copy of the individual
	 */
	public UnreliableIndividual clone() {
		return new UnreliableIndividual(this);
	}

	/**
	 * Returns the unreliable fitness
	 * @return the unreliable fitness
	 */
	public double getUnreliableFitness() {
		return unreliableFitness;
	}

	/**
	 * Sets the unreliable fitness
	 * @param uf the unreliable fitness to set
	 */
	public void setUnreliableFitness(double uf) {
		this.unreliableFitness = uf;
	}
	
	/**
	 * Swaps the real fitness and the unreliable fitness
	 */
	public void swapFitness() {
		double temp = fitness;
		fitness = unreliableFitness;
		unreliableFitness = temp;
	}
	
	@Override
	public String toString() {
		return "{\n\tfitness: " + fitness + "\n\tuFitness: " + unreliableFitness + "\n\tgenome: " + genome + "\n}";
	}

}
