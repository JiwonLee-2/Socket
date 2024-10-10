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

import com.nhnacademy.client.runable.MessageClient;
import com.nhnacademy.server.method.response.Response;
import com.nhnacademy.server.runable.MessageServer;
import com.nhnacademy.server.thread.channel.Session;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;

@Slf4j
class WhisperResponseTest {
    Response whisperResponse;

    @BeforeEach
    void setUp(){
        whisperResponse = new WhisperResponse();
        Session.initializeId("marco");
    }

    @Test
    @DisplayName("instance of Response")
    void constructor(){
        Assertions.assertInstanceOf(Response.class, whisperResponse);
    }

    @Test
    void getMethod() {
        Assertions.assertEquals("whisper",whisperResponse.getMethod());
    }

    @Test
    @DisplayName("validate : true")
    void validate1(){
        Assertions.assertTrue(whisperResponse.validate("whisper"));
    }

    @Test
    @DisplayName("validate : false")
    void validate2(){
        Assertions.assertFalse(whisperResponse.validate("login"));
    }

    @Test
    @DisplayName("whisper nhnacademy hello")
    void execute1() throws IOException {
        Socket client = Mockito.mock(Socket.class);
        Mockito.when(client.getOutputStream()).thenReturn(new ByteArrayOutputStream());

        try(MockedStatic<MessageServer> mockedStatic = mockStatic(MessageServer.class)){
            mockedStatic.when(()->{
                MessageServer.getClientSocket(anyString());
            }).thenReturn(client);
            String message = "hello";
            String actual = whisperResponse.execute(String.format("nhnacademy %s", message));
            Assertions.assertTrue(actual.contains(message));
            log.debug("actual:{}",actual);
        }
    }

    @Test
    @DisplayName("whisper nhnacademy hello java")
    void execute2() throws IOException {
        Socket client = Mockito.mock(Socket.class);
        Mockito.when(client.getOutputStream()).thenReturn(new ByteArrayOutputStream());

        try(MockedStatic<MessageServer> mockedStatic = mockStatic(MessageServer.class)){
            mockedStatic.when(()->{
                MessageServer.getClientSocket(anyString());
            }).thenReturn(client);
            String message = "hello java";
            String actual = whisperResponse.execute(String.format("nhnacademy %s", message));
            Assertions.assertTrue(actual.contains(message));
            log.debug("actual:{}",actual);
        }
    }

}