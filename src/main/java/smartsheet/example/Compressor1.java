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
//        System.out.println(this.outputString);
    }

    public String revert(){
        String revertString = "";
        String specialWord = "";
        char [] letterArr = new char[this.outputString.length()];
        letterArr = this.outputString.toCharArray();
        int i = 0;
        System.out.println("revert string:");
        while( i < letterArr.length){
            if( letterArr[i] == '$' ){
                for( int j = i + 1; j < letterArr.length; j++){
                    if( j < letterArr.length && (letterArr[j] - 48 >= 0 && letterArr[j] -48 <=9)){
                        specialWord += letterArr[i+1];
                        i ++;
                    } else {
                        break;
                    }
                }
//                if( i+1 < letterArr.length && (letterArr[i+1] - 48 >= 0 && letterArr[i+1] -48 <=9)){
//                    specialWord += letterArr[i+1];
//                    i ++;
//                    continue;
//                }
            }
            if(specialWord.length() > 0){
                Integer value = null;
                value = Integer.valueOf(specialWord);
                revertString += getWord(value);
            } else {
                System.out.print(letterArr[i]);
                revertString += letterArr[i];
            }
            specialWord = "";
            i ++;
        }
        System.out.println("\nEnd");
        return revertString;
    }

    public String getWord(Integer value){
    //    List<Object> keyList = new ArrayList<>();
        String word = null;
        for(String key: this.identifierMap.keySet()){
    //        System.out.println("key:[" + key + "]");
    //        System.out.println("value:["+ this.identifierMap.get(key) + "]");
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
//        System.out.println("word:[" + word + "]");
    }
}
