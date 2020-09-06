package smartsheet.example;

/**
 * Given an array of characters, compress it in-place.
 *
 * The length after compression must always be smaller than or equal to the original array.
 *
 * Every element of the array should be a character (not int) of length 1.
 *
 * After you are done modifying the input array in-place, return the new length of the array.
 *
 *
 * Follow up:
 * Could you solve it using only O(1) extra space?
 *
 *
 * Example 1:
 *
 * Input:
 * ["a","a","b","b","c","c","c"]
 *
 * Output:
 * Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
 *
 * Explanation:
 * "aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".
 *
 *
 * Example 2:
 *
 * Input:
 * ["a"]
 *
 * Output:
 * Return 1, and the first 1 characters of the input array should be: ["a"]
 *
 * Explanation:
 * Nothing is replaced.
 *
 *
 * Example 3:
 *
 * Input:
 * ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
 *
 * Output:
 * Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
 *
 * Explanation:
 * Since the character "a" does not repeat, it is not compressed. "bbbbbbbbbbbb" is replaced by "b12".
 * Notice each digit has it's own entry in the array.
 *
 *
 * Constraints:
 *
 * 1 <= chars.length <= 2000
 * chars[i].length == 1
 * chars[i] is a lower-case English letter, upper-case English letter, digit or a symbol.
 */
public class NewCompress {
    public static void main(String args[]){
        char []a = new char[]{'a','b','b','b','b','b','b','b','b','b','b','b','b'};
        char []b = new char[]{'a','a','b','b','c','c','c'};
        char []c = new char[]{'a'};
        int re = compress(c);
        System.out.println(re);
    }

    public static int compress(char[] chars){
        int size = 0;
        int count = 1;
        StringBuffer newString = new StringBuffer();
        if(chars.length <= 0){
            return 0;
        }else{
            int i=1;
            newString.append(chars[0]);
            while(i<chars.length){
                if(chars[i]==chars[i-1]){
                    count++;
                    i++;
                    continue;
                }else{
                    if(count>1){
                        newString.append(Integer.toString(count));
                    }
                    newString.append(chars[i]);
                }
                count = 1;
                i++;
            }
            if(count>1){
                newString.append(Integer.toString(count));
            }
        }
        System.out.println(newString.toString());
        System.out.println(newString.length());
        return newString.length();
    }
}
