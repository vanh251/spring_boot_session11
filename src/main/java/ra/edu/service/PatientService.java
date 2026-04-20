package ra.edu.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.edu.dto.PatientDTO;
import ra.edu.entity.Patient;
import ra.edu.repository.PatientRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public Patient addPatient(PatientDTO patientDTO) {
        log.info("Tiếp nhận bệnh nhân mới: {}", patientDTO.getName());

        if (patientDTO.getAge() != null && patientDTO.getAge() > 120) {
            log.warn("Cảnh báo: Tuổi bệnh nhân ({}) lớn hơn 120!", patientDTO.getAge());
        }

        Patient patient = new Patient();
        patient.setName(patientDTO.getName());
        patient.setAge(patientDTO.getAge());

        log.trace("Đang tiến hành lưu thông tin bệnh nhân vào Database...");

        return patientRepository.save(patient);
    }
}
