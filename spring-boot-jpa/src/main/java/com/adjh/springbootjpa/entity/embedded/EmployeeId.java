package com.adjh.springbootjpa.entity.embedded;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 *
 */
@Getter
@Setter
@Embeddable
public class EmployeeId implements Serializable {
    private String companyId;
    private String employeeId;
}

