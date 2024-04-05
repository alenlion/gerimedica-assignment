package com.assignment.gerimedicaassignment.common.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@EqualsAndHashCode
public class ResponseDto<T>  {
    T data;
    Pageable pageable;
    Integer totalPage;
    Long totalElement;
    String message;
    Integer status;

}
