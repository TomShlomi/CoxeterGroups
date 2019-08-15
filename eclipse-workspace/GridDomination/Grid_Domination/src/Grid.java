import java.util.ArrayList;

public class Grid {
	private int dim; //dimension of the grid
	private int[][] bcs; //unused
	private int range; // unused
	private int[] bcpattern; //has length dim; represents a pattern of broadcasts on the grid; currently only works for dim = 2
	
	public Grid(int n) {
		dim = n;
	}
	
	public boolean fastDomCheck2d(int t, int r) {//checks whether the pattern dominates the grid with (t, r) domination
		//System.out.println(bcpattern[0] + " " + bcpattern[1]);
		int[] ints = new int[bcpattern[0]];
		ints[0] = t;
		for (int i = 1; i < t; i++) {
			ints[i] = ints[ints.length - i] = t-i;
		}
		for (int i = t - r + 1; i < (bcpattern[0]+1.0)/2.0; i++) {//Due to symmetry, only the points (0, 0)-((bcpattern[0]+1)/2, 0) must be checked. We know the points (0, 0) - (t - r) are dominated
			int rcptn = ints[i];							//by the origin broadcast, so they don't need to be checked. This iterates over the points that must be checked to make sure the grid is dominated
			for (int j = 1; j < t; j++) {
				if (ints[Main.mod(i+j*bcpattern[1], bcpattern[0])] > j) {
					rcptn += ints[Main.mod(i+j*bcpattern[1], bcpattern[0])] - j;
				}
				if (ints[Main.mod(i-j*bcpattern[1], bcpattern[0])] > j) {
					rcptn += ints[Main.mod(i-j*bcpattern[1], bcpattern[0])] - j;
				}
				if (rcptn >= r) {break;}
			}
			if (rcptn < r) {return false;}
		} 
		//System.out.println(bcpattern[1]);
		return true;
	}
	
	public boolean fastDomCheck3d(int t, int r) {//checks whether the pattern dominates the grid with (t, r) domination
		int[] ints = new int[bcpattern[0]];
		ints[0] = t;
		for (int i = 1; i < t; i++) {
			ints[i] = ints[ints.length - i] = t-i;
		}
		for (int i = t - r + 1; i < (bcpattern[0]+1.0)/2.0; i++) {//Due to symmetry, only the points (0, 0)-((bcpattern[0]+1)/2, 0) must be checked. We know the points (0, 0) - (t - r) are dominated
			int rcptn = 0;							//by the origin broadcast, so they don't need to be checked. This iterates over the points that must be checked to make sure the grid is dominated
			for (int j = 0; j < t; j++) {
				for (int k = 0; k < t - j; k++) {
					if (ints[Main.mod(i+j*bcpattern[1]+k*bcpattern[2], bcpattern[0])] > j + k) {
						rcptn += ints[Main.mod(i+j*bcpattern[1]+k*bcpattern[2], bcpattern[0])] - j - k;
					}
					if (j != 0 && ints[Main.mod(i-j*bcpattern[1]+k*bcpattern[2], bcpattern[0])] > j + k) {
						rcptn += ints[Main.mod(i-j*bcpattern[1]+k*bcpattern[2], bcpattern[0])] - j - k;
					}
					if (k != 0 && ints[Main.mod(i+j*bcpattern[1]-k*bcpattern[2], bcpattern[0])] > j + k) {
						rcptn += ints[Main.mod(i+j*bcpattern[1]-k*bcpattern[2], bcpattern[0])] - j - k;
					}
					if (j != 0 && k != 0 && ints[Main.mod(i-j*bcpattern[1]-k*bcpattern[2], bcpattern[0])] > j + k) {
						rcptn += ints[Main.mod(i-j*bcpattern[1]-k*bcpattern[2], bcpattern[0])] - j - k;
					}
				}
				if (rcptn >= r) {break;}
			}
			if (rcptn < r) {return false;}
		}
		return true;
	}
	
