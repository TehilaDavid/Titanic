public class Passenger {

    private int passengerId; // מספר מזהה
    private boolean survived; // אם שרד
    private int pClass;
    private String name;
    private String sex;
    private double age;
    private int sibSp;
    private int parch;
    private String ticket;
    private double fare;
    private String cabin;
    private char embarked;


    public Passenger (String text) {
        String[] s = text.split(",");

        this.passengerId = Integer.parseInt(s[0]);
        this.survived = (Integer.parseInt(s[1]) == 1);
        this.pClass = Integer.parseInt(s[2]);
        this.name = s[3] + s[4];
        this.sex = s[5];

        if (s[6].equals("")) {
            this.age = -1;
        }else {
            this.age = Double.parseDouble(s[6]);
        }

        this.sibSp = Integer.parseInt(s[7]);
        this.parch = Integer.parseInt(s[8]);
        this.ticket = s[9];
        this.fare = Double.parseDouble(s[10]);
        this.cabin = s[11];
        if (s.length == 11) {
            this.embarked = s[12].charAt(0);
        }
    }


    public String toString() {
        return "Passenger{" +
                "passengerId=" + passengerId +
                ", survived=" + survived +
                ", pClass=" + pClass +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", sibSp=" + sibSp +
                ", parch=" + parch +
                ", ticket='" + ticket + '\'' +
                ", fare=" + fare +
                ", cabin='" + cabin + '\'' +
                ", embarked=" + embarked +
                '}';
    }
}
