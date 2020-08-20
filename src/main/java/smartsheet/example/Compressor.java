package smartsheet.example;

import java.util.HashMap;
import java.util.Map;

public class Compressor {
    private String inputString;
    private String outputString;

    public Compressor(String inputString) {
        this.inputString = inputString;
        this.outputString = "";
    }

    public String getInputString() {
        return inputString;
    }

    public String getOutputString() {
        return outputString;
    }

    public void minimise(){
        Map<String, Integer> identifierMap = new HashMap<String, Integer>();
        String word = "";
        String outputString = "";
        int index = 0;

        char [] letterArr = new char[this.inputString.length()];
        letterArr = this.inputString.toCharArray();
        for(int i = 0; i < letterArr.length + 1; i++){
            //  If letter i in the input string and is a letter a-z or A-Z
            if(i < letterArr.length && (letterArr[i] >= 'a' && letterArr[i] <= 'z' || letterArr[i] >= 'A' && letterArr[i] <= 'Z')) {
                word += letterArr[i];
                continue;
            }
            System.out.println(word + " ");
            System.out.println(index);
            String tmpWord = word;
            if(word.length() > 0 ){
                if(identifierMap.containsKey(word)){
                    this.outputString += "$" + identifierMap.get(word);
                } else {
                    identifierMap.put(word,index);
                    this.outputString += word;
                }
                index ++;
                word = "";
            } else {
                this.outputString += word;
            }
            if( i < letterArr.length){
                this.outputString += letterArr[i];
            }
            System.out.println("output string:" + this.outputString);
        }
    }

}
