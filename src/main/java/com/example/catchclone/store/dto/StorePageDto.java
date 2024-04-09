package com.example.catchclone.store.dto;

import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class StorePageDto {
  int page;

  int size;

  boolean isAsc;

  String sortBy;

  boolean cutByStarRate;

  int starCut;

  public Pageable toPageable() {
    return Objects.isNull(sortBy) ? PageRequest.of(page, size,
        Sort.by("createdAt").descending()) : sortBy();
  }
  public Pageable sortBy(){
    return isAsc ? PageRequest.of(page, size,
        Sort.by(sortBy).descending()) : PageRequest.of(page, size,
        Sort.by(sortBy).ascending());
  }

}
