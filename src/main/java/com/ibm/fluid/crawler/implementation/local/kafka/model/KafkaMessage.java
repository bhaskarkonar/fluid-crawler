package com.ibm.fluid.crawler.implementation.local.kafka.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonNode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "metaInfo", "content" })

public class KafkaMessage {

	@JsonProperty("metaInfo")
	private MetaInfo metaInfo;
	// @JsonProperty("content")
	private JsonNode content;

	@JsonProperty("metaInfo")
	public MetaInfo getMetaInfo() {
		return metaInfo;
	}

	@JsonProperty("metaInfo")
	public void setMetaInfo(MetaInfo metaInfo) {
		this.metaInfo = metaInfo;
	}

	// @JsonProperty("content")
	public JsonNode getContent() {
		return content;
	}

	// @JsonProperty("content")
	public void setContent(JsonNode content) {
		this.content = content;
	}

}
