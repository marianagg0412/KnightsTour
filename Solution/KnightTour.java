package Solution;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;


public class KnightTour { 
	static int N = 8;
	//Movimientos validos del caballo
	static int[] xPosibles = { 2, 1, -1, -2, -2, -1, 1, 2 };
	static int[] yPosibles = { 1, 2, 2, 1, -1, -2, -2, -1 };
	static int maxIterations = 100;
	static int startx = 0;
	static int starty = 0;

	public static int getStartx() {
		return startx;
	}

	public static void setStartx(int startx) {
		KnightTour.startx = startx;
	}

	public static int getStarty() {
		return starty;
	}

	public static void setStarty(int starty) {
		KnightTour.starty = starty;
	}

	static boolean isSafe(int x, int y)
	{
		return (x >= 0 && x < N && y >= 0 && y < N);
	}

	static int getDegree(int x, int y, int sol[][]) {
		int count = 0;
		for (int i = 0; i < 8; i++) {
			int next_x = x + xPosibles[i];
			int next_y = y + yPosibles[i];
			if (isSafe(next_x, next_y) && sol[next_x][next_y] == -1)
				count++;
		}
		return count;
	}


	static void printSolution(int solution[][])
	{
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++)
				System.out.print(solution[x][y] + " ");
			System.out.println();
		}
	}

	public static boolean Solve(){
		int[][] sol = new int[N][N];

		//initializing matriz in 'unvisited'
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				sol[i][j] =-1;
			}
		}
		sol[startx][starty] = 0;

		if(!SolveKT(startx, starty, sol, 1,0)){
			return false;
		}
		else{
			System.out.println("Solution found");
			printSolution(sol);
			return true;
		}
	}

	// Recursive (backtracking), depth-first approach
	static boolean SolveKT(int x, int y, int[][] solMatrix, int moveC, int iteration) {
		if(iteration > maxIterations) {
			return false;  // Exit if the maximum number of iterations is reached
		}
		if(moveC == N*N && isClosed(x, y, solMatrix)) {
			return true;
		}

		// Array to store next moves based on their degrees
		int[][] nextMoves = new int[8][3];

		// Try all next moves
		for(int k = 0; k < 8; k++) {
			int next_x = x + xPosibles[k];
			int next_y = y + yPosibles[k];
			if(isSafe(next_x, next_y) && solMatrix[next_x][next_y] == -1) {
				nextMoves[k][0] = next_x;
				nextMoves[k][1] = next_y;
				nextMoves[k][2] = getDegree(next_x, next_y, solMatrix);
			} else {
				nextMoves[k][2] = -1;  // Invalid move
			}
		}

		// Sort the moves based on their degree
		Arrays.sort(nextMoves, new Comparator<int[]>() {
			@Override
			public int compare(int[] move1, int[] move2) {
				return Integer.compare(move1[2], move2[2]);
			}
		});

		// Try the moves starting from the one with the lowest degree
		for(int[] move : nextMoves) {
			if(move[2] != -1) {
				solMatrix[move[0]][move[1]] = moveC;
				moveC++;
				iteration++;
				if(SolveKT(move[0], move[1], solMatrix, moveC, iteration)) {
					return true;
				} else {
					solMatrix[move[0]][move[1]] = -1;
					moveC--;
					iteration--;
				}
			}
		}
		return false;
	}


	static boolean isClosed(int x, int y, int[][] sol){
		boolean flag1 = false;
		boolean flag2 = true;
			int i = 0;
			while(i < 8 && !(startx == x + xPosibles[i] && starty == y + yPosibles[i]))
				i++;
			if(i < 8)
				flag1 = true;

		for(i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				if(sol[i][j] == -1)
					flag2 = false;
			}
		}
		return flag1 && flag2;
	}


	public static void main(String args[]) {
		Scanner datos=new Scanner(System.in);
		System.out.println("Introduce the x position: ");
		startx =datos.nextInt();
		System.out.println("Introduce the y position: ");
		starty=datos.nextInt();
		if(!Solve()){
			System.out.println("Solution not found");
		}
	}
} 

