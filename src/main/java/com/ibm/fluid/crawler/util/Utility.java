/**
 * 
 */
package com.ibm.fluid.crawler.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.fluid.crawler.exception.CrawlerGenericException;
import com.ibm.fluid.crawler.implementation.local.kafka.model.KafkaMessage;

/**
 * @author BHASKARKONAR
 */
public class Utility {
	private static Logger log = LoggerFactory.getLogger(Utility.class);

	public static String generateHashId(String uniqueId) {
		MessageDigest messageDigest;
		String hashId = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(uniqueId.getBytes("UTF-8"));
			hashId = DatatypeConverter.printHexBinary(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			log.error("NoSuchAlgorithmException ocurred.", e);
		} catch (UnsupportedEncodingException e) {
			log.error("UnsupportedEncodingException ocurred.", e);
		}
		return hashId;
	}

	public static String generateJSONMessage(Object object) throws CrawlerGenericException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new CrawlerGenericException("JsonProcessingException occurred in call to sendKafkaMessage.", e);
		}
	}

	public static String generateUniqueID(String data) {
		StringBuilder uniqueId = new StringBuilder();
		uniqueId.append(data.replace('/', '_').replace(' ', '_').replace('.', '_'));

		return Utility.generateHashId(uniqueId.toString());
	}

	/**
	 * @param metaInfo
	 * @return
	 */
	public static String generateKafkaMessageID(KafkaMessage kafkaMsg) {
		StringBuilder id;
		id = new StringBuilder();
		id.append(kafkaMsg.getMetaInfo().getCrawler().getSessionID())
				.append(kafkaMsg.getMetaInfo().getCrawler().getUniqueId());
		return id.toString();
	}
}
