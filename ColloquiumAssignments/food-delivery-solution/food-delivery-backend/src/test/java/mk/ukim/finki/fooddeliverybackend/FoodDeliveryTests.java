package mk.ukim.finki.fooddeliverybackend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mk.ukim.finki.fooddeliverybackend.dto.domain.CreateDishDto;
import mk.ukim.finki.fooddeliverybackend.model.domain.Dish;
import mk.ukim.finki.fooddeliverybackend.model.domain.Order;
import mk.ukim.finki.fooddeliverybackend.model.domain.Restaurant;
import mk.ukim.finki.fooddeliverybackend.model.domain.User;
import mk.ukim.finki.fooddeliverybackend.model.enums.OrderStatus;
import mk.ukim.finki.fooddeliverybackend.model.enums.Role;
import mk.ukim.finki.fooddeliverybackend.model.exceptions.DishNotFoundException;
import mk.ukim.finki.fooddeliverybackend.repository.DishRepository;
import mk.ukim.finki.fooddeliverybackend.repository.OrderRepository;
import mk.ukim.finki.fooddeliverybackend.repository.RestaurantRepository;
import mk.ukim.finki.fooddeliverybackend.repository.UserRepository;
import mk.ukim.finki.fooddeliverybackend.selenium.*;
import mk.ukim.finki.fooddeliverybackend.util.CodeExtractor;
import mk.ukim.finki.fooddeliverybackend.util.ExamAssert;
import mk.ukim.finki.fooddeliverybackend.util.SubmissionHelper;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import java.net.UnknownHostException;
import java.time.Duration;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
class FoodDeliveryTests {

    static {
        SubmissionHelper.exam = "test";
        //TODO: CHANGE THE VALUE OF THE SubmissionHelper.index WITH YOUR INDEX NUMBER!!!
        SubmissionHelper.index = "000000"; // INDEX
    }

    private ChromeDriver driver;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Restaurant restaurant1;
    private Restaurant restaurant2;

    private Dish dish1;
    private Dish dish2;
    private Dish dish3;

    private User customer;
    private User owner;
    private User admin;

    private Order customerOrder;
    private Order ownerOrder;
    private Order adminOrder;

