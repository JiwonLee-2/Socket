/*
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * + Copyright 2024. NHN Academy Corp. All rights reserved.
 * + * While every precaution has been taken in the preparation of this resource,  assumes no
 * + responsibility for errors or omissions, or for damages resulting from the use of the information
 * + contained herein
 * + No part of this resource may be reproduced, stored in a retrieval system, or transmitted, in any
 * + form or by any means, electronic, mechanical, photocopying, recording, or otherwise, without the
 * + prior written permission.
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 */

package com.nhnacademy.server.method.response.impl;

import java.lang.reflect.Method;
import java.util.NoSuchElementException;

import com.nhnacademy.server.method.parser.MethodParser;
import com.nhnacademy.server.method.parser.MethodParser.MethodAndValue;
import com.nhnacademy.server.method.response.Response;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EchoResponse implements Response {
    // echo method에 해당되는 응답을 구현 합니다.

    private final static String METHOD = "echo";

    @Override
    public String getMethod() {
        //#1-5 EchoResponse의 METHOD 를 반환 합니다.
        return EchoResponse.METHOD;
    }

    @Override
    public String execute(String value) {
        /*#1-6 value를 반환 합니다.
            - ex) echo hello message -> method = echo , value = hello 임으로 value를 반환 합니다.
        */
        MethodParser.MethodAndValue result = MethodParser.parse(value);
        if(result != null){
            String method = result.getMethod();
            String resultValue = result.getValue();
            log.debug("method: {}, value: {}", method, resultValue);
            return resultValue;
        }
        else {
            throw new NoSuchElementException("입력값을 받지 못했습니다.");
        }
    }
}
