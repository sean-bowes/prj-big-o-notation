package com.koisoftware.misc;

public class AliceDiceGame {

    //Alice wants to join her school's Probability Student Club. Membership dues are computed via one of two simple probabilistic games.
    //The first game: roll a die repeatedly. Stop rolling once you get a 5 followed by a 6. Your number of rolls is the amount you pay, in dollars.
    //The second game: same, except that the stopping condition is a 5 followed by a 5.
    //Which of the two games should Alice elect to play? Does it even matter?

    //Both games are identical until Alice rolls a 5. At that point, on the next roll, she has a 1/6 chance of rolling a 5,
    // and a 1/6 chance of rolling a 6. So her chances of winning at this point are identical between both games,
    // but what makes this interesting is what happens if she does not win immediately after rolling her first 5.

    //In the second game, suppose Alice rolls a 5 and then a non-5. She's now back to square one,
    // having to roll a 5 again before looking for her second 5. She will require a minimum of two more rolls to win.
    // In contrast, in the first game, it's possible for Alice to roll a 5 followed by a non-6, which could be a 5.
    // In the second game, failure to win doesn't necessarily revert the player to square one - there's a possibility
    // she could still be just one roll away from winning.

    //After rolling the first 5, a player of the first game has a 1/6 chance of winning, a 1/6 chance of trying again,
    // and a 4/6 chance of starting over. A player of the second game has a 1/6 chance of winning and a 5/6 chance of
    // having to start over. The first game will take fewer rolls to win, on average.

    private int generateRamdonNumber() {
        int min = 1;
        int max = 6;
        return (int) Math.floor(Math.random() * (max - min + 1) + min);
    }

    public double probabilityOfDiceBySequence(int a, int b) {
        int dice_thrown = 1;
        int current = generateRamdonNumber();
        while (true) {
            int next = generateRamdonNumber();
            dice_thrown += 1;
            if (current == a && next == b) {
                return dice_thrown;
            }
            current = next;
        }
    }

    public static void main(String args[]) {
        AliceDiceGame obj = new AliceDiceGame();
        int maxThrow = 30000;
        double nm56 = 0;
        for (int i = 0; i < maxThrow; i++) {
            nm56 += obj.probabilityOfDiceBySequence(5, 6);
        }
        System.out.println("5-6=" + nm56 / maxThrow);
        double nm55 = 0;
        for (int i = 0; i < maxThrow; i++) {
            nm55 += obj.probabilityOfDiceBySequence(5, 5);
        }
        System.out.println("5-5=" + nm55 / maxThrow);
    }
}