    @BeforeAll
    void setUpAll() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);

        customer = userRepository.save(new User(
                "customer",
                passwordEncoder.encode("customer"),
                "customer",
                "customer",
                "customer@email.com",
                Role.ROLE_CUSTOMER
        ));

        owner = userRepository.save(new User(
                "owner",
                passwordEncoder.encode("owner"),
                "owner",
                "owner",
                "owner@email.com",
                Role.ROLE_OWNER
        ));

        admin = userRepository.save(new User(
                "admin",
                passwordEncoder.encode("admin"),
                "admin",
                "admin",
                "admin@email.com",
                Role.ROLE_ADMIN
        ));
    }

    @BeforeEach
    void setUp() {
        restaurant1 = restaurantRepository.save(new Restaurant("Italian Restaurant", "An Italian restaurant."));
        restaurant2 = restaurantRepository.save(new Restaurant("Japanese Restaurant", "A Japanese restaurant."));

        dish1 = dishRepository.save(new Dish("Pizza", "A pizza.", 15.75, 10, restaurant1));
        dish2 = dishRepository.save(new Dish("Tiramisu", "A dessert.", 7.50, 5, restaurant1));
        dish3 = dishRepository.save(new Dish("Sushi", "A Japanese dish.", 22.25, 30, restaurant2));

        customerOrder = orderRepository.save(new Order(customer));
        ownerOrder = orderRepository.save(new Order(owner));
        adminOrder = orderRepository.save(new Order(admin));
    }

    @AfterEach
    void tearDown() {
        orderRepository.deleteAll();
        dishRepository.deleteAll();
        restaurantRepository.deleteAll();
    }

    @AfterAll
    public static void finalizeAndSubmit() throws JsonProcessingException, UnknownHostException {
        CodeExtractor.submitSourcesAndLogs();
    }

    private ResultMatcher[] matchDisplayDishDto(int index, Dish dish) {
        return new ResultMatcher[]{
                jsonPath(String.format("$[%d].name", index)).value(dish.getName()),
                jsonPath(String.format("$[%d].description", index)).value(dish.getDescription()),
                jsonPath(String.format("$[%d].price", index)).value(dish.getPrice()),
                jsonPath(String.format("$[%d].quantity", index)).value(dish.getQuantity()),
                jsonPath(String.format("$[%d].restaurantId", index)).value(dish.getRestaurant().getId())
        };
    }

    @org.junit.jupiter.api.Order(1)
    @Test
    @WithMockUser(roles = {"ADMIN"})
    void testFindDishByIdWithDetails() throws Exception {
        SubmissionHelper.startTest("testFindDishByIdWithDetails", 5);

        mockMvc.perform(get("/api/dishes/{id}/details", dish1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(dish1.getName()))
                .andExpect(jsonPath("$.description").value(dish1.getDescription()))
                .andExpect(jsonPath("$.price").value(dish1.getPrice()))
                .andExpect(jsonPath("$.quantity").value(dish1.getQuantity()))
                .andExpect(jsonPath("$.restaurant.name").value(dish1.getRestaurant().getName()))
                .andExpect(jsonPath("$.restaurant.description").value(dish1.getRestaurant().getDescription()));

        SubmissionHelper.endTest();
    }

    @org.junit.jupiter.api.Order(2)
    @Test
    void testFindDishByIdWithDetailsUi() throws Exception {
        SubmissionHelper.startTest("testFindDishByIdWithDetailsUi", 5);

        LoginPage.login(driver, "admin", "admin");
        DishPage.display(driver, dish1);

        SubmissionHelper.endTest();
    }

    @org.junit.jupiter.api.Order(3)
    @Test
    @WithMockUser(roles = {"ADMIN"})
    void testFindAllDishes() throws Exception {
        SubmissionHelper.startTest("testFindAllDishes", 2);

        mockMvc.perform(get("/api/dishes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpectAll(matchDisplayDishDto(0, dish1))
                .andExpectAll(matchDisplayDishDto(1, dish2))
                .andExpectAll(matchDisplayDishDto(2, dish3));

        SubmissionHelper.endTest();
    }

    @org.junit.jupiter.api.Order(4)
    @Test
    void testFindAllDishesUi() throws Exception {
        SubmissionHelper.startTest("testFindAllDishesUi", 3);

        int countOfDishes = (int) dishRepository.count();
        LoginPage.login(driver, "customer", "customer");
        DishesPage dishesPage = DishesPage.to(driver);
        dishesPage.assertItems(countOfDishes);
        dishesPage.assertButtons(countOfDishes, countOfDishes, 1);

        SubmissionHelper.endTest();
    }

    @org.junit.jupiter.api.Order(5)
    @Test
    @WithMockUser(roles = {"ADMIN"})
    void testAddDish() throws Exception {
        SubmissionHelper.startTest("testAddDish", 7);

        CreateDishDto createDishDto = new CreateDishDto("Spaghetti", "An Italian dish.", 12.5, 10, restaurant1.getId());

        MvcResult result = mockMvc.perform(
                        post("/api/dishes/add")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(createDishDto))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(createDishDto.name()))
                .andExpect(jsonPath("$.description").value(createDishDto.description()))
                .andExpect(jsonPath("$.price").value(createDishDto.price()))
                .andExpect(jsonPath("$.quantity").value(createDishDto.quantity()))
                .andExpect(jsonPath("$.restaurantId").value(createDishDto.restaurantId()))
                .andReturn();

        String response = result.getResponse().getContentAsString();
        Long dishId = objectMapper.readTree(response).get("id").asLong();

        mockMvc.perform(get("/api/dishes/{id}", dishId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(createDishDto.name()))
                .andExpect(jsonPath("$.description").value(createDishDto.description()))
                .andExpect(jsonPath("$.price").value(createDishDto.price()))
                .andExpect(jsonPath("$.quantity").value(createDishDto.quantity()))
                .andExpect(jsonPath("$.restaurantId").value(createDishDto.restaurantId()))
                .andReturn();

        SubmissionHelper.endTest();
    }

    @org.junit.jupiter.api.Order(6)
    @Test
    void testAddDishUi() throws Exception {
        SubmissionHelper.startTest("testAddDishUi", 8);

        int countOfDishes = (int) dishRepository.count();
        CreateDishDto createDishDto = new CreateDishDto("Spaghetti", "An Italian dish.", 12.5, 10, restaurant1.getId());
        LoginPage.login(driver, "admin", "admin");
        AddDish addDish = AddDish.add(driver, createDishDto);
        addDish.assertItems(countOfDishes + 1);

        SubmissionHelper.endTest();
    }

    @org.junit.jupiter.api.Order(7)
    @Test
    @WithMockUser(roles = {"ADMIN"})
    void testEditDish() throws Exception {
        SubmissionHelper.startTest("testEditDish", 7);

        CreateDishDto createDishDto = new CreateDishDto("Ramen", "A Japanese dish.", 7.5, 3, restaurant2.getId());

        mockMvc.perform(
                        put("/api/dishes/{id}/edit", dish2.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(createDishDto))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(createDishDto.name()))
                .andExpect(jsonPath("$.description").value(createDishDto.description()))
                .andExpect(jsonPath("$.price").value(createDishDto.price()))
                .andExpect(jsonPath("$.quantity").value(createDishDto.quantity()))
                .andExpect(jsonPath("$.restaurantId").value(createDishDto.restaurantId()))
                .andReturn();

        mockMvc.perform(get("/api/dishes/{id}", dish2.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(createDishDto.name()))
                .andExpect(jsonPath("$.description").value(createDishDto.description()))
                .andExpect(jsonPath("$.price").value(createDishDto.price()))
                .andExpect(jsonPath("$.quantity").value(createDishDto.quantity()))
                .andExpect(jsonPath("$.restaurantId").value(createDishDto.restaurantId()))
                .andReturn();

        SubmissionHelper.endTest();
    }

    @org.junit.jupiter.api.Order(8)
    @Test
    void testEditDishUi() throws Exception {
        SubmissionHelper.startTest("testEditDishUi", 8);

        int countOfDishes = (int) dishRepository.count();
        CreateDishDto createDishDto = new CreateDishDto("Ramen", "A Japanese dish.", 7.5, 3, restaurant2.getId());

        LoginPage.login(driver, "admin", "admin");
        EditDish editDish = EditDish.edit(driver, dish2.getId(), createDishDto);
        editDish.assertItems(countOfDishes);

        String nameSelector = String.format(".card[data-id='%s'] .dish-name", dish2.getId());
        String name = driver.findElement(By.cssSelector(nameSelector)).getText().trim();
        ExamAssert.assertEquals("The update dish name is not as expected.", createDishDto.name(), name);

        String descriptionSelector = String.format(".card[data-id='%s'] .dish-desc", dish2.getId());
        String description = driver.findElement(By.cssSelector(descriptionSelector)).getText().trim();
        ExamAssert.assertEquals("The update dish description is not as expected.", createDishDto.description(), description);

        Dish editedDish = dishRepository.findById(dish2.getId())
                .orElseThrow(() -> new DishNotFoundException(dish2.getId()));

        ExamAssert.assertEquals("The update dish price is not as expected.", createDishDto.price(), editedDish.getPrice());
        ExamAssert.assertEquals("The update dish quantity is not as expected.", createDishDto.quantity(), editedDish.getQuantity());
        ExamAssert.assertEquals("The update dish restaurant ID is not as expected.", createDishDto.restaurantId(), editedDish.getRestaurant().getId());

        SubmissionHelper.endTest();
    }

    @org.junit.jupiter.api.Order(9)
    @Test
    @WithMockUser(roles = {"ADMIN"})
    void testDeleteDish() throws Exception {
        SubmissionHelper.startTest("testDeleteDish", 2);

        mockMvc.perform(delete("/api/dishes/{id}/delete", dish3.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(dish3.getName()))
                .andExpect(jsonPath("$.description").value(dish3.getDescription()))
                .andExpect(jsonPath("$.price").value(dish3.getPrice()))
                .andExpect(jsonPath("$.quantity").value(dish3.getQuantity()))
                .andExpect(jsonPath("$.restaurantId").value(dish3.getRestaurant().getId()))
                .andReturn();

        mockMvc.perform(get("/api/dishes/{id}", dish3.getId()))
                .andExpect(status().isNotFound());

        SubmissionHelper.endTest();
    }

    @org.junit.jupiter.api.Order(10)
    @Test
    void testDeleteDishUi() throws Exception {
        SubmissionHelper.startTest("testDeleteDishUi", 3);

        int countOfDishes = (int) dishRepository.count();
        LoginPage.login(driver, "admin", "admin");
        DeleteDish deleteDish = DeleteDish.delete(driver, dish2.getId());
        deleteDish.assertItems(countOfDishes - 1);

        SubmissionHelper.endTest();
    }

    @org.junit.jupiter.api.Order(11)
    @Test
    @WithUserDetails(value = "admin")
    void testFindOrCreatePendingOrder() throws Exception {
        SubmissionHelper.startTest("testFindOrCreatePendingOrder", 5);

        mockMvc.perform(get("/api/orders/pending"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("admin"))
                .andExpect(jsonPath("$.dishes.length()").value(0))
                .andExpect(jsonPath("$.status").value(OrderStatus.PENDING.name()));

        SubmissionHelper.endTest();
    }

    @org.junit.jupiter.api.Order(12)
    @Test
    void testFindOrCreatePendingOrderUi() throws Exception {
        SubmissionHelper.startTest("testFindOrCreatePendingOrderUi", 5);

        adminOrder.getDishes().add(dish1);
        orderRepository.save(adminOrder);

        LoginPage.login(driver, "admin", "admin");
        OrderPage orderPage = OrderPage.to(driver);

        List<WebElement> orderItems = orderPage.getOrderList().findElements(By.className("order-item"));
        ExamAssert.assertEquals("The order list is empty.", 1, orderItems.size());

        SubmissionHelper.endTest();
    }

    @org.junit.jupiter.api.Order(13)
    @Test
    @WithUserDetails(value = "admin")
    void testAddDishToOrder() throws Exception {
        SubmissionHelper.startTest("testAddDishToOrder", 5);

        mockMvc.perform(post("/api/dishes/{id}/add-to-order", dish1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("admin"))
                .andExpect(jsonPath("$.dishes.length()").value(1))
                .andExpect(jsonPath("$.dishes[0].id").value(dish1.getId()))
                .andExpect(jsonPath("$.dishes[0].name").value(dish1.getName()))
                .andExpect(jsonPath("$.dishes[0].description").value(dish1.getDescription()))
                .andExpect(jsonPath("$.dishes[0].price").value(dish1.getPrice()))
                .andExpect(jsonPath("$.dishes[0].quantity").value(dish1.getQuantity() - 1))
                .andExpect(jsonPath("$.dishes[0].restaurantId").value(dish1.getRestaurant().getId()))
                .andExpect(jsonPath("$.status").value(OrderStatus.PENDING.name()));

        mockMvc.perform(get("/api/orders/pending"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("admin"))
                .andExpect(jsonPath("$.dishes.length()").value(1))
                .andExpect(jsonPath("$.dishes[0].id").value(dish1.getId()))
                .andExpect(jsonPath("$.dishes[0].name").value(dish1.getName()))
                .andExpect(jsonPath("$.dishes[0].description").value(dish1.getDescription()))
                .andExpect(jsonPath("$.dishes[0].price").value(dish1.getPrice()))
                .andExpect(jsonPath("$.dishes[0].quantity").value(dish1.getQuantity() - 1))
                .andExpect(jsonPath("$.dishes[0].restaurantId").value(dish1.getRestaurant().getId()))
                .andExpect(jsonPath("$.status").value(OrderStatus.PENDING.name()));

        SubmissionHelper.endTest();
    }

    @org.junit.jupiter.api.Order(14)
    @Test
    void testAddDishToOrderUi() throws Exception {
        SubmissionHelper.startTest("testAddDishToOrderUi", 5);

        LoginPage.login(driver, "admin", "admin");
        DishPage.order(driver, dish1.getId());
        OrderPage orderPage = OrderPage.to(driver);

        List<WebElement> orderItems = orderPage.getOrderList().findElements(By.className("order-item"));
        ExamAssert.assertEquals("The order list is empty.", 1, orderItems.size());

        SubmissionHelper.endTest();
    }

    @org.junit.jupiter.api.Order(15)
    @Test
    @WithUserDetails(value = "admin")
    void testRemoveDishFromOrder() throws Exception {
        SubmissionHelper.startTest("testRemoveDishFromOrder", 5);

        mockMvc.perform(post("/api/dishes/{id}/add-to-order", dish1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("admin"))
                .andExpect(jsonPath("$.dishes.length()").value(1))
                .andExpect(jsonPath("$.dishes[0].id").value(dish1.getId()))
                .andExpect(jsonPath("$.dishes[0].name").value(dish1.getName()))
                .andExpect(jsonPath("$.dishes[0].description").value(dish1.getDescription()))
                .andExpect(jsonPath("$.dishes[0].price").value(dish1.getPrice()))
                .andExpect(jsonPath("$.dishes[0].quantity").value(dish1.getQuantity() - 1))
                .andExpect(jsonPath("$.dishes[0].restaurantId").value(dish1.getRestaurant().getId()))
                .andExpect(jsonPath("$.status").value(OrderStatus.PENDING.name()));

        mockMvc.perform(post("/api/dishes/{id}/remove-from-order", dish1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("admin"))
                .andExpect(jsonPath("$.dishes.length()").value(0))
                .andExpect(jsonPath("$.status").value(OrderStatus.PENDING.name()));

        mockMvc.perform(get("/api/orders/pending"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("admin"))
                .andExpect(jsonPath("$.dishes.length()").value(0))
                .andExpect(jsonPath("$.status").value(OrderStatus.PENDING.name()));

        SubmissionHelper.endTest();
    }

    @org.junit.jupiter.api.Order(16)
    @Test
    void testRemoveDishFromOrderUi() throws Exception {
        SubmissionHelper.startTest("testRemoveDishFromOrderUi", 5);

        adminOrder.getDishes().add(dish1);
        orderRepository.save(adminOrder);

        LoginPage.login(driver, "admin", "admin");
        OrderPage orderPage = OrderPage.to(driver);

        List<WebElement> orderItems = orderPage.getOrderList().findElements(By.className("order-item"));
        ExamAssert.assertEquals("The order list is empty.", 1, orderItems.size());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(d -> d.findElement(By.className("remove-from-order")).isDisplayed());

        WebElement removeFromOrder = driver.findElement(By.className("remove-from-order"));
        removeFromOrder.click();

        wait.until(d -> d.findElements(By.className("remove-from-order")).isEmpty());

        orderPage = PageFactory.initElements(driver, OrderPage.class);
        wait.until(OrderPage::isPageFullyLoaded);

        orderItems = orderPage.getOrderList().findElements(By.className("order-item"));
        ExamAssert.assertEquals("The order list is not empty.", 0, orderItems.size());

        SubmissionHelper.endTest();
    }

    @org.junit.jupiter.api.Order(17)
    @Test
    @WithUserDetails(value = "admin")
    void testConfirmOrder() throws Exception {
        SubmissionHelper.startTest("testConfirmOrder", 3);

        adminOrder.getDishes().add(dish1);
        orderRepository.save(adminOrder);

        mockMvc.perform(put("/api/orders/pending/confirm", adminOrder.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("admin"))
                .andExpect(jsonPath("$.dishes.length()").value(1))
                .andExpect(jsonPath("$.status").value(OrderStatus.CONFIRMED.name()));

        SubmissionHelper.endTest();
    }

    @org.junit.jupiter.api.Order(18)
    @Test
    void testConfirmOrderUi() throws Exception {
        SubmissionHelper.startTest("testConfirmOrderUi", 2);

        adminOrder.getDishes().add(dish1);
        orderRepository.save(adminOrder);

        LoginPage.login(driver, "admin", "admin");
        OrderPage orderPage = OrderPage.to(driver);

        List<WebElement> orderItems = orderPage.getOrderList().findElements(By.className("order-item"));
        ExamAssert.assertEquals("The order list is empty.", 1, orderItems.size());

        orderPage.getConfirmButton().click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> d.findElements(By.className("order-item")).isEmpty());

        orderItems = orderPage.getOrderList().findElements(By.className("order-item"));
        ExamAssert.assertEquals("The order list is not empty.", 0, orderItems.size());
    }

    @org.junit.jupiter.api.Order(19)
    @Test
    @WithUserDetails(value = "admin")
    void testCancelOrder() throws Exception {
        SubmissionHelper.startTest("testCancelOrder", 3);

        adminOrder.getDishes().add(dish1);
        orderRepository.save(adminOrder);

        mockMvc.perform(put("/api/orders/pending/cancel", adminOrder.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("admin"))
                .andExpect(jsonPath("$.dishes.length()").value(1))
                .andExpect(jsonPath("$.status").value(OrderStatus.CANCELED.name()));
    }

    @org.junit.jupiter.api.Order(20)
    @Test
    void testCancelOrderUi() throws Exception {
        SubmissionHelper.startTest("testCancelOrderUi", 2);

        adminOrder.getDishes().add(dish1);
        orderRepository.save(adminOrder);

        LoginPage.login(driver, "admin", "admin");
        OrderPage orderPage = OrderPage.to(driver);

        List<WebElement> orderItems = orderPage.getOrderList().findElements(By.className("order-item"));
        ExamAssert.assertEquals("The order list is empty.", 1, orderItems.size());

        orderPage.getCancelButton().click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> d.findElements(By.className("order-item")).isEmpty());

        orderItems = orderPage.getOrderList().findElements(By.className("order-item"));
        ExamAssert.assertEquals("The order list is not empty.", 0, orderItems.size());

        SubmissionHelper.endTest();
    }

    @org.junit.jupiter.api.Order(21)
    @Test
    @WithUserDetails(value = "customer")
    void testRoleCustomer() throws Exception {
        SubmissionHelper.startTest("testRoleCustomer", 3);

        mockMvc.perform(get("/api/restaurants"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/restaurants/{id}", restaurant1.getId()))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/dishes"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/dishes/{id}", dish1.getId()))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/dishes/{id}/details", dish1.getId()))
                .andExpect(status().isOk());
        mockMvc.perform(post("/api/dishes/{id}/add-to-order", dish1.getId()))
                .andExpect(status().isOk());
        mockMvc.perform(post("/api/dishes/{id}/remove-from-order", dish1.getId()))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/orders/pending"))
                .andExpect(status().isOk());
        customerOrder.getDishes().add(dish1);
        orderRepository.save(customerOrder);
        mockMvc.perform(put("/api/orders/pending/confirm"))
                .andExpect(status().isOk());
        customerOrder.getDishes().add(dish1);
        orderRepository.save(customerOrder);
        mockMvc.perform(put("/api/orders/pending/cancel"))
                .andExpect(status().isOk());
        CreateDishDto createDishDto = new CreateDishDto("Spaghetti", "An Italian dish.", 12.5, 10, restaurant1.getId());
        mockMvc.perform(
                        post("/api/dishes/add")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(createDishDto))
                )
                .andExpect(status().isForbidden());
        createDishDto = new CreateDishDto("Ramen", "A Japanese dish.", 7.5, 3, restaurant2.getId());
        mockMvc.perform(
                        put("/api/dishes/{id}/edit", dish2.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(createDishDto))
                )
                .andExpect(status().isForbidden());
        mockMvc.perform(delete("/api/dishes/{id}/delete", dish3.getId()))
                .andExpect(status().isForbidden());
        mockMvc.perform(get("/api/users/{username}", customer.getUsername()))
                .andExpect(status().isForbidden());

        SubmissionHelper.endTest();
    }

    @org.junit.jupiter.api.Order(22)
    @Test
    @WithUserDetails(value = "owner")
    void testRoleOwner() throws Exception {
        SubmissionHelper.startTest("testRoleOwner", 3);

        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());

        mockMvc.perform(get("/api/restaurants"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/restaurants/{id}", restaurant1.getId()))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/dishes"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/dishes/{id}", dish1.getId()))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/dishes/{id}/details", dish1.getId()))
                .andExpect(status().isOk());
        mockMvc.perform(post("/api/dishes/{id}/add-to-order", dish1.getId()))
                .andExpect(status().isOk());
        mockMvc.perform(post("/api/dishes/{id}/remove-from-order", dish1.getId()))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/orders/pending"))
                .andExpect(status().isOk());
        ownerOrder.getDishes().add(dish1);
        orderRepository.save(ownerOrder);
        mockMvc.perform(put("/api/orders/pending/confirm"))
                .andExpect(status().isOk());
        ownerOrder.getDishes().add(dish1);
        orderRepository.save(ownerOrder);
        mockMvc.perform(put("/api/orders/pending/cancel"))
                .andExpect(status().isOk());
        CreateDishDto createDishDto = new CreateDishDto("Spaghetti", "An Italian dish.", 12.5, 10, restaurant1.getId());
        mockMvc.perform(
                        post("/api/dishes/add")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(createDishDto))
                )
                .andExpect(status().isOk());
        createDishDto = new CreateDishDto("Ramen", "A Japanese dish.", 7.5, 3, restaurant2.getId());
        mockMvc.perform(
                        put("/api/dishes/{id}/edit", dish2.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(createDishDto))
                )
                .andExpect(status().isOk());
        mockMvc.perform(delete("/api/dishes/{id}/delete", dish3.getId()))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/users/{username}", customer.getUsername()))
                .andExpect(status().isForbidden());

        SubmissionHelper.endTest();
    }

}
