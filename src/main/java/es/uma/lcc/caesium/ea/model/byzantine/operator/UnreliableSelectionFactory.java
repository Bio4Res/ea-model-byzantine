package es.uma.lcc.caesium.ea.model.byzantine.operator;

import java.util.List;

import es.uma.lcc.caesium.ea.operator.selection.SelectionFactory;
import es.uma.lcc.caesium.ea.operator.selection.SelectionOperator;

/**
 * Factory class for unreliable selection operators
 * @author ccottap
 * @version 1.1
 *
 */
public class UnreliableSelectionFactory extends SelectionFactory {
	/**
	 * Returns a selection operator given its name.
	 * If the name does not correspond to any known operator,
	 * it returns null.
	 * @param name the name of the selection operator
	 * @param pars parameters of the selection operator
	 * @return the selection operator named
	 */
	public SelectionOperator create (String name, List<String> pars) {
		SelectionOperator op = null;
		
		switch (name.toUpperCase()) {
	
		case "UNRELIABLE":
			SelectionOperator subOp = create(pars.get(0), pars.subList(1, pars.size()));
			op = new UnreliableSelector(subOp);
			break;
		
		default:
			op = super.create(name, pars);
		}
		
		return op;
	}
}
