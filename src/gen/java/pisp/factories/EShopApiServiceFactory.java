package pisp.factories;

import pisp.EShopApiService;
import pisp.impl.EShopApiServiceImpl;

public class EShopApiServiceFactory {

   private final static EShopApiService service = new EShopApiServiceImpl();

   public static EShopApiService getEShopApi()
   {
      return service;
   }
}
