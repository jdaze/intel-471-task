package dev.jdsoft.intel471.iprecords;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * endpoints definition for ip records creation and lookup
 */
@RestController
@RequestMapping("/ip-records")
public class IpRecordController {

  private final IpRecordFacade ipRecordFacade;

  public IpRecordController(IpRecordFacade ipRecordFacade) {
    this.ipRecordFacade = ipRecordFacade;
  }

  @GetMapping
  public ResponseEntity<List<IpRecord>> getIpRecordByValue(@RequestParam("value") String value) {
    return ResponseEntity.ok(ipRecordFacade.findByValue(value));
  }

  @PostMapping
  public ResponseEntity<IpRecord> createIpRecord(@RequestBody @Valid CreateIpRecordDto ipRecord) {
    return ResponseEntity.ok(ipRecordFacade.create(ipRecord));
  }
}
