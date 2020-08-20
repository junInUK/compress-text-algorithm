package smartsheet.example;

import java.util.HashMap;
import java.util.Map;

/**
 * This class was used to minimiser/compressor text. The purpose is the make the text as small as possible but can
 * decode to the original text.
 * It takes an input string and replaces identifiers with shorter ones.
 * The second and subsequent times each identifier(word) appears, it is replaced by a dollar($) sign and a
 * number which is the index of the first appearance of that identifier(word), counting the first identifier(word)
 * as 0, the next as 1, etc.
 * Any letter which is not an identifier is output with the same.
 * The identifiers can be split with space or any other character which isn't letters [a-z] or [A-Z].
 * For example, "alice" is a single identifier while "jump4joy" is the identifier "jump", the non-identifier
 * "4" and a second identifier "joy".
 * For the identifiers which is less than 2 letters like I, we, he, it, go, this algorithm doesn't make sense because
 * encoded code $+Number will be longer or equal than the original identifier. Like $2 longer than I and $3 not shorter
 * than it.
 * The property minWordLength is used to set the minor length of identifier.
 * If this property didn't been set, the program will encode all the identifier.
 * If the input string is "you say yes, I say no you say stop and I say go go go",
 * The output will be "you say yes, I $1 no $0 $1 stop and $3 $1 go $12 $12"
 * If minWordLength = 2 then
 * The output will be "you say yes, I $1 no $0 $1 stop and I $1 go go go"
 *
 */
public class Compressor {
    private String inputString;
    private String outputString;
    /**
     * This property tells the program if the identifier shorter than given length will keep the same copy
     * without change.
     * For example,
     * If the input string is "you say yes, I say no you say stop and I say go go go"
     * If you don't wish the identifier I and go encoded,
     * set minWordLength = 2
     * so, the output will be "you say yes, I $1 no $0 $1 stop and I $1 go go go"
     *
     */
    private int minWordLength;  //  This property tell the program

    public Compressor(String inputString) {
        this.inputString = inputString;
        this.outputString = "";
        this.minWordLength = 0;
    }

    public Compressor(String inputString, int minWordLength) {
        this.inputString = inputString;
        this.outputString = "";
        this.minWordLength = minWordLength;
    }

    public String getInputString() {
        return inputString;
    }

    public String getOutputString() {
        return outputString;
    }

    public void minimise(){
        Map<String, Integer> identifierMap = new HashMap<String, Integer>();
        StringBuffer word = new StringBuffer();
        StringBuffer tmpOutputString = new StringBuffer();
        int index = 0;

        char [] letterArr = new char[this.inputString.length()];
        letterArr = this.inputString.toCharArray();
        //  let i < letterArr.length + 1 because if not, the last word cannot been process
        for(int i = 0; i < letterArr.length + 1; i++){
//            if(i < letterArr.length){
//                System.out.println(letterArr[i]);
//            }
            /**
             *  If letter i in the input string and is a letter a-z or A-Z
             *  append this letter to variable word until letter i is not a letter or meet the end of input string
              */
            if(i < letterArr.length && (letterArr[i] >= 'a' && letterArr[i] <= 'z' || letterArr[i] >= 'A' && letterArr[i] <= 'Z')) {
                word.append(letterArr[i]);
                continue;   //  jump out of this loop and start next letter
            }
            System.out.println("word[" + word + "] " + "index: " +index);
            /**
             * If the letter wasn't letter a-z or A-Z, the temporary word was empty because of last setting.
             */
            if(word.length() > 0 ){
                /**
                 * If the word in the identifierMap and word length longer than the minWordLength,
                 * use $+index to replace this word.
                 * index is the first appearance of the word
                 */
                if(identifierMap.containsKey(word.toString()) && word.length() > this.minWordLength){
                    tmpOutputString.append("$").append(identifierMap.get(word.toString()));
                } else {
                    identifierMap.put(word.toString(),index);
                    tmpOutputString.append(word);
                }
                index ++;
                word.setLength(0);
            }
            /**
             * If the letter wasn't letter a-z or A-Z, just append it to the StringBuffer tmpOutputString
             */
            if( i < letterArr.length){
                tmpOutputString.append(letterArr[i]);
            }
            System.out.println("output string:[" + tmpOutputString +"]");
            this.outputString = tmpOutputString.toString();
        }
    }

}