	public boolean fastDomCheck4d(int t, int r) {//checks whether the pattern dominates the grid with (t, r) domination
		int[] ints = new int[bcpattern[0]];	
		ints[0] = t;
		for (int i = 1; i < t; i++) {
			ints[i] = ints[ints.length - i] = t-i;
		}
		for (int i = t - r + 1; i < (bcpattern[0]+1.0)/2.0; i++) {//Due to symmetry, only the points (0, 0)-((bcpattern[0]+1)/2, 0) must be checked. We know the points (0, 0) - (t - r) are dominated
			int rcptn = 0;							//by the origin broadcast, so they don't need to be checked. This iterates over the points that must be checked to make sure the grid is dominated
			for (int j = 0; j < t; j++) {
				for (int k = 0; k < t - j; k++) {
					for (int l = 0; l < t - j - k; l++) {	
						if (ints[Main.mod(i+j*bcpattern[1]+k*bcpattern[2]+l*bcpattern[3], bcpattern[0])] > j+k+l) {
							rcptn += ints[Main.mod(i+j*bcpattern[1]+k*bcpattern[2]+l*bcpattern[3], bcpattern[0])] -j-k-l;
						}
						if (j!= 0 && ints[Main.mod(i-j*bcpattern[1]+k*bcpattern[2]+l*bcpattern[3], bcpattern[0])] > j+k+l) {
							rcptn += ints[Main.mod(i-j*bcpattern[1]+k*bcpattern[2]+l*bcpattern[3], bcpattern[0])] -j-k-l;
						}
						if (k!= 0 && ints[Main.mod(i+j*bcpattern[1]-k*bcpattern[2]+l*bcpattern[3], bcpattern[0])] > j+k+l) {
							rcptn += ints[Main.mod(i+j*bcpattern[1]-k*bcpattern[2]+l*bcpattern[3], bcpattern[0])] -j-k-l;
						}
						if (j!= 0 && k!= 0 && ints[Main.mod(i-j*bcpattern[1]-k*bcpattern[2]+l*bcpattern[3], bcpattern[0])] > j+k+l) {
							rcptn += ints[Main.mod(i-j*bcpattern[1]-k*bcpattern[2]+l*bcpattern[3], bcpattern[0])] -j-k-l;
						}
						if (l!= 0 && ints[Main.mod(i+j*bcpattern[1]+k*bcpattern[2]-l*bcpattern[3], bcpattern[0])] > j+k+l) {
							rcptn += ints[Main.mod(i+j*bcpattern[1]+k*bcpattern[2]-l*bcpattern[3], bcpattern[0])] -j-k-l;
						}
						if (j!= 0 && l!= 0 && ints[Main.mod(i-j*bcpattern[1]+k*bcpattern[2]-l*bcpattern[3], bcpattern[0])] > j+k+l) {
							rcptn += ints[Main.mod(i-j*bcpattern[1]+k*bcpattern[2]-l*bcpattern[3], bcpattern[0])] -j-k-l;
						}
						if (k!= 0 && l!= 0 && ints[Main.mod(i+j*bcpattern[1]-k*bcpattern[2]-l*bcpattern[3], bcpattern[0])] > j+k+l) {
							rcptn += ints[Main.mod(i+j*bcpattern[1]-k*bcpattern[2]-l*bcpattern[3], bcpattern[0])] -j-k-l;
						}
						if (j!= 0 && l!= 0 && k!= 0 && ints[Main.mod(i-j*bcpattern[1]-k*bcpattern[2]-l*bcpattern[3], bcpattern[0])] > j+k+l) {
							rcptn += ints[Main.mod(i-j*bcpattern[1]-k*bcpattern[2]-l*bcpattern[3], bcpattern[0])] -j-k-l;
						}
						
					}
				}
				if (rcptn >= r) {break;}
			}
			if (rcptn < r) {return false;}
		}
		return true;
	}
	
