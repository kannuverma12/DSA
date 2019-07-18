package com.kv.failed;


/**
 * @author karanverma
 *
 * Given a positive number n and precision p, find the square root of number upto p decimal places
 * using binary search.
 *
 * 1) As the square root of number lies in range 0 <= squareRoot <= number, therefore, initialize start and end as : start = 0, end = number.
 * 2) Compare the square of mid integer with the given number. If it is equal to the number, then we found our integral part, else look for the same in left or right side depending upon the scenario.
 * 3) Once we are done with finding the integral part, start computing the fractional part.
 * 4) Initialize the increment variable by 0.1 and iteratively compute the fractional part upto p places. For each iteration, increment changes to 1/10th of it’s previous value.
 * 5) Finally return the answer computed.
 */
public class SqrtImplementation {

    // Driver code
    public static void main(String[] args)
    {
        // Function calling
        System.out.println(squareRoot(50, 3));

        // Function calling
        System.out.println(squareRoot(10, 4));
    }

    // Function to find square root of given number upto given precision
    static float squareRoot(int number, int precision)
    {
        int start = 0, end = number;
        int mid;

        // variable to store the answer
        double ans = 0.0;

        // for computing integral part  of square root of number
        while (start <= end) {
            mid = (start + end) / 2;
            if (mid * mid == number) {
                ans = mid;
                break;
            }

            // incrementing start if integral  part lies on right side of the mid
            if (mid * mid < number) {
                start = mid + 1;
                ans = mid;
            }

            // decrementing end if integral part  lies on the left side of the mid
            else {
                end = mid - 1;
            }
        }

        // For computing the fractional part
        // of square root upto given precision
        double increment = 0.1;
        for (int i = 0; i < precision; i++) {
            while (ans * ans <= number) {
                ans += increment;
            }

            // loop terminates when ans * ans > number
            ans = ans - increment;
            increment = increment / 10;
        }
        return (float)ans;
    }

    /*
    Time Complexity : The time required to compute the integral part is O(log(number)) and
    constant i.e, = precision for computing the fractional part. Therefore, overall time complexity
    is O(log(number) + precision) which is approximately equal to O(log(number)).
     */



}
