import java.util.Scanner;

public class Knapsack01 {

    static int knapsack(int[] weight, int[] profit, int n, int capacity) {

        int[][] dp = new int[n + 1][capacity + 1];

        // Build table dp[][] in bottom-up manner
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {

                if (i == 0 || w == 0)
                    dp[i][w] = 0;

                else if (weight[i - 1] <= w)
                    dp[i][w] = Math.max(
                            profit[i - 1] + dp[i - 1][w - weight[i - 1]],
                            dp[i - 1][w]);

                else
                    dp[i][w] = dp[i - 1][w];
            }
        }

        return dp[n][capacity];
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int[] weight = new int[n];
        int[] profit = new int[n];

        System.out.println("Enter weights:");
        for (int i = 0; i < n; i++)
            weight[i] = sc.nextInt();

        System.out.println("Enter profits:");
        for (int i = 0; i < n; i++)
            profit[i] = sc.nextInt();

        System.out.print("Enter capacity of knapsack: ");
        int capacity = sc.nextInt();

        int maxProfit = knapsack(weight, profit, n, capacity);

        System.out.println("Maximum Profit: " + maxProfit);

        sc.close();
    }
}