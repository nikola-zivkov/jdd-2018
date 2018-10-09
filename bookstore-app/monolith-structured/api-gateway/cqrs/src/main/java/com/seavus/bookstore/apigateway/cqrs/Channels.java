/*
 * Copyright (c) 2018 Seavus. All Rights Reserved.
 *
 * Use of this source code is governed by an MIT license, included in 'LICENSE.Seavus'.
 */

package com.seavus.bookstore.apigateway.cqrs;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {

    @Input("com_seavus_bookstore_api_cqrs_UpdateQBookRatingOnProductRatedEvent")
    SubscribableChannel updateQBookRatingOnProductRatedEvent();
}
