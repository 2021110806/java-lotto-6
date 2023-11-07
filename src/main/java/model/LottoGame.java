package model;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoGame {

    private static final int LOTTO_PRICE = 1000;
    private static final int FIRST_PRIZE = 7;
    private static final int FIFTH_PRIZE = 3;
    private static final List<Integer> PRIZE_MONEY = List.of(0, 0,5000, 50000, 1500000, 30000000, 2000000000);
     List<Lotto> lottos = new ArrayList<>();

    List<Integer> makeLottoNumbers() {
        List<Integer> LottoNumbers = new ArrayList<>();
        int currentLottoSize = 0;
        while (currentLottoSize < 6) {
            int LottoNumber = Randoms.pickNumberInRange(1, 45);
            if (!LottoNumbers.contains(LottoNumber)) {
                LottoNumbers.add(LottoNumber);
            }
            currentLottoSize++;
        }
        return LottoNumbers;
    }

    void purchaseLotto(int money) {
        while (money > 0) {
            lottos.add(new Lotto(makeLottoNumbers()));
            money -= LOTTO_PRICE;
        }
    }

    public Map<Integer, Integer> countWinningLottoResult(WinningLotto winningLotto) {
        Map<Integer, Integer> winningLottoResult = Stream.of(7,6,5,4,3)
                .collect(Collectors.toMap(
                        key -> key,
                        value -> 0
                ));
        for (Lotto lotto : lottos) {
            int result = winningLotto.countContains(lotto);
            if (winningLottoResult.containsKey(result)) {
                winningLottoResult.put(result, winningLottoResult.get(result) + 1);
            }
        }
        return winningLottoResult;
    }

    int calculateEarnings(Map<Integer, Integer> winningLottoResult) {
        int earnings = 0;
        for (int prize = FIFTH_PRIZE; prize <= FIRST_PRIZE; prize++) {
            earnings += winningLottoResult.get(prize) * PRIZE_MONEY.get(prize);
        }
        return earnings;
    }

    double calculateEarningRate(int earnings, int cost) {
        return earnings / cost;
    }
}