import java.util.Arrays;

public class SplitStringTest {
    public static void main(String[] args){
        String address = "490 Helms St.";
        String subAddresses[] = address.split(" "); //delimiter is " ".

        System.out.println(Arrays.toString(subAddresses));
;    }
}
