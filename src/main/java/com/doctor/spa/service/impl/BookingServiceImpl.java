package com.doctor.spa.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.doctor.spa.entity.Mail;
import com.doctor.spa.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.doctor.spa.dto.BookingDto;
import com.doctor.spa.entity.Booking;
import com.doctor.spa.mapper.BookingMapper;
import com.doctor.spa.repository.BookingRepository;
import com.doctor.spa.service.BookingService;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

	private final BookingRepository bookingRepository;
	private final BookingMapper bookingMapper;

	@Autowired
	MailService mailService;

	public BookingServiceImpl(BookingRepository bookingRepository, BookingMapper bookingMapper) {
		this.bookingRepository = bookingRepository;
		this.bookingMapper = bookingMapper;
	}

	@Override
	public BookingDto createBooking(BookingDto dto) {
		Booking booking = new Booking();
		booking.setName(dto.getName());
		booking.setEmail(dto.getEmail());
		booking.setPhone(dto.getPhone());
		booking.setQuestion(dto.getQuestion());
		booking.setStatus("NEW");
		booking.setHour(dto.getHour());
		booking.setMinute(dto.getMinute());
		String services = "";
		for (String service: dto.getServices()) {
			services = services.concat(service).concat("; ");
		}
		booking.setServices(services);
		LocalDateTime date = LocalDateTime.parse(dto.getConsultDate());
		booking.setConsultDate(date);
		bookingRepository.save(booking);

		sendNotificationEmail(dto);
		return dto;
	}

	public void sendNotificationEmail(BookingDto dto) {
		Mail bookingNotification = new Mail();

		bookingNotification.setMailFrom("Thai.nguyen.glo@gmail.com");
		bookingNotification.setMailTo("nguyenquocthai2779@gmail.com");
		bookingNotification.setMailSubject("subscription email");
		bookingNotification.setType("Booking");
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("name", dto.getName());
		model.put("email", dto.getEmail());
		model.put("phone", dto.getPhone());
		model.put("date", dto.getConsultDate());
		model.put("time", dto.getHour() + ":" + dto.getMinute());
		model.put("services", dto.getServices());
		model.put("status", "NEW");
		model.put("question", dto.getQuestion());
		bookingNotification.setModel(model);

		mailService.sendEmail(bookingNotification);
	}

	@Override
	public Page<BookingDto> getBookings(Pageable pageable) {
		return bookingRepository.findAll(pageable).map(bookingMapper::toDto);
	}

	@Override
	public Page<BookingDto> getBookingsWithStatus(Pageable pageable, String status) {
		return ((status == null || "".equals(status) ?
				bookingRepository.findAll(pageable) :
				bookingRepository.findByStatus(status, pageable)))
				.map(bookingMapper::toDto);
	}

	@Override
	public Integer getBookingsNo(String status) {
		List<Booking> bookings;
		if (status == null || "".equals(status)) {
			bookings = bookingRepository.findAll();
		} else {
			bookings = bookingRepository.findByStatus(status);
		}
		return bookings.size();
	}

	@Override
	public BookingDto getBookingDetail(long id) {
		Booking booking = bookingRepository.findById(id);
		return bookingMapper.toDto(booking);
	}

	@Override
	public Boolean updateStatus(long id, String status) {
		Booking booking = bookingRepository.findById(id);
		booking.setStatus(status);
		bookingRepository.save(booking);
		return true;
	}
}
