package dev.jdsoft.intel471.iprecords;

import com.atlassian.oai.validator.mockmvc.OpenApiValidationMatchers;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
public class IpRecordControllerTest {

  private static final String IP_RECORDS_PATH = "/ip-records";

  @Autowired private MockMvc mockMvc;

  private final URL yaml =
      Objects.requireNonNull(getClass().getClassLoader().getResource("intel-471-api.yaml"));

  @ParameterizedTest
  @MethodSource("validRequests")
  void givenValidJsonDataShouldReturnStatusOK(String requestBody) throws Exception {
    this.mockMvc
        .perform(post(IP_RECORDS_PATH).content(requestBody).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(HttpStatus.OK.value()))
        .andExpect(
            OpenApiValidationMatchers.openApi()
                .isValid(IOUtils.toString(yaml, StandardCharsets.UTF_8)));
  }

  @ParameterizedTest
  @MethodSource("invalidRequests")
  void givenInvalidJsonDataShouldReturnBadRequest(String requestBody) throws Exception {
    this.mockMvc
        .perform(post(IP_RECORDS_PATH).content(requestBody).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
  }

  private static List<String> validRequests() {
    return List.of(
        "{\"type\":\"ipv4\",\"value\":\"1.2.3.4\",\"firstSeen\":\"2020-07-10 15:00:00.000\",\"totalCount\":8}",
        "{\"type\":\"ipv4\",\"value\":\"1.2.3.4\",\"firstSeen\":\"2020-07-10 05:00:00.000\",\"totalCount\":100}",
        "{\"type\":\"ipv4\",\"value\":\"1.2.3.4\",\"firstSeen\":\"2020-07-10 16:03:00.000\",\"totalCount\":0}",
        "{\"type\":\"\",\"value\":\"1.2.3.4\",\"firstSeen\":\"2020-07-10 16:03:00.000\",\"totalCount\":0}");
  }

  private static List<String> invalidRequests() {
    return List.of(
        "{\"type\":\"ipv4\",\"value\":\"1.2.3.4\",\"firstSeen\":\"2020-07-10 15:00:00.000\",\"totalCount\":-1}",
        "{\"type\":\"ipv4\",\"value\":\"1.2.3.4\",\"firstSeen\":\"2020-07-10 15:00:00.000\",\"totalCount\":101}",
        "{\"type\":\"ipv4\",\"value\":\"1.2.3.4\",\"firstSeen\":\"2020.07.10 15:00:00.000\",\"totalCount\":100}",
        "{\"type\":\"ipv4\",\"value\":\"1.2.3.4\",\"firstSeen\":\"10.07.2020 15:00:00.000\",\"totalCount\":0}",
        "{\"type\":\"ipv4\",\"value\":\"\",\"firstSeen\":\"10-07-2020 15:00:00.000\",\"totalCount\":0}");
  }
}
