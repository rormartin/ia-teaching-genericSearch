ia-teaching-genericSearch
=========================

Generic library for teaching different search strategy. Used in the
University Rey Juan Carlos, in subject IA Fundamentals (Fundamentos de
Inteligencia Articial) between 2010-2012.
The library was develop just for teaching purposes.
The advantage of the library is that it include a 2 statistic visualization ways:
- textual one with some metrics (states explored, expanded, time, etc)
- visual one, with the same information of the textual one plus a graphical
  representation for the search tree, with nodes information and the exploration order.

The source include 2 simple samples to show how to use the library: the classic n-puzzle problem
and the classic find-the-number problem.

The library includes this search strategies:
- Breadth search
- Depth search
- Iterative depth search
- Greedy search (based in cost and based in heuristics)
- A-start search

To use the library, just generate the package with maven (mvn package) and include it
in your projects.
