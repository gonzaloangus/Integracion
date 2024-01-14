package selenium;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GoogleSearchTest {

	private WebDriver driver;

	@Before
	public void setUp() {
		// Configurar WebDriverManager para el controlador de Chrome
		WebDriverManager.chromedriver().setup();
		
		// Iniciar el navegador Chrome
		driver = new ChromeDriver();
		
		// Maximizar la ventana y abrir la URL
		driver.manage().window().maximize();
		driver.get("http://localhost:8086/Integracion/");
	}

	@Test
	public void testGooglePage() {
		// Esperar un máximo de 20 segundos para que los elementos estén disponibles
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// Encontrar el campo de búsqueda, limpiarlo, ingresar texto y enviar la búsqueda
		WebElement searchbox = driver.findElement(By.name("q"));
		searchbox.clear();
		searchbox.sendKeys("Validar");
		searchbox.submit();

		// Esperar un máximo de 20 segundos para que la página de resultados cargue
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		// Verificar el título de la página de resultados
		assertEquals("Validar - Google Search", driver.getTitle());
	}

	@After
	public void tearDown() {
	    // Agregar un pequeño retardo antes de cerrar el navegador (10 segundos)
	    try {
	        Thread.sleep(10000);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }

	    // Verificar si el WebDriver no es nulo antes de llamar a quit()
	    if (driver != null) {
	        driver.quit();
	    }
	}
}
