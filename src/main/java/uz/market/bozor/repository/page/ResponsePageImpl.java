package uz.market.bozor.repository.page;


import lombok.Getter;

import java.util.List;

@Getter
public class ResponsePageImpl<T> implements ResponsePage<T> {

  private final List<T> elements;

  private final RequestPage requestPage;

  private final long totalElements;

  public ResponsePageImpl(RequestPage request, List<T> elements, long totalElements) {
    this.requestPage = request;
    this.totalElements = totalElements;
    this.elements = elements;
  }
}
