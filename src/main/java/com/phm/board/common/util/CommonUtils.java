package com.phm.board.common.util;

import java.util.UUID;

public class CommonUtils {

	// 32자의 랜덤한 문자열을 만들어주는 메서드 
	public static String getRandomString(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
