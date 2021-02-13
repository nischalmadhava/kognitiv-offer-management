package com.kognitiv.offermanagement;

import com.kognitiv.offermanagement.entity.Offer;
import com.kognitiv.offermanagement.repository.OfferRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class OfferManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(OfferManagementApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(OfferRepository offerRepository) throws ParseException {
		String vaildFromStr = "2021-02-12";
		DateFormat validFromDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date vaildFrom = validFromDateFormat.parse(vaildFromStr);

		String validTillStr = "2021-02-15";
		DateFormat validTillDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date validTill = validTillDateFormat.parse(validTillStr);

		return args -> {
			offerRepository.save(new Offer("Amazon Coupon", vaildFrom, validTill, "Bangalore"));
			offerRepository.save(new Offer("Flipkart Coupon", vaildFrom, validTill, "Mumbai"));
			offerRepository.save(new Offer("Paytm Gift Voucher", vaildFrom, validTill, "Delhi"));
			offerRepository.save(new Offer("PayPal Coupon", vaildFrom, validTill, "Chandigarh"));
		};
	}

}