	public boolean fastDomChecknd(int n, int t, int r) {//checks whether the pattern dominates the grid with (t, r) domination
		int[] ints = new int[bcpattern[0]];	
		ints[0] = t;
		for (int i = 1; i < t; i++) {
			ints[i] = ints[ints.length - i] = t-i;
		}
		int[] shifts = new int[n];
		for (shifts[0] = t - r + 1; shifts[0] < (bcpattern[0]+1.0)/2.0; shifts[0]++) {//Due to symmetry, only the points (0, 0)-((bcpattern[0]+1)/2, 0) must be checked. We know the points (0, 0) - (t - r) are dominated
			int rcptn = 0;						//by the origin broadcast, so they don't need to be checked. This iterates over the points that must be checked to make sure the grid is dominated
			int[] rc = new int[n];
			for (int i = 1; i < n; i++) {
				rc[i] = 1;
			}
			while (true) {
				rcptn += Math.max(ints[Main.mod(shifts[0] + dot(shifts, bcpattern, 1), bcpattern[1])] - pSum(shifts, 1), 0);
				for (int i = shifts.length - 1; i > 0; i--) {
					if (shifts[i] == 1) {
						shifts[i] = -1;
						for (int j = i+1; j < shifts.length; j++) {
							shifts[j] = 1;
						}
						break;
					}
				}
				for (int i = 1; i < shifts.length; i++) {
					if (shifts[i] == 1) {
						continue;
					}
				}
				break;
			}
			for (int j = 0; j < t; j++) {
				for (int k = 0; k < t - j; k++) {
					for (int l = 0; l < t - j - k; l++) {	
						if (ints[Main.mod(shifts[0]+j*bcpattern[1]+k*bcpattern[2]+l*bcpattern[3], bcpattern[0])] > j+k+l) {
							rcptn += ints[Main.mod(shifts[0]+j*bcpattern[1]+k*bcpattern[2]+l*bcpattern[3], bcpattern[0])] -j-k-l;
						}
						if (j!= 0 && ints[Main.mod(shifts[0]-j*bcpattern[1]+k*bcpattern[2]+l*bcpattern[3], bcpattern[0])] > j+k+l) {
							rcptn += ints[Main.mod(shifts[0]-j*bcpattern[1]+k*bcpattern[2]+l*bcpattern[3], bcpattern[0])] -j-k-l;
						}
						if (k!= 0 && ints[Main.mod(shifts[0]+j*bcpattern[1]-k*bcpattern[2]+l*bcpattern[3], bcpattern[0])] > j+k+l) {
							rcptn += ints[Main.mod(shifts[0]+j*bcpattern[1]-k*bcpattern[2]+l*bcpattern[3], bcpattern[0])] -j-k-l;
						}
						if (j!= 0 && k!= 0 && ints[Main.mod(shifts[0]-j*bcpattern[1]-k*bcpattern[2]+l*bcpattern[3], bcpattern[0])] > j+k+l) {
							rcptn += ints[Main.mod(shifts[0]-j*bcpattern[1]-k*bcpattern[2]+l*bcpattern[3], bcpattern[0])] -j-k-l;
						}
						if (l!= 0 && ints[Main.mod(shifts[0]+j*bcpattern[1]+k*bcpattern[2]-l*bcpattern[3], bcpattern[0])] > j+k+l) {
							rcptn += ints[Main.mod(shifts[0]+j*bcpattern[1]+k*bcpattern[2]-l*bcpattern[3], bcpattern[0])] -j-k-l;
						}
						if (j!= 0 && l!= 0 && ints[Main.mod(shifts[0]-j*bcpattern[1]+k*bcpattern[2]-l*bcpattern[3], bcpattern[0])] > j+k+l) {
							rcptn += ints[Main.mod(shifts[0]-j*bcpattern[1]+k*bcpattern[2]-l*bcpattern[3], bcpattern[0])] -j-k-l;
						}
						if (k!= 0 && l!= 0 && ints[Main.mod(shifts[0]+j*bcpattern[1]-k*bcpattern[2]-l*bcpattern[3], bcpattern[0])] > j+k+l) {
							rcptn += ints[Main.mod(shifts[0]+j*bcpattern[1]-k*bcpattern[2]-l*bcpattern[3], bcpattern[0])] -j-k-l;
						}
						if (j!= 0 && l!= 0 && k!= 0 && ints[Main.mod(shifts[0]-j*bcpattern[1]-k*bcpattern[2]-l*bcpattern[3], bcpattern[0])] > j+k+l) {
							rcptn += ints[Main.mod(shifts[0]-j*bcpattern[1]-k*bcpattern[2]-l*bcpattern[3], bcpattern[0])] -j-k-l;
						}
						
					}
				}
				if (rcptn >= r) {break;}
			}
			if (rcptn < r) {return false;}
		}
		return true;
	}

