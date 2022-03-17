package minilabs;

public class ArchUnit4Hackathon {
    int amountOfMoney = 5;
    int costOfHouse = 100_000_000;

    public void calculate() {
        if (amountOfMoney >= costOfHouse) {
            System.out.println("You can afford a house!");
        }
        else if (!(amountOfMoney >= costOfHouse)) {
            System.out.println("You can't afford a house!");
        }
        else if (amountOfMoney < costOfHouse) {
            System.out.println("You can't afford a house!");
        }
    }
}
