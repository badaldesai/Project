import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Strings -- extract printable strings from binary file
 * 
 * @author Ian F. Darwin, http://www.darwinsys.com/
 * @version $Id: Strings.java,v 1.3 2004/02/08 23:57:29 ian Exp $
 */


public class Strings {

  protected int minLength = 4;
  static int nt=0;
  static int n=0;
  String word1=null;
  /**
   * Return true if the character is printable IN ASCII. Not using
   * Character.isLetterOrDigit(); applies to all unicode ranges
   */
  protected boolean isStringChar(char ch) {
    if (ch >= 'a' && ch <= 'z')
      return true;
    if (ch >= 'A' && ch <= 'Z')
      return true;
    if (ch >= '0' && ch <= '9')
      return true;
    /*switch (ch) {
    case '/':
    case '-':
    case ':':
    case '.':
    case ',':
    case '_':
    case '$':
    case '%':
    case '\'':
    case '(':
    case ')':
    case '[':
    case ']':
    case '<':
    case '>':
      return true;
    }*/
    return false;
  }

  /** Process one file */
  protected void process(String fileName, InputStream inStream) {
    try {
      int i;
      char ch;

      // This line alone cuts the runtime by about 66% on large files.
      BufferedInputStream is = new BufferedInputStream(inStream);

      StringBuffer sb = new StringBuffer();

      // Read a byte, cast it to char, check if part of printable string.
      while ((i = is.read()) != -1) {
        ch = (char) i;
        if (isStringChar(ch) || (sb.length() > 0 && ch == ' '))
          // If so, build up string.
          sb.append(ch);
        else {
          // if not, see if anything to output.
          if (sb.length() == 0)
            continue;
          if (sb.length() >= minLength) {
            report(fileName, sb);
          }
          sb.setLength(0);
        }
      }
      is.close();
    } catch (IOException e) {
      System.out.println("IOException: " + e);
    }
  }

  /**
   * This simple main program looks after filenames and opening files and such
   * like for you.
   */
  public static void main(String[] av) {
    Strings o = new Strings();
  
        try {
          o.process("/Users/Mac/Desktop/manager.ser", new FileInputStream("/Users/Mac/Desktop/manager.ser"));
          System.out.println(nt+"\t"+n);
        } catch (FileNotFoundException e) {
          System.err.println(e);
        }
    }

  /** Output a match. Made a separate method for use by subclassers. */
  @SuppressWarnings("null")
protected void report(String fName, StringBuffer theString) {
	  //
	//System.out.println(fName + ": " + theString);
	  
	  
	  if(!theString.toString().contains("Technologies")) {
	  word1 = theString.toString().toString();
	  if(word1.toString().contains("Nexsan")) {
		  n++;
		  System.out.println(word1);
		 // System.exit(1);
	  	}
	  }
	  else {
	  if(theString.toString().contains("Technologies")&&word1.contains("Nexsan")) {
		  System.out.println("\n"+word1+" "+theString+"\n");
		 // System.exit(1);
		  nt++;
		  n--;
		  }
	  }
  /*  Pattern p = Pattern.compile("contactst");
    Matcher m = p.matcher(theString);
    if(m.find()){
    	System.out.println("\n"+fName + ": " + theString+"\n");
    	//System.exit(1);
    }***/
  }
}