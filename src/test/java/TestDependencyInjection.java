import DAO.AccountDAOImpl;
import DAO.Interface.IAccountDAO;
import service.AccountService;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.*;

public class TestDependencyInjection {
    public static void main(String[] args) throws ParseException {
        ServiceBuilder.getInstance()
                .register(IAccountDAO.class, AccountDAOImpl.class)
                .register(AccountService.class, AccountService.class).build();
        var accountService = ServiceBuilder.getInstance().getService(AccountService.class);
        accountService.getAllAccounts().forEach(System.out::println);
    }
}
class ServiceBuilder{
    private static ServiceBuilder instance;
    private ServiceBuilder(){
    }
    public static ServiceBuilder getInstance(){
        if(instance == null){
            instance = new ServiceBuilder();
        }
        return instance;
    }
    private Map<Class<?>, Class<?>> serviceMap = new HashMap<>();
    private   Map<Class<?>, Object> serviceInstanceMap = new HashMap<>();
    public <T> ServiceBuilder register(Class<T> service, Class<? extends T> impl){
        serviceMap.put(service, impl);
        return this;
    }
    public void build(){

        for (Class<?> iService: serviceMap.keySet()){
            Class<?> impl = serviceMap.get(iService);
            try {
                Constructor<?> constructor = impl.getConstructor();
                Object instance = constructor.newInstance();
                serviceInstanceMap.put(iService, instance);
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
        }

        for (Class<?> iService: serviceInstanceMap.keySet()){
            var impl = serviceInstanceMap.get(iService);
            Field[] fields = impl.getClass().getDeclaredFields();
            Arrays.stream(fields).forEach(f->{
                if(serviceMap.containsKey(f.getType())){
                   String setterMethodName = "set" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
                     try {
                          Method setterMethod = impl.getClass().getMethod(setterMethodName, f.getType());
                            setterMethod.invoke(impl, serviceInstanceMap.get(f.getType()));
                     } catch (Exception e) {
                          e.printStackTrace();
                     }
                }else {
                    throw new RuntimeException("Dependency not found");
                }
            });
        }
    }
    public <T> T getService(Class<T> service){
        return (T) serviceInstanceMap.get(service);
    }

}
