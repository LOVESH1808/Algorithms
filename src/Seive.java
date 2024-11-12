import java.util.Arrays;

public class Seive {
    static boolean[] generate_Prime(int n) {
        boolean arr[] = new boolean[n + 1];
        Arrays.fill(arr, true);
        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(arr[i]) {
//                System.out.print(i + " ");
                for(int j = i * i; j <= n; j += i) {
                    arr[j] = false;
                }
            }
        }
        return arr;
    }
    public static void main(String[] args) {
        boolean[] arr = generate_Prime(10);
        arr[0] = false;
        arr[1] = false;
        for(int i = 0; i < arr.length; i++) {
            System.out.print(i + "\t");
        }
        System.out.println();
        for(int i = 0; i < arr.length; i++) {

            System.out.print(arr[i] + "\t");
        }
    }
}
