// Java program for implementation of Selection Sort
import java.util.Scanner;
import java.util.Random;
import java.lang.*;
// import org.apache.commons.lang3.time.StopWatch;

class StopWatch {

private long startTime = 0;
private long stopTime = 0;
private boolean running = false;


public void start() {
    this.startTime = System.currentTimeMillis();
    this.running = true;
}


public void stop() {
    this.stopTime = System.currentTimeMillis();
    this.running = false;
}


//elaspsed time in milliseconds
public long getElapsedTime() {
    long elapsed;
    if (running) {
         elapsed = (System.currentTimeMillis() - startTime);
    }
    else {
        elapsed = (stopTime - startTime);
    }
    return elapsed;
}

}

class int2
{
    int x, y;

    int2(int a, int b)
    {
        this.x = a;
        this.y = b;
    }
}

class MergeSort
{
    void bubblesort(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
        {
            for(int j = 0; j < n-1-i; j++)
            {
                if (arr[j] > arr[j+1])
                {
                    exch(arr, j, j+1);
                }
            }
        }
    }

	void selectionsort(int arr[])
	{
		int n = arr.length;

		// One by one move boundary of unsorted subarray
		for (int i = 0; i < n-1; i++)
		{
			// Find the minimum element in unsorted array
			int min_idx = i;
			for (int j = i+1; j < n; j++)
				if (arr[j] < arr[min_idx])
					min_idx = j;

			// Swap the found minimum element with the first
			// element
			int temp = arr[min_idx];
			arr[min_idx] = arr[i];
			arr[i] = temp;
		}
	}

    void insertionSort(int arr[])
    {
        int n = arr.length;
        for (int i = 1; i < n; i++)
        {
            for (int j = i; j > 0; j--)
            {
                if (arr[j] < arr[j-1])
                {
                    int temp = arr[j-1];
        			arr[j-1] = arr[j];
        			arr[j] = temp;
                }
                else
                {
                    break;
                }
            }
        }
    }

    void shellSort(int arr[])
    {
        int n = arr.length;
        // int pass[] = {, 1};
        int k = 1;
        while (k < n/3) k = 3*k + 1;
        while (k >= 1)
        {
            int t = k;
            for (int i = t; i < n; i++)
            {
                for (int j = i; j >= t; j-=t)
                {
                    if (arr[j] < arr[j-t])
                    {
                        int temp = arr[j-t];
            			arr[j-t] = arr[j];
            			arr[j] = temp;
                    }
                    else
                    {
                        break;
                    }
                }
            }
            k = k/3;
        }
    }

