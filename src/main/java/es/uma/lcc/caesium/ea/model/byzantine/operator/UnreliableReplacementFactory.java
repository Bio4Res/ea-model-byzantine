package es.uma.lcc.caesium.ea.model.byzantine.operator;

import java.util.List;

import es.uma.lcc.caesium.ea.operator.replacement.ReplacementFactory;
import es.uma.lcc.caesium.ea.operator.replacement.ReplacementOperator;

/**
 * Factory for replacement methods
 * @author ccottap
 * version 1.0
 *
 */
public class UnreliableReplacementFactory extends ReplacementFactory {
	/**
	 * Returns a replacement operator given its name.
	 * If the name does not correspond to any known operator,
	 * it returns null.
	 * @param name the name of the replacement operator
	 * @param pars parameters of the replacement operator
	 * @return the replacement operator named
	 */
	public ReplacementOperator create (String name, List<String> pars) {
		ReplacementOperator op = null;
		
		switch (name.toUpperCase()) {
		case "UNRELIABLE":
			ReplacementOperator subOp = super.create(pars.get(0), pars.subList(1, pars.size()));
			op = new UnreliableReplacer(subOp);
			break;
		default:
			op = super.create(name, pars);
		}
		
		return op;
	}
}
