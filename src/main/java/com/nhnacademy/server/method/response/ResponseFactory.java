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

package com.nhnacademy.server.method.response;

import com.nhnacademy.server.method.response.exception.ResponseNotFoundException;
import com.nhnacademy.server.method.response.impl.EchoResponse;

import java.util.ArrayList;
import java.util.Objects;

public class ResponseFactory {
    private static final ArrayList<Response> responseList = new ArrayList<>(){{
        //#1-8 EchoResponse 객체를 성성해서 추가 합니다.
        add(new EchoResponse());
    }};

    public static Response getResponse(String method){
        Response reponse = responseList.stream().filter(o->o.validate(method)).findFirst().orElse(null);
        if(Objects.isNull(reponse)){
            throw new ResponseNotFoundException();
        }
        return reponse;
    }
}
