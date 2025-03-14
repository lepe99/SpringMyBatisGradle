package data.controller;

import data.dto.EmployeeDTO;
import data.service.EmployeeService;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import naver.storage.NcpObjectStorageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final NcpObjectStorageService ncpObjectStorageService;
    
    private String objectStorageUrl = "https://kr.object.ncloudstorage.com/bitcamp-bucket-120/employee/";
    private String bucketName = "bitcamp-bucket-120";
    private String frontUrl = "https://ymf0kmgk8726.edge.naverncp.com/hNwdEsMHVP/employee/";
    private String backUrl = "?type=f&w=30&h=30&faceopt=true&ttype=jpg";
    
    
    @GetMapping("/")
    public String home() {
        return "employee/home";
    }
    
    @GetMapping("/list")
    public String list(Model model) {
        List<EmployeeDTO> list = employeeService.selectEmployees();
        model.addAttribute("list", list);
        model.addAttribute("totalCount", list.size());
        model.addAttribute("objectStorageUrl", objectStorageUrl);
        model.addAttribute("frontUrl", frontUrl);
        model.addAttribute("backUrl", backUrl);
        
        return "employee/employeeList";
    }
    
    @GetMapping("/form")
    public String form() {
        return "employee/employeeForm";
    }
    
    @PostMapping("/insert")
    public String insert(@ModelAttribute EmployeeDTO employeeDTO,
                         @RequestParam("upload")MultipartFile file) {
        if (!file.isEmpty()) {
            String fileName = ncpObjectStorageService.uploadFile(bucketName, "employee", file);
            employeeDTO.setImage(fileName);
        }
        
        employeeService.insertEmployee(employeeDTO);
        return "redirect:./list";
    }
    
    @GetMapping("/delete")
    public String delete(@RequestParam int num) {
        employeeService.deleteEmployee(num);
        String image = employeeService.selectEmployeeByNum(num).getImage();
        if (image != null) {
            ncpObjectStorageService.deleteFile(bucketName, "employee", image);
        }
        
        return "redirect:./list";
    }
    
    @GetMapping("/detail")
    public String detail(@RequestParam int num, Model model) {
        EmployeeDTO employeeDTO = employeeService.selectEmployeeByNum(num);
        model.addAttribute("employee", employeeDTO);
        model.addAttribute("objectStorageUrl", objectStorageUrl);
        
        return "employee/detail";
    }
    
    @GetMapping("/updateForm")
    public String updateForm(@RequestParam int num, Model model) {
        EmployeeDTO employeeDTO = employeeService.selectEmployeeByNum(num);
        model.addAttribute("employee", employeeDTO);
        model.addAttribute("objectStorageUrl", objectStorageUrl);
        
        return "employee/updateForm";
    }
    
    @PostMapping("/update")
    public String update(@ModelAttribute EmployeeDTO employeeDTO,
                         @RequestParam("upload") MultipartFile file,
                         @RequestParam String oldImage) {
        if (!file.isEmpty()) {
            // 기존 이미지 삭제
            if (oldImage != null) {
                ncpObjectStorageService.deleteFile(bucketName, "employee", oldImage);
            }
            
            String fileName = ncpObjectStorageService.uploadFile(bucketName, "employee", file);
            employeeDTO.setImage(fileName);
        }
        
        employeeService.updateEmployee(employeeDTO);
        return "redirect:./detail?num=" + employeeDTO.getNum();
    }
}


