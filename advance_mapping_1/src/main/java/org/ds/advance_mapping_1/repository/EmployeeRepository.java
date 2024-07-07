package org.ds.advance_mapping_1.repository;

import org.ds.advance_mapping_1.bean.EmployeeBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeBean,Long> {
}
