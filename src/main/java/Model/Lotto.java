package Model;

import lotto.Utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

    private static final int LOTTO_NUMBER_SIZE = 6;
    protected static final int MIN_LOTTO_NUMBER = 1;
    protected static final int MAX_LOTTO_NUMBER = 45;
    private final List<Integer> numbers;

    public Lotto (String numbers) {
        this.numbers = validate(numbers);
    }

    public Lotto(List<Integer> numbers) {
        this.numbers = numbers;
    }

    List<Integer> validate(String numbersInput) {
        numbersInput = deleteWhiteSpace(numbersInput);
        numbersInput = correctCommas(numbersInput);
        validateWinningNumberNumeric(numbersInput);
        List<Integer> numbers = Utils.convertStringToIntegerList(numbersInput);
        validateWinningNumberLength(numbers);
        validateWinningNumberRange(numbers);
        validateWinningNumberDuplication(numbers);
        return numbers;
    }
    void validateWinningNumberLength(List<Integer> winningNumber) {
        if (winningNumber.size() != LOTTO_NUMBER_SIZE) {
            throw new IllegalArgumentException();
        }
    }

    String deleteWhiteSpace(String winningNumber) {
        return winningNumber.replace("\\s", "");
    }

    String correctCommas(String winningNumber) {
        winningNumber = winningNumber.replaceAll("^,|,$", "");
        winningNumber = winningNumber.replaceAll(",+", ",");
        return winningNumber;
    }

    void validateWinningNumberNumeric(String winningNumber) {
        try {
            for (int winningNumberIndex = 0; winningNumberIndex < winningNumber.length(); winningNumberIndex++) {
                if (!(Character.isDigit(winningNumber.charAt(winningNumberIndex)) || winningNumber.charAt(winningNumberIndex) == ',')) {
                    throw new IllegalArgumentException();
                }
            }
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException();
        }
    }
    void validateWinningNumberRange(List<Integer> winningNumber) {
        for (Integer lottoNumber : winningNumber) {
            if (MIN_LOTTO_NUMBER > lottoNumber || MAX_LOTTO_NUMBER < lottoNumber) {
                throw new IllegalArgumentException();
            }
        }
    }

    void validateWinningNumberDuplication(List<Integer> winningNumber) {
        Set<Integer> comparativeGroup = new HashSet<>(winningNumber);
        if (comparativeGroup.size() != winningNumber.size()) {
            throw new IllegalArgumentException();
        }
    }

    List<Integer> getNumbers() {
        return numbers;
    }
}
