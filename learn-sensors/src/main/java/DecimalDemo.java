import java.math.BigDecimal;

/**
 * @author huangjinhai
 * @date 2022\1\30
 */
public class DecimalDemo {
    public static void main(String[] args) {

        BigDecimal bigDecimal1 = new BigDecimal(0.1F);
        BigDecimal bigDecimal2 = new BigDecimal("0.1");
        System.out.println(bigDecimal1.toString());
        System.out.println(bigDecimal2.toString());

    }
}
