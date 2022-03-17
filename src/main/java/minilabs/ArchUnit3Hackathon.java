package minilabs;

public class ArchUnit3Hackathon {
        private int distanceFromLocal;
        private boolean isCompetitive;
        private String name;

        public void detectCompetition() {
            if (isCompetitive) {
                System.out.println("The " + name + " is competitive!");
            }
            else {
                System.out.println("Your nearest " + name + " is " + distanceFromLocal + " miles away.");
            }
        }
}
