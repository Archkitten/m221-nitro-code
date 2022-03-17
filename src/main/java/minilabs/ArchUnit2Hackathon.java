package minilabs;

public class ArchUnit2Hackathon {
    public static void avgGrades() {
        int grade1 = 100;
        int grade2 = 70;
        int grade3 = 60;
        int grade4 = 90;
        int grade5 = 80;
        double avg12 = (double)(grade1 + grade2) / 2;
        double avg34 = (double)(grade3 + grade4) / 2;
        double finalAvg = (avg12 + avg34 + (double)grade5) / 3;
        System.out.println(finalAvg);
    }
}
