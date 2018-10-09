/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class MessagePublisher {

    @Autowired
    private BinderAwareChannelResolver resolver;

    public void publish(Object event) {
        resolver.resolveDestination(event.getClass().getName()).send(MessageBuilder.withPayload(event).build());
    }
}
