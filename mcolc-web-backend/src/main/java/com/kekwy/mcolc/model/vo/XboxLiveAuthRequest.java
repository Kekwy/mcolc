package com.kekwy.mcolc.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true, chain = true)
public class XboxLiveAuthRequest {
    @JsonProperty("Properties")
    private Properties properties = new Properties();
    @JsonProperty("RelyingParty")
    private String relyingParty;
    @JsonProperty("TokenType")
    private String tokenType;

    @Data
    @Accessors(fluent = true, chain = true)
    public static class Properties {
        @JsonProperty("AuthMethod")
        private String authMethod;
        @JsonProperty("SiteName")
        private String siteName;
        @JsonProperty("RpsTicket")
        private String rpsTicket;
    }

}
