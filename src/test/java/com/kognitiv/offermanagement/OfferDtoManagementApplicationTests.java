package com.kognitiv.offermanagement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kognitiv.offermanagement.dto.OfferListDto;
import com.kognitiv.offermanagement.entity.Offer;
import com.kognitiv.offermanagement.repository.OfferRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
class OfferDtoManagementApplicationTests {

	private static final ObjectMapper om = new ObjectMapper();

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	private OfferRepository offerRepository;

	private List<Offer> offerList;

	private OfferListDto offerListDto;


	@BeforeEach
	public void init() throws ParseException {
		String vaildFromStr = "2021-02-12";
		DateFormat validFromDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date vaildFrom = validFromDateFormat.parse(vaildFromStr);

		String validTillStr = "2021-02-15";
		DateFormat validTillDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date validTill = validTillDateFormat.parse(validTillStr);

		Offer offer1 = new Offer(1L,"Amazon Coupon", vaildFrom, validTill, "Bangalore");
		Offer offer2 = new Offer(2L,"Flipkart Coupon", vaildFrom, validTill, "Mumbai");
		Offer offer3 = new Offer(3L,"Paytm Gift Voucher", vaildFrom, validTill, "Delhi");
		Offer offer4 = new Offer(4L,"PayPal Coupon", vaildFrom, validTill, "Chandigarh");

		offerList = new ArrayList<>();

		offerList.add(offer1);
		offerList.add(offer2);
		offerList.add(offer3);
		offerList.add(offer4);

		offerListDto = new OfferListDto();
		offerListDto.setSuccess(true);
		offerListDto.setData(offerList);


		when(offerRepository.findAll()).thenReturn(offerList);
	}

	@Test
	public void fetch_all_offers_ok() throws Exception {
		String expected = "{\n" +
				"    \"success\": true,\n" +
				"    \"data\": [\n" +
				"        {\n" +
				"            \"id\": 1,\n" +
				"            \"name\": \"Amazon Coupon\",\n" +
				"            \"validFrom\": \"2021-02-11T18:30:00.000+00:00\",\n" +
				"            \"validTill\": \"2021-02-14T18:30:00.000+00:00\",\n" +
				"            \"location\": \"Bangalore\"\n" +
				"        },\n" +
				"        {\n" +
				"            \"id\": 2,\n" +
				"            \"name\": \"Flipkart Coupon\",\n" +
				"            \"validFrom\": \"2021-02-11T18:30:00.000+00:00\",\n" +
				"            \"validTill\": \"2021-02-14T18:30:00.000+00:00\",\n" +
				"            \"location\": \"Mumbai\"\n" +
				"        },\n" +
				"        {\n" +
				"            \"id\": 3,\n" +
				"            \"name\": \"Paytm Gift Voucher\",\n" +
				"            \"validFrom\": \"2021-02-11T18:30:00.000+00:00\",\n" +
				"            \"validTill\": \"2021-02-14T18:30:00.000+00:00\",\n" +
				"            \"location\": \"Delhi\"\n" +
				"        },\n" +
				"        {\n" +
				"            \"id\": 4,\n" +
				"            \"name\": \"PayPal Coupon\",\n" +
				"            \"validFrom\": \"2021-02-11T18:30:00.000+00:00\",\n" +
				"            \"validTill\": \"2021-02-14T18:30:00.000+00:00\",\n" +
				"            \"location\": \"Chandigarh\"\n" +
				"        }\n" +
				"    ]\n" +
				"}";

		ResponseEntity<String> response = restTemplate
				.withBasicAuth("kognitivUser", "password")
				.getForEntity("/collect/offer", String.class);

		printJSON(response);

		assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
		assertEquals(HttpStatus.OK, response.getStatusCode());

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	private static void printJSON(Object object) {
		String result;
		try {
			result = om.writerWithDefaultPrettyPrinter().writeValueAsString(object);
			System.out.println(result);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
