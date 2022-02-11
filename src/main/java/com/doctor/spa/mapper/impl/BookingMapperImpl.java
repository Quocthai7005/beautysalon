package com.doctor.spa.mapper.impl;

import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.doctor.spa.dto.BookingDto;
import com.doctor.spa.entity.Booking;
import com.doctor.spa.mapper.BookingMapper;

@Service
public class BookingMapperImpl implements BookingMapper {

	@Override
	public BookingDto toDto(Booking booking) {
		BookingDto dto = null;
		if (booking != null) {
			dto = new BookingDto();
			dto.setName(booking.getName());
			dto.setEmail(booking.getEmail());
			dto.setPhone(booking.getPhone());
			dto.setQuestion(booking.getQuestion());
			dto.setStatus(booking.getStatus());
			dto.setConsultDate(booking.getConsultDate().format(DateTimeFormatter.ISO_DATE));
		}
		return dto;
	}

}
