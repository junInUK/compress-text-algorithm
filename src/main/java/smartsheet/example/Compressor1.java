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
public class Compressor1 {

    private String inputString;
    private String outputString;
    private Map<String, Integer> identifierMap;
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
    private int minWordLength;

    public Compressor1(String inputString) {
        this.inputString = inputString;
        this.outputString = "";
        this.identifierMap = new HashMap<String, Integer>();
        this.minWordLength = 0;
    }

    public Compressor1(String inputString, int minWordLength) {
        this.inputString = inputString;
        this.outputString = "";
        this.identifierMap = new HashMap<String, Integer>();
        this.minWordLength = minWordLength;
    }

    public String getInputString() {
        return this.inputString;
    }

    public String getOutputString() {
        return this.outputString;
    }

    public void minimise() {
        StringBuffer word = new StringBuffer();
        StringBuffer tmpOutputString = new StringBuffer();
        int index = 0;

        char [] letterArr = new char[this.inputString.length()];
        letterArr = this.inputString.toCharArray();
        int i = 0;
        while(i < letterArr.length){
            if(Character.isLowerCase(letterArr[i]) || Character.isUpperCase(letterArr[i])){
                word.append(letterArr[i]);
                i++;
                continue;
            }else{
                if(word.length() > 0){
                    processWord(word.toString(), index, tmpOutputString);
                    index ++;
                }
                word.setLength(0);
                if(Character.isDigit(letterArr[i])){
                    //  If current character is number and last character isn't space
                    if(i - 1 >= 0 && letterArr[i-1] != 32) {
                        tmpOutputString.append("\\").append(letterArr[i]);
                    } else {
                        tmpOutputString.append(letterArr[i]);
                    }
                } else {
                    tmpOutputString.append(letterArr[i]);
                }
            }
            i++;
        }

        if(word.length() > 0){
            processWord(word.toString(), index, tmpOutputString);
        }
        this.outputString = tmpOutputString.toString();
    }

    public String revert(){
        String revertString = "";
        StringBuffer tmpRevertString = new StringBuffer();
        StringBuffer specialWord = new StringBuffer();
        char [] letterArr = new char[this.outputString.length()];
        letterArr = this.outputString.toCharArray();
        int i = 0;
        while( i < letterArr.length){
            if( letterArr[i] == '\\' ){
                i ++;
            }
            if( letterArr[i] == '$' ){
                for( int j = i + 1; j < letterArr.length; j++){
                    if( j < letterArr.length && Character.isDigit(letterArr[j])){
                        specialWord.append(letterArr[i+1]);
                        i ++;
                    } else {
                        break;
                    }
                }
            }
            if(specialWord.length() > 0){
                Integer value = null;
                value = Integer.valueOf(specialWord.toString());
                tmpRevertString.append(getWord(value));
            //    revertString += getWord(value);
            } else {
            //    revertString += letterArr[i];
                tmpRevertString.append(letterArr[i]);
            }
            specialWord.setLength(0);
            i ++;
        }
        revertString = tmpRevertString.toString();
        return revertString;
    }

    public String getWord(Integer value){
        String word = null;
        for(String key: this.identifierMap.keySet()){
            if(this.identifierMap.get(key).equals(value)){
                word = key;
            }
        }
        return word;
    }

    private void processWord(String word, int index, StringBuffer updateString){
        /**
         * If the word in the identifierMap and word length longer than the minWordLength,
         * use $+index to replace this word.
         * index is the first appearance of the word
         */
        if(this.identifierMap.containsKey(word) && word.length() > this.minWordLength){
            updateString.append("$").append(identifierMap.get(word));
        } else {
            this.identifierMap.put(word, index);
            updateString.append(word);
        }
    }
}
