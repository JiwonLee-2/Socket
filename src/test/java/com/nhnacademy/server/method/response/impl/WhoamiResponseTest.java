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
import com.nhnacademy.server.thread.channel.Session;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
class WhoamiResponseTest {

    WhoamiResponse whoamiResponse;

    @BeforeEach
    void setUp(){
        whoamiResponse = new WhoamiResponse();
        Session.initializeId("marco");
    }

    @Test
    @DisplayName("instance of Response")
    void constructor(){
        Assertions.assertInstanceOf(Response.class, whoamiResponse);
    }

    @Test
    void getMethod() {
        Assertions.assertEquals("whoami",whoamiResponse.getMethod());
    }

    @Test
    @DisplayName("validate : true")
    void validate1(){
        Assertions.assertTrue(whoamiResponse.validate("whoami"));
    }

    @Test
    @DisplayName("validate : false")
    void validate2(){
        Assertions.assertFalse(whoamiResponse.validate("login"));
    }

    @Test
    @DisplayName("execute : whoami(\"marco\")")
    void execute() {
        String actual = whoamiResponse.execute("");
        Assertions.assertTrue(actual.contains("marco"));
    }

}