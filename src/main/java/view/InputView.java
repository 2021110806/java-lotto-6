package view;

import camp.nextstep.edu.missionutils.Console;

import java.util.NoSuchElementException;

public class InputView {

    public static String getMoneyForLottoPurchasing() {
        String money;
        money = Console.readLine();
        return money;
    }

    public static String getWinningNumber() {
        String winningNumber;
        winningNumber = Console.readLine();
        return winningNumber;
    }

    public static String getBonusNumber() {
        String bonusNumber;
        bonusNumber = Console.readLine();
        return bonusNumber;
    }
}
