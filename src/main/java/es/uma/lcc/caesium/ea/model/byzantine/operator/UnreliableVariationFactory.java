/**
 * 
 */
package es.uma.lcc.caesium.ea.model.byzantine.operator;

import java.util.List;

import es.uma.lcc.caesium.ea.operator.variation.VariationFactory;
import es.uma.lcc.caesium.ea.operator.variation.VariationOperator;

/**
 * Factory for unreliable-fitness-based operators
 * @author ccottap
 * @version 1.0
 *
 */
public class UnreliableVariationFactory extends VariationFactory {

	public VariationOperator create (String name, List<String> pars) {
		VariationOperator op = null;
		
		switch (name.toUpperCase()) {
		// Unreliable
		case "UNRELIABLE":
			VariationOperator subOp = create(pars.get(0), pars.subList(1, pars.size()));
			op = new UnreliableVariationOperator(subOp);
			break;
		
		default:
			op = super.create(name, pars);
		}
		
		return op;
	}

}
