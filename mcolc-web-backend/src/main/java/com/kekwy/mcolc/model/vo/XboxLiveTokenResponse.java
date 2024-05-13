package com.kekwy.mcolc.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class XboxLiveTokenResponse {

    @JsonProperty("IssueInstant")
    private String issueInstant;
    @JsonProperty("NotAfter")
    private String notAfter;
    @JsonProperty("Token")
    private String token;
    @JsonProperty("DisplayClaims")
    private DisplayClaims displayClaims;

    @Data
    public static class DisplayClaims {
        @JsonProperty("xui")
        private List<Item> xui;

        @Data
        public static class Item {
            @JsonProperty("uhs")
            private String uhs;

        }
    }

}
