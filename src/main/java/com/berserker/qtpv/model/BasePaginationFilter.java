package com.berserker.qtpv.model;

import com.berserker.qtpv.base.Constant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@AllArgsConstructor
public class BasePaginationFilter {
  int page;
  int size;
  public BasePaginationFilter() {
    this.page = Constant.PAGE_NUMBER;
    this.size = Constant.PAGE_SIZE;
  }

  public Pageable getPageable() {
    return PageRequest.of(page, size);
  }
}
