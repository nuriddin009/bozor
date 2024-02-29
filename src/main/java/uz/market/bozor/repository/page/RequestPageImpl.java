package uz.market.bozor.repository.page;

import lombok.Getter;

@Getter
public class RequestPageImpl implements RequestPage {

  private final int pageNumber;

  private final int pageLimit;

  private final int startingIndex;

  public RequestPageImpl(int pageNumber, int pageLimit) {
    this.pageNumber = pageNumber;
    this.pageLimit = pageLimit;
    this.startingIndex = (pageNumber - 1) * pageLimit;
  }

}
