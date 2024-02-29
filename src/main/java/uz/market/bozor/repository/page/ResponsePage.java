package uz.market.bozor.repository.page;

import java.util.List;

public interface ResponsePage<T> {

  List<T> getElements();

  RequestPage getRequestPage();

  long getTotalElements();

}
