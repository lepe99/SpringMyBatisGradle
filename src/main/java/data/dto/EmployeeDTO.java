package data.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;

@Data
@Alias("employeeDto")
public class EmployeeDTO {
    private int num;
    private String name;
    private String image;
    private String gender;
    private String hp;
    private String dept;
    private String regDate;
    private Timestamp createdAt;
}
