package com.bizplus.boardsaturday.support;

import java.util.List;
import java.util.stream.Collectors;

public abstract class StringToIntegerConverter {

    public static List<Integer> convertStringListToIntList(List<String> stringList) {
        return stringList.stream()
                .map(Integer::parseInt) // 람다식을 사용하여 각 문자열을 정수로 변환
                .collect(Collectors.toList());
    }
}
