public class Knapsack_DP {
	private int[] p;
	private int[] w;
	private int W;

	public Knapsack_DP(int[] a, int[] b, int c) {
		p = a;
		w = b;
		W = c;
	}

	public void solve() {
		// P[i][w] = maximum profits of i items under the constrain w
		int n = p.length;
		int[][] P = new int[n+1][W+1];

		// Initialize P
		for(int i=0; i<=n; ++i) 
			for(int j=0; j<=W; ++j) {
				P[i][j] = 0;
			}

		// Compute P
		for(int i=1; i<=n; ++i) {
			//======================================================
			for(int weight=0;weight<=W;weight++) {
				if(w[i-1]<=weight) {
					if(P[i-1][weight]<P[i-1][weight-w[i-1]]+p[i-1]){
						P[i][weight]=P[i-1][weight-w[i-1]]+p[i-1];
					}else {
						P[i][weight]=P[i-1][weight];
					}
				}else {
					P[i][weight]=P[i-1][weight];
				}
			}
			
							
			//======================================================
		}
		printMatrix("",P);
		System.out.println("P[n][W] = " + P[n][W]);
	}

	// Print a matrix
	private void printMatrix(String name, int[][] a) {
		System.out.println(name + " = ");
		for(int i=0; i<a.length; ++i) {
			for(int j=0; j<a[i].length; ++j) {
				if (a[i][j] == Integer.MAX_VALUE)
					System.out.print(" INF");
				else
					System.out.format("%4d", a[i][j]);
			}
			System.out.println(); 
		}		
	}

	// The main function
	public static void main(String[] args) {
		// Figure 4.13
		System.out.println("========== Solve Figure 4.13 ==========");		
		int[] p = {50,60,140};  // the profits
		int[] w = {5,10,20};    // the weights
		int W = 30;             // the total weight restriction

		Knapsack_DP h = new Knapsack_DP(p,w,W);
		h.solve();
		
		
		// Chapter 5 Exercise 33
		System.out.println("\n========== Solve Chapter 5 Exercise 33 ==========");
		//========================================================
		int[] p1 = {20,35,30,12,3};
		int[] w1 = {2,5,7,3,1};
		int W1 = 9;
		Knapsack_DP h1 = new Knapsack_DP(p1,w1,W1);
		h1.solve();
		//========================================================            
	}
}
