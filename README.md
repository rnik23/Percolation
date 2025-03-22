# Percolation Project

This project estimates the percolation threshold via Monte Carlo simulation. It is part of the Princeton Algorithms course assignments and is based on the work by Bob Sedgewick and Kevin Wayne.

## Overview

A percolation system is modeled as an n-by-n grid of sites, each of which is either open or blocked. The system is said to percolate if there exists a path of connected open sites from the top row to the bottom row. This project implements a `Percolation` data type that uses the weighted quick-union algorithm (via `WeightedQuickUnionUF`) to efficiently determine connectivity. In addition, a `PercolationStats` data type is provided to perform multiple experiments and estimate the percolation threshold (i.e., the fraction of open sites when the system percolates).

## How It Works

1. **Percolation Model**:  
   The grid is represented as a 2D boolean array where `true` indicates an open site and `false` indicates a blocked site. Two virtual nodes are used:
   - A **virtual top** node connected to all sites in the top row.
   - A **virtual bottom** node connected to all sites in the bottom row.
   
   The system percolates when these two virtual nodes become connected through open sites.

2. **Union-Find Data Structure**:  
   The project uses two instances of the union-find data structure:
   - One (`uf`) for percolation checks, which connects both virtual top and virtual bottom nodes.
   - A second (`uf2`) for fullness checks (to avoid the "backwash" problem), which connects only to the virtual top.

3. **Monte Carlo Simulation**:  
   The `PercolationStats` class performs T independent experiments:
   - Start with all sites blocked.
   - Randomly open blocked sites until the system percolates.
   - Record the fraction of open sites (percolation threshold) for each experiment.
   - Compute the sample mean, standard deviation, and the 95% confidence interval for the threshold.

## Prerequisites

- Java (version 11 or later is recommended)
- [algs4.jar](https://algs4.cs.princeton.edu/code/) (provided by the course)
- (Optional) IntelliJ IDEA with the provided custom IntelliJ programming environment

## Installation and Setup

1. **Download and Set Up the Java Environment**:  
   Follow the step-by-step instructions provided for your operating system (Mac OS X, Windows, or Linux) to install the custom IntelliJ programming environment.

2. **Compile the Code**:  
   Place `Percolation.java` and `PercolationStats.java` in the default package (do not add a package statement). Then compile using:
   ```bash
   javac-algs4 Percolation.java PercolationStats.java
