package smartsheet.example;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Compressor1Test {
    Compressor1 compressorEasy,
            compressor,
            compressorMultiLine,
            compressorSimpleMultiLine;

    @Before
    public void before(){
        compressorEasy = new Compressor1("you say yes, I say no you say stop and I say go go go");
        compressor = new Compressor1(" *&you say yes, I say no you say stop and I say go go go say4no");
        compressorMultiLine = new Compressor1("/*\n" +
                "* Function to chop a string in half. \n" +
                "*/ \n" +
                "public static string chop(string input) { \n" +
                "if (input == null || input.isEmpty()) { \n" +
                "return input; \n" +
                "}\n" +
                "if (input.length() % 2 == 1) { \n" +
                "return \"cannot chop an odd-length string in half\"; \n" +
                "}\n" +
                "return input.substring(input.length() / 2); \n" +
                "}");
        compressorSimpleMultiLine = new Compressor1("Hello\n" +
                "java");
    }

    @Test
    public void canGetInput(){
        assertEquals(" *&you say yes, I say no you say stop and I say go go go say4no", compressor.getInputString());
    }

    @Test
    public void canCompressorString(){
        System.out.println(compressor.getInputString());
        compressor.minimise();
        assertEquals(" *&you say yes, I $1 no $0 $1 stop and $3 $1 go $12 $12 $14$5",compressor.getOutputString());
    }

    @Test
    public void canRevertString(){
        System.out.println("Original String:    [" + compressor.getInputString() + "]");
        compressor.minimise();
        System.out.println("After minimise String:[" + compressor.getOutputString() + "]");
        System.out.println("After revert String:[" + compressor.revert() + "]");
    }

    @Test
    public void canRevertStringEasy(){
        System.out.println("Original String:[" + compressorEasy.getInputString() + "]");
        compressorEasy.minimise();
        System.out.println("After minimise String:[" + compressorEasy.getOutputString() + "]");
        System.out.println("After revert String:[" + compressorEasy.revert() + "]");
    }

    @Test
    public void canGetWordByIndex(){
        System.out.println(compressor.getInputString());
        compressor.minimise();
        assertEquals("say", compressor.getWord(1));
    }

    @Test
    public void canCompressorMultiline(){
//        System.out.println(compressorMultiLine.getInputString());
        compressorMultiLine.minimise();

        assertEquals("/*\n" +
                "* Function to chop a string in half. \n" +
                "*/ \n" +
                "public static $4 $2($4 input) { \n" +
                "if ($12 == null || $12.isEmpty()) { \n" +
                "return $12; \n" +
                "}\n" +
                "$13 ($12.length() % 2 == 1) { \n" +
                "$18 \"cannot $2 an odd-$22 $4 $5 $6\"; \n" +
                "}\n" +
                "$18 $12.substring($12.$22() / 2); \n" +
                "}", compressorMultiLine.getOutputString());

    }


}
