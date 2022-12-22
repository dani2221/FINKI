package mk.ukim.finki.wp.lab.selenium;

import mk.ukim.finki.wp.lab.exceptions.CourseNameExistsException;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Type;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.GradeService;
import mk.ukim.finki.wp.lab.service.StudentService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LoginTests {


    @Autowired
    CourseService courseService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentService studentService;


    private HtmlUnitDriver driver;

    private static String user = "user";
    private static String admin = "admin";

    private static boolean dataInitialized = false;


    @BeforeEach
    private void setup() throws CourseNameExistsException {
        this.driver = new HtmlUnitDriver(true);
        initData();
    }

    @AfterEach
    public void destroy() {
        if (this.driver != null) {
            this.driver.close();
        }
    }

    private void initData() throws CourseNameExistsException {
        if (!dataInitialized) {
            courseService.addCourse("name" ,"desc", 0);
            dataInitialized = true;
        }
    }

    @Test
    public void testScenario() throws Exception {
        String url = System.getProperty("geb.build.baseUrl", "http://localhost:999/courses");
        driver.get(url);
        List<WebElement> elements = driver.findElements(By.className("del"));
        Assert.assertEquals(elements.size(), 0);
        url = System.getProperty("geb.build.baseUrl", "http://localhost:999/login");
        driver.get(url);
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("admin");
        driver.findElement(By.className("btn")).click();
        elements = driver.findElements(By.className("del"));
        Assert.assertNotEquals(elements.size(), 0);

    }

}
