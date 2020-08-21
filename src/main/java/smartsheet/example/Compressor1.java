package smartsheet.example;

import java.util.HashMap;
import java.util.Map;

public class Compressor1 {

    private String inputString;
    private String outputString;
    private Map<String, Integer> identifierMap;

    public Compressor1(String inputString) {
        this.inputString = inputString;
        this.outputString = "";
        this.identifierMap = new HashMap<String, Integer>();
    }

    public String getInputString() {
        return this.inputString;
    }

    public String getOutputString() {
        return this.outputString;
    }

    public void minimise() {
        String word = "";
        int index = 0;

        char [] letterArr = new char[this.inputString.length()];
        letterArr = this.inputString.toCharArray();
        int i = 0;
        while(i < letterArr.length){
            if(Character.isLowerCase(letterArr[i]) || Character.isUpperCase(letterArr[i])){
                word += letterArr[i];
                i++;
                continue;
            }else{
                if(word.length() > 0){
                    processWord(word, index);
                    index ++;
                }
                word = "";
                if(Character.isDigit(letterArr[i])){
                    //  If current character is number and last character isn't space
                    if(i - 1 >= 0 && letterArr[i-1] != 32) {
                        this.outputString += "\\" + letterArr[i];
                    } else {
                        this.outputString += letterArr[i];
                    }
                } else {
                    this.outputString += letterArr[i];
                }
            }
            i++;
        }

        if(word.length() > 0){
            processWord(word, index);
        }
    }

    public String revert(){
        String revertString = "";
        String specialWord = "";
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
                        specialWord += letterArr[i+1];
                        i ++;
                    } else {
                        break;
                    }
                }
            }
            if(specialWord.length() > 0){
                Integer value = null;
                value = Integer.valueOf(specialWord);
                revertString += getWord(value);
            } else {
                revertString += letterArr[i];
            }
            specialWord = "";
            i ++;
        }
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

    private void processWord(String word, int index){
        if(this.identifierMap.containsKey(word)){
            this.outputString += "$" + identifierMap.get(word);
        } else {
            this.identifierMap.put(word, index);
            this.outputString += word;
        }
    }
}
