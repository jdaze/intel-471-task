package dev.jdsoft.intel471.iprecords;

import org.springframework.stereotype.Service;

import java.util.List;

/** Facade for IpRecordController */
// FYI: This is a simple way, but in "bigger" systems I'd prefer to use CQRS approach for data reads
// and writes. This is fine for the case imo, but if system grows it's better to split
@Service
class IpRecordFacade {

  private final IpRecordRepository ipRecordRepository;

  IpRecordFacade(IpRecordRepository ipRecordRepository) {
    this.ipRecordRepository = ipRecordRepository;
  }

  /**
   * Finds all ip records by value field
   *
   * @param value value string to perform lookup on ip records
   * @return list of ip records with given value field
   */
  List<IpRecord> findByValue(String value) {
    return ipRecordRepository.findByValue(value);
  }

  /**
   * @param createIpRecordDto dto to be mapped to IpRecord entity and saved
   * @return newly created entity
   */
  IpRecord create(CreateIpRecordDto createIpRecordDto) {
    return ipRecordRepository.save(createIpRecordDto.toIpRecord());
  }
}
