package com.hye.data.remote.dto.bus.tago.station

import com.hye.data.remote.dto.bus.common.BusSttnItemDto
import com.hye.data.remote.dto.bus.tago.TagoListSerializer


object TagoBusSttnListSerializer : TagoListSerializer<BusSttnItemDto>(BusSttnItemDto.serializer())