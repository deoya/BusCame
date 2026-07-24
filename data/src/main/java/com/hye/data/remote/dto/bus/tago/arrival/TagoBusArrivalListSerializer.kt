package com.hye.data.remote.dto.bus.tago.arrival

import com.hye.data.remote.dto.bus.tago.TagoListSerializer

object TagoBusArrivalListSerializer :
    TagoListSerializer<TagoBusArrivalItemDto>(TagoBusArrivalItemDto.serializer())