    private static void merge(int arr[], int aux[], int hi, int lo, int mid)
    {
        assert isSorted(arr, lo, mid);
        assert isSorted(arr, mid+1, hi);
        for (int k = lo; k <= hi; k++)
        {
            aux[k] = arr[k];
        }

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++)
        {
            if (i > mid) arr[k] = aux[j++];
            else if (j > hi) arr[k] = aux[i++];
            else if (aux[i] > aux[j]) arr[k] = aux[j++];
            else arr[k] = aux[i++];
        }
        assert isSorted(arr, lo, hi);
    }

    public void mergesort(int arr[], int aux[], int lo, int hi)
    {
        // int N = arr.length;
        if (hi <= lo) return; // return after breaking it up into single elements
        int mid = lo + (hi - lo)/2;
        mergesort(arr, aux, lo, mid);
        mergesort(arr, aux, mid+1, hi);
        merge(arr, aux, hi, lo, mid);
    }

    public static int balancePos(int arr[])
    {
        int N = arr.length;
        long left;
        long right;
        for (int i = 1; i < N; i++)
        {
            left = right = 0;
            for (int j = 0; j < N; j++)
            {
                if (j < i)
                {
                    left += arr[j];
                }
                else if (j > i)
                {
                    right += arr[j];
                }
            }
            if (left == right)
            {
                return i;
            }
        }
        return -1;
    }

    public static int betterBalancePos(int arr[])
    {
        int size = arr.length;
          int right_sum = 0,
              left_sum = 0;

          // Maintains left
          // cumulative sum
          left_sum = 0;

          // Maintains right
          // cumulative sum
          right_sum = 0;
          int i = -1, j = -1;

          for(i = 0, j = size - 1;
              i < j; i++, j--)
          {
            left_sum += arr[i];
            right_sum += arr[j];

            // Keep moving i towards
            // center until left_sum
            //is found lesser than right_sum
            while(left_sum < right_sum &&
                  i < j)
            {
              i++;
              left_sum += arr[i];
            }

            // Keep moving j towards
            // center until right_sum is
            // found lesser than left_sum
            while(right_sum < left_sum &&
                  i < j)
            {
              j--;
              right_sum += arr[j];
            }
          }

          if(left_sum == right_sum && i == j)
            return i;
          else
            return -1;
        }

    public static int betterBalancePoss(int arr[])
    {
        int N = arr.length;
        long left;
        long right;
        left = right = 0;
        int part  = N/2;
        for (int j = 0; j < N/2 - 1; j++)
        {
            left += arr[j];
            right += arr[j + N/2 + 1];
        }
        left += arr[N/2 - 1];
        // right += arr[N - 1];

        for (int i = N/2; i > 0; i--)
        {
            // System.out.println(left + ", " + right);
            if (left > right)
            {
                left -= arr[part-1];
                right += arr[part];
                part -= 1;
            }
            else if (left < right)
            {
                left += arr[part];
                right -= arr[part+1];
                part += 1;
            }
            else //(left == right)
            {
                return part;
            }
        }
        return -1;
    }

    /*public static int betterBalancePos(int arr[])
    {
        int sumTotal=0;
        for (int i=0; i < arr.length; i++){ // O(arr.length)
            sumTotal += arr[i];
        }

        int sumRight = 0;
        int sumLeft=0;
        int i;
        for (i=1; i < arr.length-1; i++){ // O(arr.length)
            sumLeft += arr[i-1];
            sumRight = sumTotal - arr[i] - sumLeft;
            if (sumRight == sumLeft){
                System.out.println("\nFound = "+arr[i]);
                break;
            }
        }
        return i;
    }*/

    public void mergesortBottomup(int arr[], int aux[])
    {
        for (int i = 1; i < arr.length; i=i+i)
        {
            for (int j = 0; j < arr.length - i; j+=i+i)
            {
                // int mid = lo + (hi - lo)/2;
                merge(arr, aux, Math.min(j+i+i-1, arr.length-1), j, j+i-1);
            }
        }
    }

    private static void exch(int a[], int b, int c)
    {
        int temp = a[b];
        a[b] = a[c];
        a[c] = temp;
    }

    private static boolean less(int a, int b)
    {
        return (a < b);
    }

    private static int partition(int arr[], int lo, int hi)
    {
        int i = lo, j = hi+1;
        while (true)
        {
            while (less(arr[++i], arr[lo]))
            if (i == hi) break;
            while (less(arr[lo], arr[--j]))
            if (j == lo) break;

            if (i >= j) break;
            exch(arr, i, j);
        }
        exch(arr, lo, j);
        return j;
    }

    private static int2 partition3way(int arr[], int lo, int hi)
    {
        int i = lo, j = hi, k = i;
        int v = arr[lo];
        while (k <= j)
        {
            if (arr[k] < v)
            {
                exch(arr, k++, i++);
            }
            else if (arr[k] > v)
            {
                exch(arr, k, j--);
            }
            else
            {
                k++;
            }
        }
        int2 w = new int2(i, j);
        return w;
    }

    public static void quicksort(int arr[])
    {
        // StdRandom.shuffle(arr);
        quicksort(arr, 0, arr.length - 1);
    }

    private static void quicksort(int arr[], int lo, int hi)
    {
        if (hi <= lo) return;
        int j = partition(arr, lo, hi);
        quicksort(arr, lo, j-1);
        quicksort(arr, j+1, hi);
    }

    public static void quicksort3way(int arr[])
    {
        // StdRandom.shuffle(arr);
        quicksort3way(arr, 0, arr.length - 1);
    }

    private static void quicksort3way(int arr[], int lo, int hi)
    {
        if (hi <= lo) return;
        int2 j = partition3way(arr, lo, hi);
        quicksort3way(arr, lo, j.x - 1);
        quicksort3way(arr, j.y + 1, hi);
    }

    private static int quickselect(int arr[], int k)
    {
        int lo = 0, hi = arr.length - 1;
        while (hi > lo)
        {
            int j = partition(arr, lo, hi);
            if      (j < k) lo = j + 1;
            else if (j > k) hi = j - 1;
            else
            return arr[k];
        }
        return arr[k];
    }

    static boolean isSorted(int arr[], int start, int end)
    {
        for (int i = start; i < end; i++)
        {
            if (arr[i] > arr[i+1])
            {
                return false;
            }
        }
        return true;
    }

	// Prints the array
	void printArray(int arr[])
	{
		int n = arr.length;
		for (int i=0; i<n; ++i)
			System.out.print(arr[i]+" \n ");
		System.out.println();
	}

	// Driver code to test above
	public static void main(String args[])
	{
        int firstArg = 0;
        if (args.length > 0) {
            try {
                firstArg = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Argument " + args[0] + " must be an integer.");
                System.exit(1);
            }
        }
        int secondArg = 0;
        if (args.length > 1 && firstArg == 0) {
            try {
                secondArg = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.err.println("Argument " + args[1] + " must be an integer.");
                System.exit(1);
            }
        }
        boolean isSelect = false;
        int index;
		MergeSort ob = new MergeSort();
        // generate 100 random number between 0 to 100
        // int[]  arr = IntStream.generate(() -> new Random().nextInt(100)).limit(100).toArray();
		// int arr[] = {64,25,12,22,11};
        StopWatch s = new StopWatch();
        Random rd = new Random(); // creating Random object
        long time = 1651862387755L;//System.currentTimeMillis();//1651601506760L; //
        rd.setSeed(time);
        int size = 8*100000;
        int[] arr = new int[size];
        int[] aux = new int[size];
        for (int i = 0; i < arr.length; i++) {
            int var = rd.nextInt() % 10;
            if (var < 0)
            {
                var += 10;
            }
            arr[i] = (var % 10); // storing random integers in an array
        }
        // ob.printArray(arr);
        s.start();
        //code you want to time goes here
        // Instant starts = Instant.now();
        // Thread.sleep(10);
        switch(firstArg) {
            // code block
          case 1:
            ob.selectionsort(arr);
            break;
            // code block
          case 2:
            ob.insertionSort(arr);
            break;
              // code block
          case 3:
              ob.shellSort(arr);
            break;
          case 4:
              // code block
              ob.mergesort(arr, aux, 0, size - 1);
            break;
          case 5:
              ob.mergesortBottomup(arr, aux);
            break;
          case 6:
              quicksort(arr);
              break;
          case 7:
              ob.bubblesort(arr);
              break;
          case 8:
              quicksort3way(arr);
              break;
          case 9:
              index = balancePos(arr);
              isSelect = true;
              System.out.println("Balance at " + index + " Position. Seed = " + time);
              break;
          case 10:
              index = betterBalancePos(arr);
              isSelect = true;
              System.out.println("Balance at " + index + " Position. Seed = " + time);
              break;
          case 0:
        default:
              int val = quickselect(arr, secondArg);
              isSelect = true;
              System.out.println("Val at " + secondArg + " Position = " + val);
          // code block
        }
        // Instant ends = Instant.now();
        // System.out.println(Duration.between(starts, ends));
        s.stop();
        // ob.printArray(arr);
        if (isSorted(arr, 0, size - 1) == false && !isSelect)
        {
            System.out.println("Sort unsuccessful");
            System.exit(-1);
        }
		// System.out.println("Sorted array");
        System.out.println("elapsed time in milliseconds: " + s.getElapsedTime());
	}
}
