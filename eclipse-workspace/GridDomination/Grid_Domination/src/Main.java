public class Main {
	public static void main(String[] args) {
	int dimensions = 4;
	int min_t = 1;
	int max_t = 10;
	switch (dimensions) {
	case 2: d2trTest(min_t, max_t); break;
	case 3: d3trTest(min_t, max_t); break;
	case 4: d4trTest(min_t, max_t); break;
	}
	}
	
	private static boolean patternTest(int t, int r, int[] pattern, Grid g) {
		g.setBCPattern(pattern);
		return g.fastDomCheck2d(t, r);
	}
	
	private static void d2trTest(int t0, int tmax) {
		System.out.println();
		Grid g = new Grid(2);
		for (int t = t0; t <= tmax; t++) { //Iterates over possible values of t
			for (int r = 1; r <= t; r++) {//Iterates over possible values of r
				double upbound = d2maxDom(t, r); //Gets the maximum possible value of the reciprocal of the broadcast density
				boolean done = false;
				for (int d = (int) Math.floor(upbound); d >= 1; d--) { //j represents the inverse of the density of a particular broadcast pattern
					for (int e = 0; e < d; e++) { //represents the shift in the x values of the broadcasts between adjacent rows of the grid
						if (patternTest(t, r, new int[] {d, e}, g)) { 
							System.out.println("t = " + t + ", r = " + r + "; Upper bound = " + upbound + "; Optimal tower is T(" + d + ", " + e + ")");
							done = true;//if the pattern {i, j} dominates the infinite grid, it breaks and goes to the next (t, r)
							break;//since j iterates down from its maximum possible value, the j it breaks at must be the highest working value 			
						}
					}
					if (done) {
						break;
					}
				}
			}
		}
	}
	
	private static void d3trTest(int t0, int tmax) {
		Grid g = new Grid(3);
		for (int t = t0; t <= tmax; t++) { //Iterates over possible values of t
			for (int r = 1; r <= t; r++) {//Iterates over possible values of r
				double upbound = d3maxDom(t, r); //Gets the maximum possible value of the reciprocal of the broadcast density
				boolean done = false;
				for (int d = (int) Math.floor(upbound); d >= 1; d--) { //represents the inverse of the density of a particular broadcast pattern
					for (int e = 0; e <= d/2.0; e++) { //represents the shift in the x values of the broadcasts between adjacent rows of the grid
						for (int f = 0; f <= e; f++) {
							//System.out.println(d+ " " + e + " " + f);
							g.setBCPattern(new int[] {d, e, f});
							boolean dominates = g.fastDomCheck3d(t, r);
							if (dominates) { 
								System.out.println("t = " + t + ", r = " + r + "; Upper bound = " + upbound + "; Optimal tower is T(" + d + ", " + e + ", " + f + ")");
								done = true;//if the pattern {i, j} dominates the infinite grid, it breaks and goes to the next (t, r)
								break;//since j iterates down from its maximum possible value, the j it breaks at must be the highest working value 			
							}
						}
						if (done) {break;}
					}
					if (done) {break;}
				}
			}
		}
	}
	
	private static void d4trTest(int t0, int tmax) { 
		Grid grid = new Grid(4);
		for (int t = t0; t <= tmax; t++) { //Iterates over possible values of t
			for (int r = 1; r <= t; r++) {//Iterates over possible values of r
				double upbound = d4maxDom(t, r); //Gets the maximum possible value of the reciprocal of the broadcast density
				boolean done = false;
				for (int d = (int) Math.floor(upbound); d >= 1; d--) { //j represents the inverse of the density of a particular broadcast pattern
					for (int e = 0; e <= d/2.0; e++) { //represents the shift in the x values of the broadcasts between adjacent rows of the grid
						for (int f = 0; f <= e; f++) {
							for (int g = 0; g <= f; g++) {
								grid.setBCPattern(new int[] {d, e, f, g});
								boolean dominates = grid.fastDomCheck4d(t, r);
								if (dominates) { 
									System.out.println("t = " + t + ", r = " + r + "; Upper bound = " + upbound + "; Optimal tower is T(" + d + ", " + e + ", " + f + ", " + g + ")");
									done = true;//if the pattern {i, j} dominates the infinite grid, it breaks and goes to the next (t, r)
									break;//since j iterates down from its maximum possible value, the j it breaks at must be the highest working value 			
								}
							}
							if (done) {break;}
						}
						if (done) {break;}
					}
					if (done) {break;}
				}
			}
		}
	}

	public static int mod(int x, int y) {//returns x mod y, unlike % and Math.mod
		return (Math.floorMod(x, y) + Math.abs(y)) % Math.abs(y);
	}
	
	public static double d2maxDom(int t, int r) {//returns the maximum possible number of vertices a single (t, r) broadcast dominates in a 2d grid
		return (r - 2*(-1 + r)*r*(-1 + 2*r - 3*t)/3.0 + 2*r*(-1 + r - t)*(r - t))/r;
	}
	
	public static double d3maxDom(int t, int r) {//returns the maximum possible number of vertices a single (t, r) broadcast dominates in a 3d grid
		return (int) Math.ceil((-r*r*r+4*r*r*t-6*r*t*t-2*r+4*t*t*t+4*t)/3.0);
	}
	
	public static int d4maxDom(int t, int r) {//returns the maximum possible number of vertices a single (t, r) broadcast dominates in a 4d grid
		return (int) Math.ceil(2.0*r*r*r*r/15 - 2.0*r*r*r*t/3 + 4.0*r*r*t*t/3 + 2.0*r*r/3 - 4.0*r*t*t*t/3 - 2.0*r*t + 2.0*t*t*t*t/3 + 2.0*t*t + 1.0/5);
	}
	
	//All methods below are unused and/or don't work properly

	private static void dntrTest(int n, int t0, int tmax) { 
		Grid grid = new Grid(n);
		int[] ds = new int[n];
		for (int t = t0; t <= tmax; t++) { //Iterates over possible values of t
			for (int r = 1; r <= t; r++) {//Iterates over possible values of r
				double upbound = d4maxDom(t, r); //Gets the maximum possible value of the reciprocal of the broadcast density
				boolean done = false;
				for (ds[0] = (int) Math.floor(upbound); ds[0] >= 1; ds[0]--) { //j represents the inverse of the density of a particular broadcast pattern
					while (true) {
						grid.setBCPattern(ds);
						System.out.println(upbound + " " + ds[0] + " " + ds[1] + " " + ds[2] + " " + ds[3]);
						boolean dominates = grid.fastDomCheck4d(t, r);
						if (dominates) { 
							System.out.println("t = " + t + ", r = " + r + ", " + upbound + " " + ds[0]);
							done = true;//if the pattern {i, j} dominates the infinite grid, it breaks and goes to the next (t, r)
							break;//since j iterates down from its maximum possible value, the j it breaks at must be the highest working value 			
						}
						for (int i = ds.length - 1; i > 0; i--) {
							if (ds[i] < ds[i-1]) {
								ds[i]++;
								for (int j = i+1; j < ds.length; j++) {
									ds[j] = 0;
								}
								break;
							}
						}
						if (ds[ds.length-1] > ds[0]/2.0) {
							for (int i = 1; i < ds.length; i++) {
								ds[i] = 0;
							}
							break;
						}
					}
					if (done) {break;}
				}
			}
		}
	}
	
	private static void patternsTest (int t, int r, int max) { //unused
	Grid g = new Grid(2);
	for (int i = 0; i <= max; i++) {
		for (int j = 1; j <= max; j++) {
			if (patternTest(t, r, new int[] {i, j}, g)) {
				System.out.println(i + " " + j);
			}
		}
	}
}
	
	private static boolean d2Test(int[] v1, int[] v2, int t, int r, Grid g) {
		g.setBCs(v1, v2);
		return g.checkLocalDomination(t, r);
	}
	
	private static int d2v1Test(int[] v1, int max, int t, int r, Grid g) {
		int maxtrue = -1;
		for (int i = 1; i <= max; i++) {
			if (d2Test(v1, new int[] {i, 0}, t, r, g)) {
				maxtrue = i;
			}
		}
		return maxtrue;
	}
	
	private static void bulkTest(int[] r1, int[] r2, int t, int r) {
		Grid g = new Grid(2);
		for (int i = r1[0]; i <= r1[1]; i++) {
			for (int j = r2[0]; j <= r2[1]; j++) {
				System.out.println(i + " " + j + " " + d2v1Test(new int[] {i, j}, 100, t, r, g));
			}
		}
	}
}
