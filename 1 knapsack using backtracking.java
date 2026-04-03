import java.util.Scanner;

public class KnapsackBacktracking {

    static int maxProfit = 0;

    // Backtracking function
    static void knapsack(int i, int profit, int weight,
                         int[] wt, int[] val, int n, int capacity) {

        // If within capacity, update max profit
        if (weight <= capacity && profit > maxProfit) {
            maxProfit = profit;
        }

        // If more items exist
        if (i < n) {

            // Include current item
            if (weight + wt[i] <= capacity) {
                knapsack(i + 1,
                        profit + val[i],
                        weight + wt[i],
                        wt, val, n, capacity);
            }

            // Exclude current item
            knapsack(i + 1,
                    profit,
                    weight,
                    wt, val, n, capacity);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int[] wt = new int[n];
        int[] val = new int[n];

        System.out.println("Enter weights:");
        for (int i = 0; i < n; i++)
            wt[i] = sc.nextInt();

        System.out.println("Enter profits:");
        for (int i = 0; i < n; i++)
            val[i] = sc.nextInt();

        System.out.print("Enter capacity: ");
        int capacity = sc.nextInt();

        knapsack(0, 0, 0, wt, val, n, capacity);

        System.out.println("Maximum Profit = " + maxProfit);

        sc.close();
    }
}