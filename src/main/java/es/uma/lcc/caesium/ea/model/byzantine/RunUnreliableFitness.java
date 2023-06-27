package es.uma.lcc.caesium.ea.model.byzantine;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import es.uma.lcc.caesium.ea.base.EvolutionaryAlgorithm;
import es.uma.lcc.caesium.ea.config.EAConfiguration;
import es.uma.lcc.caesium.ea.fitness.ObjectiveFunction;
import es.uma.lcc.caesium.ea.model.byzantine.fitness.FitnessInverter;
import es.uma.lcc.caesium.ea.model.byzantine.fitness.FitnessRandomizer;
import es.uma.lcc.caesium.ea.model.byzantine.fitness.UnreliableObjectiveFunction;
import es.uma.lcc.caesium.ea.model.byzantine.handler.AverageHandler;
import es.uma.lcc.caesium.ea.model.byzantine.handler.MajorityHandler;
import es.uma.lcc.caesium.ea.model.byzantine.operator.UnreliableReplacementFactory;
import es.uma.lcc.caesium.ea.model.byzantine.operator.UnreliableSelectionFactory;
import es.uma.lcc.caesium.ea.model.byzantine.operator.UnreliableVariationFactory;
import es.uma.lcc.caesium.ea.problem.discrete.binary.LeadingOnes;
import es.uma.lcc.caesium.ea.problem.discrete.binary.onemax.OneMax;
import es.uma.lcc.caesium.ea.problem.discrete.binary.trap.DeceptiveTrap;
import es.uma.lcc.caesium.ea.problem.discrete.binary.trap.MMDP;
import es.uma.lcc.caesium.ea.statistics.EntropyDiversity;

/**
 * Class for testing the evolutionary algorithm with unreliable objective functions
 * @author ccottap
 * @version 1.2
 */
public class RunUnreliableFitness {

	/**
	 * Main method
	 * @param args command-line arguments
	 * @throws JsonException if the configuration file is not correctly formatted
	 * @throws IOException if there is a problem reading the configuration or writing statistics
	 */
	public static void main(String[] args) throws JsonException, IOException {
		EAConfiguration conf;
		if (args.length < 1)
			conf = new EAConfiguration();
		else {
			FileReader reader = new FileReader(args[0]);
			conf = new EAConfiguration((JsonObject) Jsoner.deserialize(reader));
		}
		conf.setSelectionFactory(new UnreliableSelectionFactory());
		conf.setVariationFactory(new UnreliableVariationFactory());
		conf.setReplacementFactory(new UnreliableReplacementFactory());
		System.out.println(conf);
		
		List<ObjectiveFunction> problems = new ArrayList<ObjectiveFunction>();
		List<String> problemNames = new ArrayList<String>();
		
		problems.add(new OneMax(100));
		problemNames.add("onemax");
		
		problems.add(new DeceptiveTrap(25, 4));
		problemNames.add("trap");
		
		problems.add(new MMDP(17));
		problemNames.add("mmdp");
		
		problems.add(new LeadingOnes(100));
		problemNames.add("leadingones");

		List<String> handlers = new ArrayList<String>();
		handlers.add("none");
		handlers.add("majority");
		handlers.add("average");

		for (String handler: handlers) {
			for (int i=0; i<problems.size(); i++) {
				runTests (conf, problems.get(i), problemNames.get(i), "inverter", handler);
				runTests (conf, problems.get(i), problemNames.get(i), "randomizer", handler);
			}
		}
				
		
	}

	/**
	 * Runs the tests for different values of the unreliability probability on a specific fitness function
	 * @param conf configuration of the EA
	 * @param f the fitness function
	 * @param problem string with the problem name 
	 * @param model name of the the unreliability model used
	 * @param handler name of the unreliability handler used
	 * @throws IOException if the stats file cannot be created
	 */
	private static void runTests(EAConfiguration conf, ObjectiveFunction f, String problem, String model, String handler) throws IOException {
		EvolutionaryAlgorithm myEA = new EvolutionaryAlgorithm(conf);
		myEA.getStatistics().setDiversityMeasure(new EntropyDiversity());
		final int numreps = 3;
		int numruns = conf.getNumRuns();

		System.out.println("Running " + handler + " :: " + model + "(" + problem + ")");
		for (int p=50; p<=50; p+=5) {
			double prob = (double)p/100.0;
			System.out.println("Trying with prob = " + prob);
			UnreliableObjectiveFunction uf;
			switch(model) {
				case "inverter":
					uf = new FitnessInverter(f, prob);
					break;
				case "randomizer":
					uf = new FitnessRandomizer(f, prob);
					break;
				default:
					System.err.println("Error: unknown model " + model);
					return;
			}
			switch(handler) {
				case "none":
					myEA.setObjectiveFunction(uf);
					break;
				case "majority":
					myEA.setObjectiveFunction(new MajorityHandler(uf, numreps));
					break;
				case "average":
					myEA.setObjectiveFunction(new AverageHandler(uf, numreps));
					break;
				default:
					System.err.println("Error: unknown handler " + handler);
					return;
			}
			for (int i=0; i<numruns; i++) {
				myEA.run();
				System.out.println ("Run " + i + ": " + 
									String.format(Locale.US, "%.2f", myEA.getStatistics().getTime(i)) + "s" + 
									"\tfitness: " + myEA.getStatistics().getBest(i).getFitness());
			}
			String folderpath = handler + numreps + "/" + model + "/" + problem;
			try {
				Files.createDirectories(Paths.get(folderpath));
			}
			catch (FileAlreadyExistsException e) {}
			PrintWriter file = new PrintWriter(folderpath + "/stats_" + p + ".json");
			file.print(myEA.getStatistics().toJSON().toJson());
			file.close();
			myEA.getStatistics().clear();
		}		
	}
	


}
