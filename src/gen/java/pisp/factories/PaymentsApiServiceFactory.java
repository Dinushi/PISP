package pisp.factories;

import pisp.PaymentsApiService;
import pisp.impl.PaymentsApiServiceImpl;

public class PaymentsApiServiceFactory {

   private final static PaymentsApiService service = new PaymentsApiServiceImpl();

   public static PaymentsApiService getPaymentsApi()
   {
      return service;
   }
}
