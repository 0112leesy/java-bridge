package bridge;

import static camp.nextstep.edu.missionutils.Console.readLine;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {

    /**
     * 다리의 길이를 입력받는다.
     */
    public int readBridgeSize() throws IllegalArgumentException {
        System.out.println("다리의 길이를 입력해주세요.");
        String input = readLine();
        if (!input.matches("^[0-9]*$")) {
            throw new IllegalArgumentException("[ERROR] <임시 에러 메시지>");
        }
        int bridgeSize = Integer.parseInt(input);
        return bridgeSize;
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public String readMoving() throws IllegalArgumentException {
        System.out.println("이동할 칸을 선택해주세요. (위: U, 아래: D)");
        String input = readLine();
        if (!input.equals("U") && !input.equals("D")) {
            throw new IllegalArgumentException("[ERROR] <임시 에러 메시지>");
        }
        return input;
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public String readGameCommand() throws IllegalArgumentException {
        System.out.println("게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)");
        String input = readLine();
        if (!input.equals("R") && !input.equals("Q")) {
            throw new IllegalArgumentException("[ERROR] <임시 에러 메시지>");
        }
        return input;
    }
}
