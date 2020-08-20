package smartsheet.example;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CompressorTest {

    Compressor compressor, compressorMultiLine;

    @Before
    public void before(){
        compressor = new Compressor("you say yes, I say no you say stop and I say go go go");
        compressorMultiLine = new Compressor("/*\n" +
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
    }

    @Test
    public void canGetInput(){
        assertEquals("you say yes, I say no you say stop and I say go go go", compressor.getInputString());
    }

    @Test
    public void canCompressorString(){
        System.out.println(compressor.getInputString());
        compressor.minimise();
        assertEquals("you say yes, I $1 no $0 $1 stop and $3 $1 go $12 $12",compressor.getOutputString());
    }

    @Test
    public void canCompressorMultiline(){
        System.out.println(compressorMultiLine.getInputString());
        compressorMultiLine.minimise();
//        String outputString = "/*\n" +
//                "* Function to chop a string in half. \n" +
//                "*/ \n" +
//                "public static string chop(string input) { \n" +
//                "if (input == null || input.isEmpty()) { \n" +
//                "return input; \n" +
//                "}\n" +
//                "if (input.length() % 2 == 1) { \n" +
//                "return \"cannot chop an odd-length string in half\"; \n" +
//                "}\n" +
//                "return input.substring(input.length() / 2); \n" +
//                "}";
//        assertEquals("/*\n" +
//                "* Function to chop a string in half. \n" +
//                "*/ \n" +
//                "public static string chop(string input) { \n" +
//                "if (input == null || input.isEmpty()) { \n" +
//                "return input; \n" +
//                "}\n" +
//                "if (input.length() % 2 == 1) { \n" +
//                "return \"cannot chop an odd-length string in half\"; \n" +
//                "}\n" +
//                "return input.substring(input.length() / 2); \n" +
//                "}", compressorMultiLine.getOutputString());
        System.out.println(compressorMultiLine.getOutputString());
    }

}