	public boolean checkDomination2d(int t, int r) {//checks whether the pattern dominates the grid with (t, r) domination
		for (int i = t - r + 1; i < (bcpattern[0]+1.0)/2.0; i++) {//Due to symmetry, only the points (0, 0)-((bcpattern[0]+1)/2, 0) must be checked. We know the points (0, 0) - (t - r) are dominated
			int rcptn = 0;							//by the origin broadcast, so they don't need to be checked. This iterates over the points that must be checked to make sure the grid is dominated
			boolean done = false;
			for (int j = i - t; j < i + t; j++) {//Iterates over the region where broadcasts could be providing reception for the point (i, 0). 
				for (int k = Math.abs(j - i) - t; k < t - Math.abs(j - i); k++) {
					int[] bc = new int[] {j, k};
					if (isBC(bc)) {//Checks whether the point {j, k} is a broadcast
						rcptn += t - distance(bc, new int[] {i, 0}); //if so, it adds that broadcast's contribution to the reception at (i, 0).
						if (rcptn >= r) {
							done = true;
							break;
						}
					}
				if (done) {break;}
				}
			}
			if (rcptn < r) {
				return false;
			}
		}
		return true;
	}
	
	public boolean checkDomination3d(int t, int r) {//checks whether the pattern dominates the grid with (t, r) domination
		for (int i = t - r + 1; i < (bcpattern[0]+1.0)/2.0; i++) {//Due to symmetry, only the points (0, 0)-((bcpattern[0]+1)/2, 0) must be checked. We know the points (0, 0) - (t - r) are dominated
			int rcptn = 0;							//by the origin broadcast, so they don't need to be checked. This iterates over the points that must be checked to make sure the grid is dominated
			boolean done = false;
			for (int j = i - t; j < i + t; j++) {//Iterates over the region where broadcasts could be providing reception for the point (i, 0). 
				for (int k = Math.abs(j - i) - t; k < t - Math.abs(j - i); k++) {
					for (int l = Math.abs(k) + Math.abs(j - i) - t; l < t - Math.abs(k) - Math.abs(j - i); l++) {
						int[] bc = new int[] {j, k, l};
						if (isBC(bc)) {//Checks whether the point {j, k} is a broadcast
							rcptn += t - distance(bc, new int[] {i, 0, 0}); //if so, it adds that broadcast's contribution to the reception at (i, 0).
							if (rcptn >= r) {
								done = true;
								break;
							}
						}
					}
					if (done) {break;}
				}
				if (done) {break;}
			}
			if (rcptn < r) {
				return false;
			}
		}
		return true;
	}
	
	public boolean checkDomination4d(int t, int r) {//checks whether the pattern dominates the grid with (t, r) domination
		for (int i = t - r + 1; i < (bcpattern[0]+1.0)/2.0; i++) {//Due to symmetry, only the points (0, 0)-((bcpattern[0]+1)/2, 0) must be checked. We know the points (0, 0) - (t - r) are dominated
			int rcptn = 0;							//by the origin broadcast, so they don't need to be checked. This iterates over the points that must be checked to make sure the grid is dominated
			boolean done = false;
			for (int j = i - t; j < i + t; j++) {//Iterates over the region where broadcasts could be providing reception for the point (i, 0). 
				for (int k = Math.abs(j - i) - t; k < t - Math.abs(j - i); k++) {
					for (int l = Math.abs(k) + Math.abs(j - i) - t; l < t - Math.abs(k) - Math.abs(j - i); l++) {
						for (int m = l + k + Math.abs(j - i); m < t - l - k - Math.abs(j - i); m++) {
							int[] bc = new int[] {j, k, l, m};
							if (isBC(bc)) {//Checks whether the point {j, k, l, m} is a broadcast
								rcptn += t - distance(bc, new int[] {i, 0, 0, 0}); //if so, it adds that broadcast's contribution to the reception at (i, 0).
								if (rcptn >= r) {
									done = true;
									break;
								}
							}
						}
					}
					if (done) {break;}
				}
				if (done) {break;}
			}
			if (rcptn < r) {
				return false;
			}
		}
		return true;
	}
	
/** for (int i = t - r + 1; i < (bcpattern[0]+1.0)/2.0; i++) {//this should eventually replace the text of the checkDomination method. It does not currently work.
			int rcptn = 0;
			boolean done = false;
			for (int k = - t; k < t; k++) {
				int modv = Main.mod(bcpattern[1]*k, bcpattern[0]);
				for (int j = (int) (Math.ceil(((Math.abs(k) - t)*1.0/bcpattern[0] - modv/bcpattern[0]))*bcpattern[0] + modv); j < t - Math.abs(k); j += bcpattern[0]) {
					rcptn += t - distance(new int[] {j, k}, new int[] {i, 0});
					if (rcptn >= r) {
						done = true;
						break;
						}
				}
				if (done) {break;}
			}
			if (rcptn < r) {
				return false;
			}
		}
		return true; */
	
