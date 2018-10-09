/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.apigateway.cqrs;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration("com.seavus.bookstore.apigateway.cqrs.ChannelsConfiguration")
@EnableBinding(Channels.class)
public class ChannelsConfiguration {}
