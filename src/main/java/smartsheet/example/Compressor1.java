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
            if((letterArr[i] >= 'a' && letterArr[i] <= 'z') || letterArr[i] >='A' && letterArr[i] <= 'Z'){
                word += letterArr[i];
                i++;
                continue;
            }else{
                if(word.length() > 0){
                    processWord(word, index);
                    index ++;
                }
                word = "";
                this.outputString += letterArr[i];
            }
            i++;
        }

        if(word.length() > 0){
            processWord(word, index);
        }
        System.out.println(this.outputString);
    }
    
    private void processWord(String word, int index){
        if(this.identifierMap.containsKey(word)){
            this.outputString += "$" + identifierMap.get(word);
        } else {
            this.identifierMap.put(word, index);
            this.outputString += word;
        }
        System.out.println("word:[" + word + "]");
    }
}
