package com.majestic.nails.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import com.majestic.nails.entity.Mail;
import com.majestic.nails.service.MailService;
import com.majestic.nails.mapper.BookingMapper;
import com.majestic.nails.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.majestic.nails.dto.BookingDto;
import com.majestic.nails.entity.Booking;
import com.majestic.nails.service.BookingService;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import static com.majestic.nails.common.MajesticConstant.STATUS_NEW;

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
		booking.setStatus(STATUS_NEW);
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
		if (validateEmail(dto.getEmail())) {
			sendConfirmationEmail(dto);
		}
		return dto;
	}

	private boolean validateEmail(String email) {
		boolean valid = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException e) {
			valid = false;
		}
		return valid;
	}

	public void sendNotificationEmail(BookingDto dto) {
		Mail bookingNotification = new Mail();

		bookingNotification.setMailFrom("Thai.nguyen.glo@gmail.com");
		bookingNotification.setMailTo("nguyenquocthai2779@gmail.com");
		bookingNotification.setMailSubject("Booking_Notification_" + dto.getName());
		bookingNotification.setType("Booking_notification");
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
		// Send to register
		bookingNotification.setMailTo(dto.getEmail());
		mailService.sendEmail(bookingNotification);
	}

	public void sendConfirmationEmail(BookingDto dto) {
		Mail bookingNotification = new Mail();

		bookingNotification.setMailFrom("Thai.nguyen.glo@gmail.com");
		bookingNotification.setMailSubject("Majestic: Booking Confirmation");
		bookingNotification.setType("Booking_confirmation");
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
		// Send to register
		bookingNotification.setMailTo(dto.getEmail());
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
