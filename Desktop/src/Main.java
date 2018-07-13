
public class Main {

	public static void main(String[] args) {
		runPCA(2);
	}
	
	public static void runPCA(int k) {
		double[][] arr = {{1, 3, 2}, {2, 3, 4}, {3, 6, 2}, {5, 7, 0}, {1, -1, 3}};
		DataSet ds = new DataSet(arr);
		double[][] ret = ds.SVD(k);
		for (int i = 0; i < ret.length; i++) {
			System.out.println();
			for (int j = 0; j < ret[i].length; j++) {
				System.out.print(ret[i][j] + " ");
			}
		}
	}

}
