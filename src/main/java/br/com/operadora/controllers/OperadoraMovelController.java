package br.com.operadora.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class OperadoraMovelController {

	static ObjectMapper mapper = new ObjectMapper();

	static String msg(String status) throws JsonProcessingException {
		HashMap<String, String> m = new HashMap<String, String>();
		m.put("status", status);
		return mapper.writeValueAsString(m);
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET )
	public String im_here() {

		return "Operadora Móvel Dummy - L I G A D O";
	}

	/*
	 * TODO AppWebConfiguration CORS produces = {"application/xml",
	 * "application/json"} consumes ={"application/xml", "application/json"}
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/sms", method = RequestMethod.POST)
	public ResponseEntity<String> index(@RequestBody String dadosDeEnvio) {

		try {
			List<String> sms = mapper.readValue(dadosDeEnvio, List.class);
			String src = sms.get(0);
			String msg = sms.get(1);
			String dst = sms.get(2);

			/*
			 * TODO desconsiderar o prefixo if (src.charAt(2) == '9' ||
			 * dst.charAt(2) == '9')
			 */
			if (msg.isEmpty())
				return new ResponseEntity<String>(msg("Message is empty"), HttpStatus.PRECONDITION_FAILED);

			else if (src.length() != 11)
				return new ResponseEntity<String>(msg("Source phone is invalid"), HttpStatus.PRECONDITION_FAILED);

			else if (dst.length() != 11)
				return new ResponseEntity<String>(msg("Destination phone is invalid"), HttpStatus.PRECONDITION_FAILED);

			else if (src.charAt(2) == '9' || dst.charAt(2) == '9')
				return new ResponseEntity<String>(msg("Mobile user not found"), HttpStatus.PRECONDITION_FAILED);
			else
				return new ResponseEntity<String>(msg("SMS sent"), HttpStatus.CREATED);/* 201: Success */
			

		} catch (JsonParseException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * responses: "201": description: SMS sent "500": description: Internal
	 * Server Error "405": description: Validation exception "404": description:
	 * Mobile User not found
	 */

}