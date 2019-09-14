package binconversion;

public class BinConversion {
//    public static void main(String[] args) {
//        int n = 18;
//        Integer.toHexString(n);
//        System.out.println(n + "的二进制是:" + Integer.toBinaryString(n));
//        System.out.println(n + "的八进制是:" + Integer.toOctalString(n));
//        System.out.println(n + "的十六进制是:" + Integer.toHexString(n));
//        System.out.println(n + "的三进制是:" + Integer.toString(n, 3));
//    }

    public String twoToten(String two) {
        int ten = Integer.parseInt(two, 2);
        return String.valueOf(ten);
    }

    public String eightToten(String eight){
        int ten = Integer.parseInt(eight, 8);
        return String.valueOf(ten);
    }

    public String sixteenToten(String eight){
        int ten = Integer.parseInt(eight, 16);
        return String.valueOf(ten);
    }

    public String tenTotwo(String ten){
        int Ten = Integer.parseInt(ten);
        String two = Integer.toBinaryString(Ten);
        return two;
    }

    public String tenToeight(String ten){
        int Ten = Integer.parseInt(ten);
        String eight = Integer.toOctalString(Ten);
        return eight;
    }

    public String tenTosixteen(String ten){
        int Ten = Integer.parseInt(ten);
        String sixteen = Integer.toHexString(Ten);
        return sixteen;
    }

    public String twoToeight(String two){
        return tenToeight(twoToten(two));
    }

    public String eightTotwo(String eight){
        return tenTotwo(eightToten(eight));
    }

    public String twoTosixteen(String two){
        return tenTosixteen(twoToten(two));
    }

    public String sixteenTotwo(String sixteen){
        return tenTotwo(sixteenToten(sixteen));
    }

    public String eightTosixteen(String eight){
        return tenTosixteen(eightToten(eight));
    }

    public String sixteenToeight(String sixteen){
        return tenToeight(sixteenToten(sixteen));
    }
}
