package mk.ukim.finki.aud1;

public class StringPrefixTest {

    public boolean isPrefix (String str1, String str2) {

        //return str2.startsWith(str1);

        if (str1.equals(str2))
            return true;

        if (str1.length()>str2.length()) {
            return false;
        }
        else {
            for (int i=0;i<str1.length();i++) {
                if (str1.charAt(i)!=str2.charAt(i)) //str1[i]
                    return false;
            }
            return true;
        }
    }
}
