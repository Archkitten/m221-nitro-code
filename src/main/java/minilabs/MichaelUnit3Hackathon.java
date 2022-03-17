package minilabs;

class MichaelUnit3Hackathon {
    private double gameCost;
    private String gameName;
    private String gameID;
    public MichaelUnit3Hackathon(double mn, String name, String ID) {
        gameCost = mn;
        gameName = name;
        gameID = ID;
    }
    public boolean makeItMorePayToWin() {
        if (gameCost < 10000000) {
            return true;
        } else {
            return false;
        }
    }
    public static void main(String args[]) {
        double gameCost = 10000.0;
        MichaelUnit3Hackathon fifa2020 = new MichaelUnit3Hackathon(gameCost, "FIFA2020", "AV1392");
        System.out.println(fifa2020.makeItMorePayToWin());
        if(fifa2020.makeItMorePayToWin()) {
            gameCost += 1000000.0;
        }
    }
}