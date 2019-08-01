package Unicode2Character;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        char c1 = '\u0031';
        char c2 = '\u0032';
        char c3 = '\u0030';
        char c4 = '\u0036';
        char c5 = '\u0031';
        char c6 = '\u0035';
        char nul = '\u0000';
        char[] chars = new char[8];
        chars[0] = c1;
        chars[1] = c2;
        chars[2] = c3;
        chars[3] = c4;
        chars[4] = c5;
        chars[5] = c6;
        chars[7] = nul;
        chars[7] = nul;

        String s = new String(chars);
        System.out.println("'" + s + "'" + s.length());
    }
}
