# ea-model-byzantine

Evolutionary algorithms with Byzantine failures in fitness evaluation

Carlos Cotta, 2023

## Overview

Extension of a basic EA to objective functions that return unreliable fitness values. Two different models of Byzantine behavior are considered:

* Randomizer: the objective function returns the true fitness of a previously evaluated solution (randomly selected).
* Inverter: the objective function returns a value that represents the reflection of the true fitness of the solution being evaluated with respect to the maximum and minimum fitness values observes to the moment.

The algorithm can be run in the presence of these failures without further adjustment, or be endowed with some mechanism to handle this issue. Two possibilities are included, in both cases based on redundant computation:

* Average: repeat the evaluation k times and take the average value
* Majority: repeat the evaluaiton k times and take the most repeated value (or the average of most repeated values if there are several of them).

## Requirements

It is builds upon [this base EA library](https://github.com/Bio4Res/ea). A Maven dependency is included. Note that the base library requires JDK 17 or higher.

## References

1. C. Cotta, "[On the Performance of Evolutionary Algorithms with Unreliable Fitness Information](http://www.lcc.uma.es/~ccottap/papers/cotta23performance.pdf)", _EvoStar 2023 Late Breaking Abstracts_, Antonio M. Mora (Ed.), Brno, Czech Republic
2. C. Cotta, "Tackling Adversarial Faults in Panmictic Evolutionary Algorithms", _Genetic and Evolutionary Computation Conference Companion (GECCO '23 Companion)_, ACM Press, July 15-19, 2023, Lisbon, Portugal, doi:[10.1145/3583133.3596426](http://doi.org/10.1145/3583133.3596426)
3. C. Cotta, "Enhancing Evolutionary Optimization Performance under Byzantine Fault Conditions", _18th International Conference on Hybrid Artificial Intelligence Systems_, Springer, 2023 (in press)
