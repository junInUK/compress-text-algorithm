package smartsheet.example;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CompressorTest {

    Compressor compressor,
            compressorMultiLine,
            compressorSimpleMultiLine,
            compressorWithMinWordLength,
            compressorMultiLineWithMinWordLength;

    @Before
    public void before(){
        compressor = new Compressor(" *&you say yes, I say no you say stop and I say go go go say4no");
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
        compressorSimpleMultiLine = new Compressor("Hello\n" +
                "java");
        compressorWithMinWordLength = new Compressor(
                " *&you say yes, I say no you say stop and I say go go go say4no",
                2
                );
        compressorMultiLineWithMinWordLength = new Compressor( "/*\n" +
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
                "}", 2);
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
    public void canCompressorStringWithMinWordLength(){
        System.out.println(compressorWithMinWordLength.getInputString());
        compressorWithMinWordLength.minimise();
        assertEquals(" *&you say yes, I $1 no $0 $1 stop and I $1 go go go $14no",
                compressorWithMinWordLength.getOutputString());
    }

    @Test
    public void canCompressorMultiline(){
        System.out.println(compressorMultiLine.getInputString());
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

    @Test
    public void canComparessMultilineWithMinWordLength(){
        System.out.println(compressorMultiLineWithMinWordLength.getInputString());
        compressorMultiLineWithMinWordLength.minimise();
        assertEquals("/*\n" +
                "* Function to chop a string in half. \n" +
                "*/ \n" +
                "public static $4 $2($4 input) { \n" +
                "if ($12 == null || $12.isEmpty()) { \n" +
                "return $12; \n" +
                "}\n" +
                "if ($12.length() % 2 == 1) { \n" +
                "$18 \"cannot $2 an odd-$22 $4 in $6\"; \n" +
                "}\n" +
                "$18 $12.substring($12.$22() / 2); \n" +
                "}", compressorMultiLineWithMinWordLength.getOutputString());
    }

    @Test
    public void canCompressorSimpleMultiline(){
        System.out.println(compressorSimpleMultiLine.getInputString());
        compressorSimpleMultiLine.minimise();
        assertEquals("Hello\n" +
                                "java", compressorSimpleMultiLine.getOutputString());
    }

}
