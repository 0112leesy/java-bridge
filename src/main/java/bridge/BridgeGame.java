package bridge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private static List<String> bridge;
    private static List<Integer> record;
    private static int trial;
    private static boolean success;

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public List<Integer> move(String choice) {
        int currentPosition = record.size();
        int stateCode = getStateCode(currentPosition, choice);
        record.add(stateCode);
        return record;
    }

    private int getStateCode(int position, String choice) throws IllegalArgumentException {
        String state = bridge.get(position) + choice;
        for (StateCode stateCode : StateCode.values()) {
            if (state.equals(stateCode.getState())) {
                return stateCode.getCode();
            }
        }
        throw new IllegalArgumentException("[ERROR] 유효하지 않은 상태 - U 또는 D만 입력되어야함");
    }

    public String getMapToString() {
        String map = mapToString(new StringBuilder("["), buildMap(record));
        return map;
    }

    private String mapToString(StringBuilder stringBuilder, char[][] map) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < map.length; j++) {
                stringBuilder.append(String.format(" %c |", map[j][i]));
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1).append("]\n[");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }

    private char[][] buildMap(List<Integer> record) {
        char[][] Map = initializeMap(record.size());
        for (int i = 0; i < record.size(); i++) {
            int currentRecord = record.get(i);
            Map[i][getIndex(currentRecord)] = getResult(currentRecord);
        }
        return Map;
    }

    private char[][] initializeMap(int mapLength) {
        char[][] Map = new char[mapLength][2];
        for (int i = 0; i < mapLength; i++) {
            Arrays.fill(Map[i], ' ');
        }
        return Map;
    }

    private int getIndex(int record) {
        if (record == 1 || record == 3) {
            return 0;
        }
        return 1;
    }

    private char getResult(int record) {
        if (record == 1 || record == 4) {
            return 'O';
        }
        return 'X';
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
        this.record = new ArrayList<>();
        this.trial += 1;
    }

    public void initialize(List<String> bridge) {
        this.bridge = bridge;
        this.record = new ArrayList<>();
        this.trial = 1;
        this.success = false;
    }

    public boolean hasFail() {
        if (record.contains(2) || record.contains(3)) {
            return true;
        }
        return false;
    }
}
