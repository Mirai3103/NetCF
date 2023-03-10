package Migrate;

import DAO.JPA.Context;

import Utils.Helper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import model.*;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Main {
    @Transactional
    public static void accountMigration() {
        EntityManager entityManager = Context.getInstance().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        // create admin employee

        // create admin account
        Account adminAccount = new Account();
        adminAccount.setRole(Account.Role.ADMIN);
        adminAccount.setUsername("admin");
        adminAccount.setPassword("admin");
        entityManager.persist(adminAccount);

        Employee employeeAdmin = new Employee();
        employeeAdmin.setName("admin");
        employeeAdmin.setOtherInformation("admin");
        employeeAdmin.setCreatedAt(new Date());
        employeeAdmin.setAccountID(adminAccount.getId());
        entityManager.persist(employeeAdmin);

        // create manager employee
        Employee employeeManager = new Employee();
        employeeManager.setName("Nguyễn Văn A");
        employeeManager.setOtherInformation("Chủ cửa hàng");
        entityManager.persist(employeeManager);

        // create manager account
        Account managerAccount = new Account();
        managerAccount.setRole(Account.Role.MANAGER);
        managerAccount.setUsername("manager");
        managerAccount.setPassword("manager");
        entityManager.persist(managerAccount);
        employeeManager.setAccountID(managerAccount.getId());
        entityManager.persist(employeeManager);


        // create employee accounts
        for (int i = 1; i <= 5; i++) {
            Account account = new Account();
            account.setRole(Account.Role.EMPLOYEE);
            account.setUsername("employee" + i);
            account.setPassword("employee" + i);
            entityManager.persist(account);
            Employee employee = new Employee();
            employee.setName("Nhân viên " + i);
            employee.setOtherInformation("Nhân viên " + i);
            employee.setAccountID(account.getId());
            entityManager.persist(employee);
        }

        transaction.commit();
        entityManager.close();
    }

    @Transactional
    public static void machineMigration() {
        EntityManager entityManager = Context.getInstance().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        // create machines
        for (int i = 1; i <= 20; i++) {
            Computer machine = new Computer();
            machine.setName("Máy " + i);
            machine.setPrice(5000);
            machine.setStatus(Computer.ComputerStatus.OFF);
            machine.setType(Math.random() > 0.5 ? Computer.ComputerType.Vip : Computer.ComputerType.Normal);
            machine.setPrice(machine.getType() == Computer.ComputerType.Vip ? 10000 : 5000);
            entityManager.persist(machine);
        }

        transaction.commit();
        entityManager.close();
    }

    private static Product randomProduct(List<Product> listProducts) {


        int index = (int) (Math.random() * listProducts.size());
        return listProducts.get(index);
    }

    public static Employee randomEmployee(List<Employee> listEmployees) {
        int index = (int) (Math.random() * listEmployees.size());
        return listEmployees.get(index);
    }

    public static Date getRandomDate(Date from, Date to) {
        System.out.println(from.toString()+" "+to.toString());
        long startMillis = from.getTime();
        long endMillis = to.getTime();
        long randomMillis = ThreadLocalRandom.current().nextLong(startMillis, endMillis-1);
        return new Date(randomMillis);
    }

    @Transactional
    public static void fromSqlFileMigration() throws IOException {
        EntityManager entityManager = Context.getInstance().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        String path = "migrations";
        var files = Helper.getResourceFolderFiles(path);
        var filesEndWithSql = Arrays.stream(files).filter(p -> p.getName().endsWith(".sql")).toList();
        filesEndWithSql.forEach(f -> {
            try {
                String rawSql = new String(Files.readAllBytes(Path.of(f.getPath())));
                Query query = entityManager.createNativeQuery(rawSql);
                query.executeUpdate();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        transaction.commit();
        entityManager.close();
    }
    public static   Date createDateTime(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }
    @Transactional
    public static void invoiceMigration() {
        EntityManager entityManager = Context.getInstance().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        var random = new Random();
        List<Product> listProducts = entityManager.createQuery("select p from Product p where  p.stock > -1", Product.class).getResultList();
        List<Employee> listEmployees = entityManager.createQuery("select e from Employee e join Account a on e.accountID = a.id where a.role = 2 or a.role =1 ", Employee.class).getResultList();


        for (int i = 0; i < 10; i++) {
            // Tạo một hóa đơn ngẫu nhiên
            Invoice receipt = new Invoice();
            receipt.setCreatedToAccountId(null);
            receipt.setComputerId(null);
            receipt.setType(Invoice.InvoiceType.IMPORT);
            int numberOfProduct = random.nextInt(5);
            List<Product> products = new ArrayList<>();

            for (int j = 0; j < numberOfProduct; j++) {
                Product product = randomProduct(listProducts);
                if (products.contains(product)) {
                    continue;
                }
                products.add(product);
            }

            if (products.size() == 0) {
                continue;
            }

            Employee employee = listEmployees.get(random.nextInt(listEmployees.size()));
            receipt.setCreatedBy(employee.getId());
            receipt.setCreatedAt(getRandomDate(createDateTime(2021, 1, 1), new Date()));
            entityManager.persist(receipt);
            double total = 0.0;
            for (Product product : products) {
                InvoiceDetail invoiceDetail = new InvoiceDetail();
                invoiceDetail.setInvoiceId(receipt.getId());
                invoiceDetail.setProductId(product.getId());
                invoiceDetail.setQuantity((int) (Math.random() * 20) + 5);
                // from 2000 to 5000
                invoiceDetail.setPrice((int) (product.getPrice() * random.nextInt(3000, 6000)));
                entityManager.persist(invoiceDetail);
                product.setStock(product.getStock() + invoiceDetail.getQuantity());
                entityManager.merge(product);
                total += invoiceDetail.getQuantity() * invoiceDetail.getPrice();
            }
            receipt.setTotal(total);
            entityManager.merge(receipt);

        }

        transaction.commit();
        entityManager.close();
    }

    public static Computer randomComputer(List<Computer> listComputers) {
        int index = (int) (Math.random() * listComputers.size());
        return listComputers.get(index);
    }

    public static boolean checkValidUsageAccount(Date startAt, Date endAt, Integer accountId, EntityManager entityManager) {
        if (accountId == null) {
            return true;
        }
        List<ComputerUsage> listComputerUsage = entityManager.createQuery("select c from ComputerUsage c where c.usedByAccountId = :accountId and c.createdAt < :endAt and c.endAt > :startAt", ComputerUsage.class)
                .setParameter("accountId", accountId)
                .setParameter("endAt", endAt)
                .setParameter("startAt", startAt)
                .getResultList();
        return listComputerUsage.size() == 0;
    }

    public static boolean checkValidComputer(Date startAt, Date endAt, int computerId, EntityManager entityManager) {
        List<ComputerUsage> listComputerUsage = entityManager.createQuery("select c from ComputerUsage c where c.computerID = :computerId and c.createdAt < :endAt and c.endAt > :startAt", ComputerUsage.class)
                .setParameter("computerId", computerId)
                .setParameter("endAt", endAt)
                .setParameter("startAt", startAt)
                .getResultList();
        return listComputerUsage.size() == 0;
    }

    @Transactional
    public static void computerUsageMigration() {
        EntityManager entityManager = Context.getInstance().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        var random = new Random();
        List<Computer> listComputers = entityManager.createQuery("select c from Computer c", Computer.class).getResultList();
        List<Employee> listEmployees = entityManager.createQuery("select e from Employee e join Account a on e.accountID = a.id where a.role = 2 or a.role =1 ", Employee.class).getResultList();
        List<Account> listAccounts = entityManager.createQuery("select a from Account a where a.role = 3", Account.class).getResultList();
        for (int i = 0; i < 300; i++) {
            ComputerUsage computerUsage = new ComputerUsage();
            var money =((int)  (random.nextInt(5000, 26000)*1.0/1000))*1000;
            computerUsage.setTotalMoney(  money);
            computerUsage.setUsedByAccountId(random.nextInt(0, 20) % 3 == 0 ? null : listAccounts.get(random.nextInt(listAccounts.size())).getId());
            var machine = listComputers.get(random.nextInt(listComputers.size()));
            computerUsage.setComputerID(machine.getId());
            computerUsage.setCreatedAt(getRandomDate(createDateTime(2021, 1, 1), new Date()));

            long usageMilisecond = 60 * 60 *((long) Math.floor((money*1.0 / machine.getPrice())*1000));

            computerUsage.setEndAt(new Date(computerUsage.getCreatedAt().getTime() + usageMilisecond));
            while (!checkValidComputer(computerUsage.getCreatedAt(), computerUsage.getEndAt(), computerUsage.getComputerID(), entityManager)|| !checkValidUsageAccount(computerUsage.getCreatedAt(), computerUsage.getEndAt(), computerUsage.getUsedByAccountId(), entityManager)) {
                computerUsage.setCreatedAt(getRandomDate(createDateTime(2021, 1, 1), new Date()));
                usageMilisecond = ((long) (Math.floor(money / machine.getPrice())) * 60 * 60 * 1000);
                computerUsage.setEndAt(new Date(computerUsage.getCreatedAt().getTime() +  usageMilisecond));
            }
            entityManager.persist(computerUsage);
        }
        transaction.commit();
        entityManager.close();
    }
    @Transactional
    public static void migrateBill(){
        EntityManager entityManager = Context.getInstance().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        List<ComputerUsage> listComputerUsage = entityManager.createQuery("select c from ComputerUsage c join Computer m on c.computerID = m.id join Account a on a.id = c.usedByAccountId", ComputerUsage.class).getResultList();
        listComputerUsage.forEach(computerUsage -> {
            var isHasBill = new Random().nextInt(0, 100) % 5 == 0;
            if (isHasBill) {
                randomBill(computerUsage, entityManager);
            }
        });
        transaction.commit();
        entityManager.close();
    }

    private static void randomBill(ComputerUsage computerUsage, EntityManager entityManager) {
        var listFoods = entityManager.createQuery("select f from Product f where f.type = :type", Product.class).setParameter("type", Product.ProductType.FOOD).getResultList();
        var listDrinks = entityManager.createQuery("select f from Product f where f.type = :type", Product.class).setParameter("type", Product.ProductType.DRINK).getResultList();
        var listCards= entityManager.createQuery("select f from Product f where f.type = :type", Product.class).setParameter("type", Product.ProductType.CARD).getResultList();
        var listEmployees = entityManager.createQuery("select e from Employee e join Account a on e.accountID = a.id where a.role = 2 or a.role =1 ", Employee.class).getResultList();
        var invoice = new Invoice();
        invoice.setType(Invoice.InvoiceType.EXPORT);
        invoice.setPaid(true);
        invoice.setCreatedAt(getRandomDate(computerUsage.getCreatedAt(), computerUsage.getEndAt()));
        invoice.setStatus(Invoice.Status.DONE);
        invoice.setComputerId(computerUsage.getComputerID());
        invoice.setCreatedBy(listEmployees.get(new Random().nextInt(listEmployees.size())).getId());

        entityManager.persist(invoice);
        var random = new Random();
        var count = 0;
        if (random.nextInt(0, 100) % 2 == 0) {
            createBillDetail(entityManager, listDrinks, invoice, random);
            count++;
            if (random.nextInt(0, 100) % 3 == 0) {
                createBillDetail(entityManager, listFoods, invoice, random);
                count++;
            }
        }
        if (random.nextInt(0, 100) % 4 == 0) {
            createBillDetail(entityManager, listCards, invoice, random);
            count++;
        }
        if (count == 0) {
            createBillDetail(entityManager, listDrinks, invoice, random);
        }

    }

    private static void createBillDetail(EntityManager entityManager, List<Product> listDrinks, Invoice invoice, Random random) {
        var dinkBill = new InvoiceDetail();
        dinkBill.setInvoiceId(invoice.getId());
        var drink = listDrinks.get(random.nextInt(listDrinks.size()));
        dinkBill.setProductId(drink.getId());
        dinkBill.setQuantity(random.nextInt(1, 2));
        dinkBill.setPrice(drink.getPrice());
        entityManager.persist(dinkBill);
    }
    private static void caculateTotalMoney() {
        EntityManager entityManager = Context.getInstance().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        var listBill = entityManager.createQuery("select i from Invoice i  ", Invoice.class)
                .getResultList();
        listBill.forEach(invoice -> {
            var totalMoney = entityManager.createQuery("select sum(d.price * d.quantity) from InvoiceDetail d where d.invoiceId = :invoiceId", Double.class)
                    .setParameter("invoiceId", invoice.getId())
                    .getSingleResult();
            invoice.setTotal(totalMoney);
            entityManager.merge(invoice);
        });
        transaction.commit();
        entityManager.close();
    }
    public static void main(String[] args) throws IOException {
        accountMigration();
        machineMigration();
        fromSqlFileMigration();
        invoiceMigration();
        computerUsageMigration();
        migrateBill();
        caculateTotalMoney();
    }
}
