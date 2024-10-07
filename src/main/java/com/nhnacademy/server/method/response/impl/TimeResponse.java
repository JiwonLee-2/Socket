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

import com.nhnacademy.server.method.response.Response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeResponse implements Response {

    private static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Override
    public String getMethod() {
        return "time";
    }

    @Override
    public String execute(String value) {
        //#1 LocalDateTime을 이용해서 현재 시간을 설정하세요.
        LocalDateTime now = LocalDateTime.now();
        
        //#2 value(date format) "" or null 이면 DEFAULT_DATETIME_FORMAT 으로 반환 합니다.
        if(value == null || value.isEmpty()){
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMAT);
            return now.format(dateTimeFormatter);
        }

        //#3  value(date format) 의해서 formatting 하는 과정에서 value의 형식이 잘못 되었 다면 DEFAULT_DATETIME_FORMAT 으로 반환 합니다.
        try{
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(value);
            return now.format(dateTimeFormatter);
        } catch(IllegalArgumentException | DateTimeParseException e){
            return now.format(DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMAT));
        }
    }
}