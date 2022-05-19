package sbmscripts.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class OTPDTO {
    private String OTP = "111111";

    @JsonCreator
    public OTPDTO(@JsonProperty("otp") String OTP) {
        this.OTP = OTP;
    }

}
