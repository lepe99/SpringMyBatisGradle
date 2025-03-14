package data.mapper;

import data.dto.EmployeeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    void insertEmployee(EmployeeDTO employeeDTO);
    List<EmployeeDTO> selectEmployees();
    EmployeeDTO selectEmployeeByNum(int num);
    void deleteEmployee(int num);
    void updateEmployee(EmployeeDTO employeeDTO);
}