	public void printBCInArea(int t, int[] p) {//prints the broadcasts that contribute to the reception of a point
		for (int j = p[0] - t; j < p[0] + t; j++) {
			for (int k = p[1] - t; k < p[1] + t; k++) {
				int[] bc = new int[] {j, k};
				if (isBC(bc) && distance(bc, p) < t) {
					System.out.print("d((" + bc[0] + ", " + bc[1] + "), (" + p[0] + ", " + p[1] + ")) = "+ distance(bc, p) + "; ");
				}
			}
		}
		System.out.println(bcpattern[1] + " " + bcpattern[0]);
	}

	public int distance(int[] p1, int[] p2) {//gets the distance between two points
		int d = 0;
		for (int i = 0; i < dim; i++) {
			d += Math.abs(p1[i] - p2[i]);
		}
		return d;
	}

	public void setBCPattern(int[] pattern) {//sets the broadcast pattern of the grid
		bcpattern = pattern;
	}
	
	public boolean isBC(int[] p) {//checks whether a point is a broadcast
		int dot = 0;
		for (int i = 1; i < p.length; i++) {
			dot += bcpattern[i]*p[i];
		}
		return (p[0] - dot) % bcpattern[0] == 0;
	}
	
	public int pSum(int[] a, int s) {
		int sum = 0;
		for (int i = s; i < a.length; i++) {
			sum += a[i];
		}
		return sum;
	}
	public int dot(int[] a, int[] b, int s) {
		int sum = 0;
		for (int i = s; i < a.length && i < b.length; i++) {
			i += a[i]*b[i];
		}
		return sum;
	}
	//Methods below are unused
	public boolean checkLocalDomination(int t, int r) {
		int[] p = new int[dim];
		for (int i = 0; i < dim; i++) {
			p[i] = -range;
		}
		int i = 0;
		while (true) { //System.out.println(p[0] + " " + p[1]);
			if (!isDominated(p, t, r)) {
				return false;
			}
			i = 0;
			while (p[i] >= range) {
				p[i] = -range;
				i++;
				if (i >= dim) {
					return true;
				}
			}
			p[i]++;
			}
	}
	
	public boolean isDominated(int[] p, int t, int r) {
		int rcptn = 0;
		for (int[] bc : bcs) {
			int d = distance(p, bc);
			if (d < t) {
				rcptn += t - d;
			}
		}
		return rcptn >= r;
	}
	
	public void setBCs(int[] v1, int[] v2) {
		range = 0;
		for (int i = 0; i < v1.length; i++) {
			range = Math.max(range, v1[i]);
			range = Math.max(range, v2[i]);
		}
		ArrayList<int[]> bcl = new ArrayList<>();
		
		for (int i = -1*range; i <= range; i++) {
			for (int j = -1*range; j <= range; j++) {
				bcl.add(addPnts(scaleProd(v1, i), scaleProd(v2, j)));
			}
		}
		bcs = new int[bcl.size()][Math.min(v1.length, v2.length)];
		for (int i = 0; i < bcs.length; i++) {
			bcs[i] = bcl.get(i);
		}
	}
	
	private static int[] addPnts(int[] p1, int[] p2) {
		int[][] ps = new int[2][p1.length];
		ps[0] = p1; ps[1] = p2;
		return addPnts(ps);
	}
	
	private static int[] addPnts (int[][] ps) {
		int[] sum = new int[ps[0].length];
		for (int i = 0; i < sum.length; i++) {
			sum[i] = 0;
			for (int j = 0; j < ps.length; j++) {
				sum[i] += ps[j][i];
			}
		}
		return sum;
	}
	
	private static int[] scaleProd (int[] p, int s) {
		int[] ret = new int[p.length];
		for (int i = 0; i < p.length; i++) {
				ret[i] = p[i]*s;
		}
		return ret;
	}
	
}
