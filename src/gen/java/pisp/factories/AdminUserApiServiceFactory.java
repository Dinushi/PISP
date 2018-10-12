package pisp.factories;

import pisp.AdminUserApiService;
import pisp.impl.AdminUserApiServiceImpl;

public class AdminUserApiServiceFactory {

   private final static AdminUserApiService service = new AdminUserApiServiceImpl();

   public static AdminUserApiService getAdminUserApi()
   {
      return service;
   }
}
