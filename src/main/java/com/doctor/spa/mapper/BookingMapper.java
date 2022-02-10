package com.doctor.spa.mapper;

import com.doctor.spa.dto.BookingDto;
import com.doctor.spa.entity.Booking;

public interface BookingMapper {

	BookingDto toDto(Booking news);

}
