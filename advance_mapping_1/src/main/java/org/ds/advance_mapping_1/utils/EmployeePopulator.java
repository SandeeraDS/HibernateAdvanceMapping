package org.ds.advance_mapping_1.utils;

import org.ds.advance_mapping_1.bean.EmployeeBean;
import org.ds.advance_mapping_1.dto.EmployeeDTO;

public class EmployeePopulator {

    public static EmployeeDTO populateEmployeeDTO(EmployeeBean employeeBean) {
        return new EmployeeDTO(employeeBean.getId(), employeeBean.getFirstName(), employeeBean.getLastName(), employeeBean.getEmail());

    }

    public static EmployeeBean populateEmployeeBean(EmployeeDTO employeeDTO, boolean populateId) {
        EmployeeBean employeeBean = new EmployeeBean();
        if(populateId){
            employeeBean.setId(employeeDTO.getId());
        }
        employeeBean.setFirstName(employeeDTO.getFirstName());
        employeeBean.setLastName(employeeDTO.getLastName());
        employeeBean.setEmail(employeeDTO.getEmail());
        return employeeBean;
    }

}
