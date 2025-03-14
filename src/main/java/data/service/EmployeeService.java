package data.service;

import data.dto.EmployeeDTO;
import data.mapper.EmployeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeMapper employeeMapper;
    
    // 직원 등록
    public void insertEmployee(EmployeeDTO employeeDTO) {
        employeeMapper.insertEmployee(employeeDTO);
    }
    
    // 직원 목록 조회
    public List<EmployeeDTO> selectEmployees() {
        return employeeMapper.selectEmployees();
    }
    
    // 직원 상세 조회
    public EmployeeDTO selectEmployeeByNum(int num) {
        return employeeMapper.selectEmployeeByNum(num);
    }
    
    // 직원 삭제
    public void deleteEmployee(int num) {
        employeeMapper.deleteEmployee(num);
    }
    
    // 직원 수정
    public void updateEmployee(EmployeeDTO employeeDTO) {
        employeeMapper.updateEmployee(employeeDTO);
    }
}
