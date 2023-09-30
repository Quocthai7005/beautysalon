package com.majestic.nails.mapper;

import com.majestic.nails.dto.BookingDto;
import com.majestic.nails.entity.Booking;

public interface BookingMapper {

	BookingDto toDto(Booking news);

}
